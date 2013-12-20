package smoothingmethod.example



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExampleController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Example.list(params), model:[exampleInstanceCount: Example.count()]
    }

    def show(Example exampleInstance) {
        respond exampleInstance
    }

    def create() {
        respond new Example(params)
    }

    @Transactional
    def save(Example exampleInstance) {
        if (exampleInstance == null) {
            notFound()
            return
        }

        if (exampleInstance.hasErrors()) {
            respond exampleInstance.errors, view:'create'
            return
        }

        exampleInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'exampleInstance.label', default: 'Example'), exampleInstance.id])
                redirect exampleInstance
            }
            '*' { respond exampleInstance, [status: CREATED] }
        }
    }

    def edit(Example exampleInstance) {
        respond exampleInstance
    }

    @Transactional
    def update(Example exampleInstance) {
        if (exampleInstance == null) {
            notFound()
            return
        }

        if (exampleInstance.hasErrors()) {
            respond exampleInstance.errors, view:'edit'
            return
        }

        exampleInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Example.label', default: 'Example'), exampleInstance.id])
                redirect exampleInstance
            }
            '*'{ respond exampleInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Example exampleInstance) {

        if (exampleInstance == null) {
            notFound()
            return
        }

        exampleInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Example.label', default: 'Example'), exampleInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'exampleInstance.label', default: 'Example'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
