package smoothingmethod

class TaskController {

    def index() {
        redirect(action: 'list', params: params)
    }

    def list() {

        def taskList = Task.list()

        [taskList: taskList]
    }

}
