package org.mathieu.characters.details

import android.app.Application
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.koin.core.component.inject
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.LocationPreview
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.Destination
import org.mathieu.ui.ViewModel


class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

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

    fun handleAction(action: CharacterDetailAction) {
        when (action){
            is CharacterDetailAction.OnClickLocationCard -> {
                val currentState = state.value
                updateState {
                    currentState.copy(
                        avatarUrl = state.value.avatarUrl,
                        name = state.value.name,
                        homeLocation = state.value.homeLocation,
                        homeTypeLocation = state.value.homeTypeLocation,
                    )
                }
            }
        }
    }
}

data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val homeLocation: String = "",
    val homeTypeLocation: String = "",
    val error: String? = null
)

sealed interface CharacterDetailAction {
    data class OnClickLocationCard (
        val character: Character,
        val location: LocationPreview
    ) : CharacterDetailAction
}