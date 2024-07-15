package com.axhillex.onepointtask.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemBottomSheet(
    onAddItem: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String?) -> Unit,
    title: String,
    description: String?,
    onDismiss: () -> Unit,
    isEditClicked: Boolean
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Title",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = onTitleChange
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = description ?: "",
                        onValueChange = onDescriptionChange
                    )
                    // Add more details about the item as needed
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    modifier = Modifier
                        .background(color = Color.DarkGray, shape = RoundedCornerShape(8.dp)),
                    onClick = { onAddItem() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text(text = if (isEditClicked) "Update Item" else "Add Item")
                }
            }
        }
    )
}

@Preview
@Composable
private fun BottomSheetPreview() {
    AddItemBottomSheet(
        onAddItem = {},
        onTitleChange = {},
        onDescriptionChange = {},
        title = "test",
        description = "test",
        onDismiss = {},
        isEditClicked = false
    )
}