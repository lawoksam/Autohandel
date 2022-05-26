package autohandel

class Mechanic {

    String firstName
    String lastName
    Number age
    Number phone
    String address
    String gender

    static constraints = {
        age nullable: true
        gender nullable: true
    }

}
