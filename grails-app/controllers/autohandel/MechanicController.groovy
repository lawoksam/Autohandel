package autohandel

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MechanicController {

    MechanicService mechanicService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond mechanicService.list(params), model:[mechanicCount: mechanicService.count()]
    }

    def show(Long id) {
        respond mechanicService.get(id)
    }

    def create() {
        respond new Mechanic(params)
    }

    def save(Mechanic mechanic) {
        if (mechanic == null) {
            notFound()
            return
        }

        try {
            mechanicService.save(mechanic)
        } catch (ValidationException e) {
            respond mechanic.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mechanic.label', default: 'Mechanic'), mechanic.id])
                redirect mechanic
            }
            '*' { respond mechanic, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond mechanicService.get(id)
    }

    def update(Mechanic mechanic) {
        if (mechanic == null) {
            notFound()
            return
        }

        try {
            mechanicService.save(mechanic)
        } catch (ValidationException e) {
            respond mechanic.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'mechanic.label', default: 'Mechanic'), mechanic.id])
                redirect mechanic
            }
            '*'{ respond mechanic, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        mechanicService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'mechanic.label', default: 'Mechanic'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mechanic.label', default: 'Mechanic'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
