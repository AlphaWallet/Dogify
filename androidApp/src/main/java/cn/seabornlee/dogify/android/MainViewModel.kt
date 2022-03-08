package cn.seabornlee.dogify.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.seabornlee.dogify.model.Breed
import cn.seabornlee.dogify.model.FetchBreedsUseCase
import cn.seabornlee.dogify.model.GetBreedsUseCase
import cn.seabornlee.dogify.model.ToggleFavouriteStateUseCase
import cn.seabornlee.dogify.repository.BreedsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    breedsRepository: BreedsRepository,
    private val getBreeds: GetBreedsUseCase,
    private val fetchBreeds: FetchBreedsUseCase,
    private val onToggleFavouriteState: ToggleFavouriteStateUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State.LOADING)
    val state: StateFlow<State> = _state
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    enum class State {
        LOADING,
        NORMAL,
        ERROR,
        EMPTY
    }

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events

    enum class Event {
        Error
    }

    private val _shouldFilterFavourites = MutableStateFlow(false)
    val shouldFilterFavourites: StateFlow<Boolean> = _shouldFilterFavourites

    val breeds =
        breedsRepository.breeds.combine(shouldFilterFavourites)
        { breeds,
          shouldFilterFavourites ->
            if (shouldFilterFavourites) {
                breeds.filter { it.isFavourite }
            } else {
                breeds
            }.also {
                _state.value = if (it.isEmpty())
                    State.EMPTY else State.NORMAL
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun refresh() {
        loadData(true)
    }

    fun onToggleFavouriteFilter() {
        _shouldFilterFavourites.value = !shouldFilterFavourites.value
    }

    fun onFavouriteTapped(breed: Breed) {
        viewModelScope.launch {
            try {
                onToggleFavouriteState(breed)
            } catch (e: Exception) {
                _events.emit(Event.Error)
            }
        }
    }

    init {
        loadData()
    }

    private fun loadData(isForceRefresh: Boolean = false) {
        val getData: suspend () -> List<Breed> =
            {
                if (isForceRefresh) fetchBreeds.invoke()
                else getBreeds.invoke()
            }
        if (isForceRefresh) {
            _isRefreshing.value = true
        } else {
            _state.value = State.LOADING
        }
        viewModelScope.launch {
            _state.value = try {
                getData()
                State.NORMAL
            } catch (e: Exception) {
                State.ERROR
            }
            _isRefreshing.value = false
        }
    }
}