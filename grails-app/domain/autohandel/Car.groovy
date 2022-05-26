package autohandel

class Car {

    static constraints = {
    }
    String brand;
    String model;
    String millage;
    String plateNumber;
    Number year;
    static hasMany = [faults: Fault]

}
