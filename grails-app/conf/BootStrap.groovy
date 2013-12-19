import smoothingmethod.Task

class BootStrap {

    def init = { servletContext ->

        new Task(name: 'Test').save(failOnError: true)

    }
    def destroy = {
    }
}
