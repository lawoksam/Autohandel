package autohandel

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FaultServiceSpec extends Specification {

    FaultService faultService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Fault(...).save(flush: true, failOnError: true)
        //new Fault(...).save(flush: true, failOnError: true)
        //Fault fault = new Fault(...).save(flush: true, failOnError: true)
        //new Fault(...).save(flush: true, failOnError: true)
        //new Fault(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //fault.id
    }

    void "test get"() {
        setupData()

        expect:
        faultService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Fault> faultList = faultService.list(max: 2, offset: 2)

        then:
        faultList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        faultService.count() == 5
    }

    void "test delete"() {
        Long faultId = setupData()

        expect:
        faultService.count() == 5

        when:
        faultService.delete(faultId)
        sessionFactory.currentSession.flush()

        then:
        faultService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Fault fault = new Fault()
        faultService.save(fault)

        then:
        fault.id != null
    }
}
