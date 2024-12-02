package com.example

import io.quarkus.test.junit.QuarkusTest
import jakarta.inject.Inject
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.MultivaluedMap
import jakarta.ws.rs.ext.MessageBodyWriter
import jakarta.ws.rs.ext.Provider
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import kotlinx.serialization.serializer
import kotlinx.serialization.serializerOrNull
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.junit.jupiter.api.Test
import java.io.OutputStream
import java.lang.reflect.Type


@QuarkusTest
class GreetingResourceTest {

    @RestClient
    lateinit var greetingClient: GreetingClient

    @Test
    fun testHelloEndpoint() {
        greetingClient.hello(IdentifiedPerson("Max"))
    }
}

@RegisterRestClient(baseUri = "http://localhost:8081")
interface GreetingClient: GreetingInterface

/**
 * By using the following code, the JUnit-Test from above will work.
 */
/*@OptIn(ExperimentalSerializationApi::class)
@Provider
class KotlinSerializationHandler : MessageBodyWriter<Any> {

    @Inject
    lateinit var json: Json

    override fun isWriteable(type: Class<*>, genericType: Type, annotations: Array<out Annotation>?, mediaType: MediaType?): Boolean {
        return serializerOrNull(genericType) != null
    }

    override fun writeTo(
        t: Any,
        type: Class<*>,
        genericType: Type,
        annotations: Array<out Annotation>?,
        mediaType: MediaType?,
        httpHeaders: MultivaluedMap<String, Any>?,
        entityStream: OutputStream,
    ) {
        when (t) {
            is String -> entityStream.write(t.toByteArray())
            else -> {
                val serializer = getSerializer(type, serializer(genericType))
                json.encodeToStream(serializer, t, entityStream)
            }
        }
    }

    private fun getSerializer(type: Class<*>, defaultSerializer: KSerializer<Any>): KSerializer<Any> {
        val serializerOfSuperClass = serializerOrNull(type.superclass)
        val serializerOfInterface = type.interfaces.asList().firstNotNullOfOrNull { serializerOrNull(it) }

        return if (serializerOfSuperClass != null) {
            getSerializer(type.superclass, serializerOfSuperClass)
        } else serializerOfInterface ?: defaultSerializer
    }
}*/
