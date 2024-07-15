package com.axhillex.onepointtask.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axhillex.onepointtask.database.Item
import com.axhillex.onepointtask.repo.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(
    private val itemRepo: ItemRepository
) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    private val _selectedItem = MutableStateFlow<Item?>(null)
    val selectedItem = _selectedItem.asStateFlow()

    private val _newItem = MutableStateFlow(Item())
    val newItem = _newItem.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(200)
            onEvent(OnEvent.GetList)
        }
    }

    internal fun onEvent(event: OnEvent) = viewModelScope.launch(Dispatchers.IO) {
        when (event) {
            is OnEvent.OnUpsert -> {
                if (newItem.value.title.isNotEmpty()) {
                    print(newItem.value)
                    itemRepo.upsertItem(newItem.value)
                    _newItem.value = Item()
                }
            }

            is OnEvent.OnDelete -> {
                itemRepo.deleteItem(event.item)
            }

            OnEvent.GetList -> {
                itemRepo.getAllItems().collect {
                    _items.value = it
                }
            }

            is OnEvent.OnItemSelected -> {
                _selectedItem.value = event.item
            }

            is OnEvent.OnDescriptionChange -> {
                _newItem.value = newItem.value.copy(description = event.description)
            }

            is OnEvent.OnTitleChange -> {
                _newItem.value = newItem.value.copy(title = event.title)
            }

            is OnEvent.OnItemEditSelected -> {
                event.item?.let {
                    _newItem.value = event.item
                }
            }
        }
    }
}

sealed class OnEvent {
    data class OnItemSelected(val item: Item?) : OnEvent()
    data class OnItemEditSelected(val item: Item?) : OnEvent()
    data object OnUpsert : OnEvent()
    data class OnDelete(val item: Item) : OnEvent()
    data class OnTitleChange(val title: String) : OnEvent()
    data class OnDescriptionChange(val description: String?) : OnEvent()
    data object GetList : OnEvent()
}