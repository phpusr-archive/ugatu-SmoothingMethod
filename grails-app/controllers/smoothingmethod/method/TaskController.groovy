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
        //TODO выдать ошибку если taskInstance == null

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

        jsonObject.taskData.each { Map td -> //TODO попробовать избавиться от этого
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

        //Создание или Обновление TaskData
        List<TaskData> taskDataList = [] //TODO удалить все TaskData, а потом заново добавить
        jsonObject.taskData.each { Map td ->
            TaskData taskDataInstance
            if (td.id) {
                taskDataInstance = TaskData.get(td.id as Long).save(flush:true)
            } else {
                taskDataInstance = new TaskData(title: td.title, value: td.value, task: taskInstance).save(flush:true)
            }

            if (!taskDataInstance) {
                hasErrors()
                return
            }

            taskDataList << taskDataInstance
        }

        //Обновление Task
        taskInstance.properties = jsonObject.task
        taskInstance.data = taskDataList
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
