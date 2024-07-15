package com.axhillex.onepointtask.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.axhillex.onepointtask.database.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDetailScreen(
    item: Item,
    onDismiss: () -> Unit
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
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!item.image.isNullOrEmpty()) {
                        AsyncImage(
                            model = "https://th.bing.com/th/id/R.968d230ecc365755a8e98d7bdd0ef9e1?rik=LkOUJ5udXc4t7w&riu=http%3a%2f%2fwww.dumpaday.com%2fwp-content%2fuploads%2f2014%2f02%2frandom-pictures-132.jpg&ehk=TyU0k2lhHDLxkY7ZGi1AVRsGlZ9PXMwjG7ZFnvaydRY%3d&risl=&pid=ImgRaw&r=0",
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .aspectRatio(1f)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                        )
                    }
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Details: ${item.description ?: "No Description Available"}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    // Add more details about the item as needed
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}

@Preview
@Composable
private fun BottomSheetPreview() {
    BottomSheetDetailScreen(Item(title = "Item 1", description = "Description 1")) {}
}