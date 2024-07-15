package com.axhillex.onepointtask.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axhillex.onepointtask.database.Item
import com.axhillex.onepointtask.vm.ItemViewModel
import com.axhillex.onepointtask.vm.OnEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    itemsList: List<Item>,
    vm: ItemViewModel
) {
    val newItem by vm.newItem.collectAsState()
    val item by vm.selectedItem.collectAsState()
    var onAddClicked by rememberSaveable {
        mutableStateOf(false)
    }
    var isEditClicked by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.TopStart
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            stickyHeader {
                Text(
                    text = "My Items List",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(itemsList) { it ->
                ItemsView(
                    item = it,
                    onItemClick = {
                        vm.onEvent(OnEvent.OnItemSelected(it))
                    },
                    onEditClick = {
                        vm.onEvent(OnEvent.OnItemEditSelected(it))
                        isEditClicked = true
                    }
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .align(Alignment.BottomEnd)
                .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp)),
            onClick = {
                onAddClicked = true
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ) {
            Text(text = "Add Item")
        }
    }

    if (onAddClicked || isEditClicked) {
        AddItemBottomSheet(
            onAddItem = {
                vm.onEvent(OnEvent.OnUpsert)
                onAddClicked = false
                isEditClicked = false
            },
            onTitleChange = {
                vm.onEvent(OnEvent.OnTitleChange(it))
            },
            onDescriptionChange = {
                vm.onEvent(OnEvent.OnDescriptionChange(it))
            },
            title = newItem.title,
            description = newItem.description,
            onDismiss = {
                onAddClicked = false
                isEditClicked = false
            },
            isEditClicked = isEditClicked
        )
    }
    item?.let {
        BottomSheetDetailScreen(it) {
            vm.onEvent(OnEvent.OnItemSelected(null))
        }
    }
}

@Preview
@Composable
private fun ListScreenPreview() {
    ListScreen(
        itemsList = listOf(
            Item(title = "Test test", description = "test test", image = "1"),
            Item(title = "Test test", description = "test test", image = "1"),
            Item(title = "Test test", description = "test test", image = "1"),
        ),
        vm = koinViewModel<ItemViewModel>()
    )
}