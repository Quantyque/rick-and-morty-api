package org.mathieu.domain.repositories

import org.mathieu.domain.models.location.LocationPreview

interface LocationRepository {

    suspend fun getLocationById(idLocation : Int): LocationPreview

}