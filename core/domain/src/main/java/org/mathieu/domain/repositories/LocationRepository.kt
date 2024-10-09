package org.mathieu.domain.repositories

interface LocationRepository {

    suspend fun getLocationById(idLocation : Int)

}