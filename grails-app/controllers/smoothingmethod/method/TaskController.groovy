package smoothingmethod.method

import grails.converters.JSON

class TaskController {

    def index() {
        redirect(action: 'list', params: params)
    }

    def list() {

        def taskList = Task.list()

        respond taskList
    }

    def show(Task task) {

        withFormat {
            html task: task
            json {
                SmoothingMethodDataWrapper dataWrapper = new SmoothingMethod(task).calc()
                //JSON.use('deep')
                render dataWrapper as JSON
            }
        }
    }

}
