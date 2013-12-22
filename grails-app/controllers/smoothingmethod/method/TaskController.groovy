package smoothingmethod.method

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

class TaskController {

    static allowedMethods = [list: 'GET', save: 'POST']

    def index() {
        redirect(action: 'list', params: params)
    }

    def list() {

        def taskList = Task.list()

        respond taskList
    }

    def show(Task task, Double a) {
        //TODO выдать ошибку если task == null

        withFormat {
            html task: task
            json {
                def dataWrapper
                try {
                    dataWrapper = new SmoothingMethod(task, a).calc()
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

        render ([status: OK] as JSON)
    }

    def edit(Task taskInstance) {
        JSON.use('deep')
        respond taskInstance
    }

    protected void notFound() {
        render ([status: NOT_FOUND] as JSON)
    }

    protected void hasErrors() {
        render ([status: INTERNAL_SERVER_ERROR] as JSON)
    }
}
