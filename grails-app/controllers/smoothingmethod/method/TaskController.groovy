package smoothingmethod.method

import grails.converters.JSON
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND

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
                SmoothingMethodDataWrapper dataWrapper = new SmoothingMethod(task, a).calc()
                render dataWrapper as JSON
            }
        }
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save() {
        Task taskInstance
        def jsonObject = request.JSON;
        println 'jsonObject: ' + jsonObject
        return //TODO

        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view:'create'
            return
        }

        taskInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'taskInstance.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*' { respond taskInstance, [status: CREATED] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskInstance.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
