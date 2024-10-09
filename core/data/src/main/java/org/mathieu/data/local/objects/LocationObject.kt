package org.mathieu.data.local.objects

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mathieu.data.remote.responses.LocationResponse
import org.mathieu.data.repositories.CharacterRepositoryImpl
import org.mathieu.data.repositories.tryOrNull
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.character.CharacterGender
import org.mathieu.domain.models.character.CharacterStatus
import org.mathieu.domain.models.location.Location

internal class LocationObject: RealmObject {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var type: String = ""
    var dimension: String = ""
    var residents: List<String> = emptyList()
    var created: String = ""
}


internal fun LocationResponse.toRealmObject() = LocationObject().also { obj ->
    obj.id = id
    obj.name = name
    obj.type = type
    obj.dimension = dimension
    obj.residents = residents
    obj.created = created
}

internal fun LocationObject.toModel() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = residents.map {
        Character(
            id = id,
            name = name,
            status = tryOrNull { CharacterStatus.valueOf(status) } ?: CharacterStatus.Unknown,
            species = species,
            type = type,
            gender = tryOrNull { CharacterGender.valueOf(gender) } ?: CharacterGender.Unknown,
            origin = originName to originId,
            location = locationName to locationId,
            avatarUrl = image
    }
)
