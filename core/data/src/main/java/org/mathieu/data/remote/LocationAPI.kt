package org.mathieu.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import org.mathieu.data.remote.responses.CharacterResponse
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.data.remote.responses.PaginatedResponse

internal class LocationApi(private val client: HttpClient) {

    suspend fun getLocation(id: Int): LocationResponse? = client
        .get("location/$id")
        .accept(HttpStatusCode.OK)
        .body()

}
