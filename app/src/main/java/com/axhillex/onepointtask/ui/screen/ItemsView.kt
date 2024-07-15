package com.axhillex.onepointtask.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.axhillex.onepointtask.database.Item

@Composable
fun ItemsView(
    item: Item,
    onItemClick: (Item) -> Unit,
    onEditClick: (Item) -> Unit
) = Row(
    Modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) },
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.Top
) {
    if (!item.image.isNullOrEmpty()) {
        AsyncImage(
            model = "https://th.bing.com/th/id/R.968d230ecc365755a8e98d7bdd0ef9e1?rik=LkOUJ5udXc4t7w&riu=http%3a%2f%2fwww.dumpaday.com%2fwp-content%2fuploads%2f2014%2f02%2frandom-pictures-132.jpg&ehk=TyU0k2lhHDLxkY7ZGi1AVRsGlZ9PXMwjG7ZFnvaydRY%3d&risl=&pid=ImgRaw&r=0",
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .aspectRatio(1f)
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                ),
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Details: ${item.description ?: "No Description Available"}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text(
            text = "Edit",
            modifier = Modifier
                .alignByBaseline()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .clickable {
                    onEditClick(item)
                }
        )
    }
}