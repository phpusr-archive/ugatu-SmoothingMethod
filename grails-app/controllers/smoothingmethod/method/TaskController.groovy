package smoothingmethod.method

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class TaskController {

    static allowedMethods = [list: 'GET', save: 'POST', update: 'PUT']

    def index() {
        redirect action:'list'
    }

    def list() {

        def taskList = Task.list(params)

        respond taskList
    }

    def show(Task taskInstance, Double a) { //TODO a парсится нормально с точкой, а объект из params, кажется нормально создается с запятой: new News(params)
        if (!taskInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label'), params.id])
            redirect action:"list", method:"GET"
        }

        withFormat {
            html taskInstance: taskInstance
            json {
                if (taskInstance.data.size() == 0) {
                    def TDE = [status: [name: 'TaskDataSizeException'], message: message(code: 'taskData.size.error.message')]
                    render TDE as JSON
                    return
                }
                def dataWrapper
                try {
                    dataWrapper = new SmoothingMethod(taskInstance, a).calc()
                } catch(ArithmeticException e) {
                    def AE = [status: [name: 'ArithmeticException'], message: message(code: 'exception.arithmetic.error.message')]
                    render AE as JSON
                    return
                }
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
        withFormat {
            form taskInstance: taskInstance
            json {
                def taskData = taskInstance?.data?.sort(){ it.id }
                respond ([task: taskInstance, taskData: taskData])
            }
        }
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
        def obj = [status: INTERNAL_SERVER_ERROR, message: g.message(code: 'default.error.message')]
        render obj as JSON
    }
}
