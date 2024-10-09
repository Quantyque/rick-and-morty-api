package org.mathieu.characters.details

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.domain.repositories.CharacterRepository
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.ViewModel


class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: CharacterRepository by inject()
    private val locationRepository: LocationRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = {
                characterRepository.getCharacter(characterId)
            }
        ) {
            onSuccess {
                updateState {
                    copy(
                        avatarUrl = it.avatarUrl,
                        name = it.name,
                        homeLocation = ,
                        homeLocationImageUrl = ,
                        error = null)
                }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }


}

data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val homeLocation: String = "",
    val homeLocationImageUrl:  String = "",
    val error: String? = null
)