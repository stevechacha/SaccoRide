package com.dev.chacha.presentation.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeNight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.*
import coil.request.ImageRequest
import coil.size.Size
import com.dev.chacha.presentation.R

@Composable
@Preview
fun ProfileScreen() {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://images.unsplash.com/photo-1626121496373-8b2b2b2b2b2b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80")
            .size(Size(100, 100))
            .build()
    )

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://images.unsplash.com/photo-1626121496373-8b2b2b2b2b2b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80")
            .size(Size(100, 100))
            .build(),
        contentDescription = "contentDescription",
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Transparent,
                            radius = this.size.maxDimension
                        )
                    },
                text = "NG",
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}








