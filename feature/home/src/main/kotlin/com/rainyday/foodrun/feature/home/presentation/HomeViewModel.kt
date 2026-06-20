package com.rainyday.foodrun.feature.home.presentation

import androidx.lifecycle.ViewModel
import com.rainyday.foodrun.feature.home.domain.model.RestaurantDomain
import com.rainyday.foodrun.feature.home.domain.usecases.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
): ViewModel() {
    private val _state = MutableStateFlow<HomeUIState>(HomeUIState.Loading)
    val state: StateFlow<HomeUIState> = _state.asStateFlow()

    private var currentPage = 1
    private val limit = 10
    private var isLastPage = false
    private val allRestaurants = mutableListOf<RestaurantDomain>()

    var searchQuery = MutableStateFlow("")
    var selectedCategory = MutableStateFlow<String?>(null)

    init {
        loadRestaurants()
    }

    fun loadRestaurants(reset: Boolean = false) {
        if (reset) {
            currentPage = 1
            isLastPage = false
            allRestaurants.clear()
        }

        if (isLastPage) return

        viewModelScope.launch {
            if (currentPage == 1) _state.value = HomeUIState.Loading

            val result = getRestaurantsUseCase(
                page = currentPage,
                limit = limit,
                category = selectedCategory.value,
                search = searchQuery.value.ifBlank { null }
            )
            result
                .onSuccess { restaurants ->
                    allRestaurants.addAll(restaurants)
                    currentPage++
                    isLastPage = restaurants.size < limit
                    _state.value = HomeUIState.Success(
                        data = restaurants,
                        isLoadingMore = false,
                        isLastPage = isLastPage
                    )
                }
                .onFailure { error ->
                    _state.value = HomeUIState.Error(error.message ?: "Unknown error")
                }
        }
    }

    fun loadMoreRestaurants() {
        if (_state.value is HomeUIState.Success && !isLastPage) {
            _state.value = (_state.value as HomeUIState.Success).copy(isLoadingMore = true)
            loadRestaurants()
        }
    }

    fun search(query: String) {
        searchQuery.value = query
        loadRestaurants(reset = true)
    }

    fun filterByCategory(category: String?) {
        selectedCategory.value = category
        loadRestaurants(reset = true)
    }
}