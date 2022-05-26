package autohandel

import grails.gorm.services.Service

@Service(Fault)
interface FaultService {

    Fault get(Serializable id)

    List<Fault> list(Map args)

    Long count()

    void delete(Serializable id)

    Fault save(Fault fault)

}