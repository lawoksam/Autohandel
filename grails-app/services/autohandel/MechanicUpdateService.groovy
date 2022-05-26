package autohandel

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import io.micronaut.http.client.HttpClient
import org.grails.web.json.JSONElement


import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse

@Transactional
class MechanicUpdateService {

    String url = "https://api.genderize.io";
    List<Mechanic> mechanics = Mechanic.findAllByAgeIsNull()

    def serviceMethod(String name)
    {
        mechanics.each {
            if(it.gender = null){
                HttpClient client = HttpClient.create(url.toURL())
                HttpRequest request = HttpRequest.GET("/?name=Andrzej")
                HttpResponse<String> resp = client.ToBlocking().exchange(request,String)
                JSONElement json = JSON.parse(resp.body())
                it.gender = json.getAt("gender")
                it.save()
            }
        }
    }




    }

