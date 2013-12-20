package smoothingmethod.method

class TaskController {

    def index() {
        redirect(action: 'list', params: params)
    }

    def list() {

        def taskList = Task.list()

        respond taskList
    }

    def show(Task task) {

        SmoothingMethod smoothingMethod = new SmoothingMethod(task)
        LinkedList<SmoothingMethod> list = new LinkedList(smoothingMethod.calc())

        respond task: task, taskData: list, a: smoothingMethod.getA(), last: list.removeLast()
    }

}
