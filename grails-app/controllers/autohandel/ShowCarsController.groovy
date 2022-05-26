package autohandel

import grails.converters.XML

class ShowCarsController {

    def index() {

        render Car.findAll() as XML
    }
}
