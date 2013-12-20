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

        JSON.use('deep')
        respond task
    }

}
