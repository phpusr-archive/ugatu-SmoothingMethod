package smoothingmethod.method

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class TaskController {

    static allowedMethods = [list: 'GET', save: 'POST', update: 'PUT']

    def index() {
        redirect(action: 'list', params: params)
    }

    def list() {

        def taskList = Task.list()

        respond taskList
    }

    def show(Task taskInstance, Double a) {
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label'), params.id])
            redirect action:"list", method:"GET"
        }

        withFormat {
            html taskInstance: taskInstance
            json {
                def dataWrapper
                try {
                    dataWrapper = new SmoothingMethod(taskInstance, a).calc()
                } catch(ArithmeticException e) {
                    def AE = [status: [name: 'ArithmeticException', message: message(code: 'exception.arithmetic.error.message')]]
                    render (AE as JSON)
                    return
                }
                JSON.use(null)
                render dataWrapper as JSON
            }
        }
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save() {
        def jsonObject = request.JSON;
        Task taskInstance = new Task(jsonObject.task as Map)

        if (!taskInstance.save(flush:true)) {
            hasErrors()
            return
        }

        jsonObject.taskData.each { Map td ->
            TaskData taskDataInstance = new TaskData(title: td.title, value: td.value, task: taskInstance).save(flush:true)
            if (!taskDataInstance) {
                hasErrors()
                return
            }
        }

        render ([status: OK, id: taskInstance.id] as JSON)
    }

    def edit(Task taskInstance) {
        JSON.use('deep')
        respond taskInstance
    }

    @Transactional
    def update() {
        def jsonObject = request.JSON;
        Task taskInstance = Task.get(jsonObject.task.id as Long)

        //Удаление старых и добавление новых TaskData
        taskInstance.data.removeAll(taskInstance.data)
        List<TaskData> taskDataList = []
        jsonObject.taskData.each { Map td ->
            TaskData taskDataInstance = new TaskData(title: td.title, value: td.value, task: taskInstance).save(flush:true)
            if (!taskDataInstance) {
                hasErrors()
                return
            }
            taskDataList << taskDataInstance
        }

        //Обновление Task
        jsonObject.task.data = taskDataList
        taskInstance.properties = jsonObject.task
        if (!taskInstance.save(flush:true)) {
            hasErrors()
            return
        }

        render ([status: OK, id: taskInstance.id] as JSON)
    }

    @Transactional
    def delete(Task taskInstance) {

        if (taskInstance == null) {
            notFound()
            return
        }

        taskInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'task.label'), taskInstance.name])
                redirect action:"list", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        render ([status: NOT_FOUND] as JSON)
    }

    protected void hasErrors() {
        render ([status: INTERNAL_SERVER_ERROR] as JSON)
    }
}
