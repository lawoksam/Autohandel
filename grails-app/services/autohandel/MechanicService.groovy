package autohandel

import grails.gorm.services.Service

@Service(Mechanic)
interface MechanicService {

    Mechanic get(Serializable id)

    List<Mechanic> list(Map args)

    Long count()

    void delete(Serializable id)

    Mechanic save(Mechanic mechanic)

}