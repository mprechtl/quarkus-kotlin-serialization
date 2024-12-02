package com.example

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType


@Path("/hello")
interface GreetingInterface {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    fun hello(person: Person): String;
}

class GreetingResource : GreetingInterface {

    override fun hello(person: Person): String {
        return if (person is IdentifiedPerson) {
            "Hello ${person.name} from Quarkus REST"
        } else {
            "Hello from Quarkus REST"
        }
    }
}
