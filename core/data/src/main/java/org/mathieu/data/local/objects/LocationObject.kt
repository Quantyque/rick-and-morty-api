package org.mathieu.data.local.objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.domain.models.location.LocationPreview

internal class LocationObject: RealmObject {
    @PrimaryKey
    var id: Int = 1
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
    var created: String = ""
    var url: String = ""
}

internal fun LocationResponse.toRealmObject() = LocationObject().also { obj ->
    obj.id = id
    obj.name = name
    obj.type = type
    obj.dimension = dimension
    obj.created = created
    obj.url = url
}

internal fun LocationObject.toModel() = LocationPreview(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    url = url
)
