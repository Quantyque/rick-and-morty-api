package org.mathieu.data.repositories

import org.mathieu.data.local.LocationLocal
import org.mathieu.data.local.objects.toModel
import org.mathieu.data.local.objects.toRealmObject
import org.mathieu.data.remote.LocationApi
import org.mathieu.domain.repositories.LocationRepository

internal class LocationRepositoryImpl(
    private val locationAPI: LocationApi,
    private val locationLocal: LocationLocal
) : LocationRepository {

    override suspend fun getLocationById(idLocation: Int) {
        locationLocal.getLocationById(idLocation)?.toModel()
            ?: locationAPI.getLocation(id = idLocation)?.let { response ->
                val obj = response.toRealmObject()
                locationLocal.insert(obj)
                obj.toModel()
            }
            ?: throw Exception("Character not found.")
    }


}