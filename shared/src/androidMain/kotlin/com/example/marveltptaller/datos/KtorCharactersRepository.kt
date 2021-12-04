package com.example.marveltptaller.datos

import com.example.marveltptaller.Modelo.Character
import com.example.marveltptaller.Modelo.CharactersRepository
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
class KtorCharactersRepository() : CharactersRepository {
    private val httpClient = HttpClient(){
       install(Logging){
           level = LogLevel.ALL
           logger = object : Logger{
               override fun log(message: String) {
                   Napier.v(tag = "Log de prueba", message = message)
               }

           }
       }
        install(JsonFeature){
            val json = kotlinx.serialization.json.Json {ignoreUnknownKeys = true}
            serializer = KotlinxSerializer(json)
        }
    }

    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        val res: ResponseMarvel = httpClient.get("https://gateway.marvel.com/v1/public/characters"){
            parameter("ts",timestamp)
            parameter("hash",md5)
            parameter("apikey", Keys().kPublic)
        }
        return res.data.results
    }
}
