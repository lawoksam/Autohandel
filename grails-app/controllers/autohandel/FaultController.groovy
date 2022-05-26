package autohandel

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FaultController {

    FaultService faultService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond faultService.list(params), model:[faultCount: faultService.count()]
    }

    def show(Long id) {
        respond faultService.get(id)
    }

    def create() {
        respond new Fault(params)
    }

    def save(Fault fault) {
        if (fault == null) {
            notFound()
            return
        }

        try {
            faultService.save(fault)
        } catch (ValidationException e) {
            respond fault.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fault.label', default: 'Fault'), fault.id])
                redirect fault
            }
            '*' { respond fault, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond faultService.get(id)
    }

    def update(Fault fault) {
        if (fault == null) {
            notFound()
            return
        }

        try {
            faultService.save(fault)
        } catch (ValidationException e) {
            respond fault.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fault.label', default: 'Fault'), fault.id])
                redirect fault
            }
            '*'{ respond fault, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        faultService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fault.label', default: 'Fault'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fault.label', default: 'Fault'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
