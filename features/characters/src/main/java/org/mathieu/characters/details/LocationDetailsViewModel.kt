package org.mathieu.characters.details

import android.app.Application
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.component.inject
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.ViewModel


class LocationDetailsViewModel(application: Application) : ViewModel<LocationDetailState>(
    LocationDetailState(), application) {

    private val characterRepository: CharacterRepository by inject()
    private val locationRepository: LocationRepository by inject()

    fun init(characterId: Int) {

        fetchData(
            source = {
                val character = characterRepository.getCharacter(characterId)
                val location = locationRepository.getLocationById(character.locationPreview.id)
                Pair(character, location)
            }
        ) {
            onSuccess { (character, location) ->
                updateState {
                    copy(
                        avatarUrl = character.avatarUrl,
                        name = character.name,
                        homeLocation = location.name,
                        homeTypeLocation = location.type,
                        error = null
                    )
                }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }


}

data class LocationDetailState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val homeLocation: String = "",
    val homeTypeLocation: String = "",
    val error: String? = null
)