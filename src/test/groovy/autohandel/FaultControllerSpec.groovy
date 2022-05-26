package autohandel

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class FaultControllerSpec extends Specification implements ControllerUnitTest<FaultController>, DomainUnitTest<Fault> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.faultList
        model.faultCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.fault!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/fault/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * save(_ as Fault)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def fault = new Fault(params)
        fault.id = 1

        controller.save(fault)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/fault/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * save(_ as Fault) >> { Fault fault ->
                throw new ValidationException("Invalid instance", fault.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def fault = new Fault()
        controller.save(fault)

        then:"The create view is rendered again with the correct model"
        model.fault != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * get(2) >> new Fault()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.fault instanceof Fault
    }

    void "Test the edit action with a null id"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * get(2) >> new Fault()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.fault instanceof Fault
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/fault/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * save(_ as Fault)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def fault = new Fault(params)
        fault.id = 1

        controller.update(fault)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/fault/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * save(_ as Fault) >> { Fault fault ->
                throw new ValidationException("Invalid instance", fault.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Fault())

        then:"The edit view is rendered again with the correct model"
        model.fault != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/fault/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.faultService = Mock(FaultService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/fault/index'
        flash.message != null
    }
}






