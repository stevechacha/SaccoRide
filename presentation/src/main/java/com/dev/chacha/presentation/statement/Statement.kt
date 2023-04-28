package com.dev.chacha.presentation.statement

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.dev.chacha.presentation.download_manager.AndroidDownloader
import kotlinx.coroutines.DelicateCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun StatementList() {
    val context = LocalContext.current
    val downloader = AndroidDownloader(context)
    downloader.downloadFile("https://pl-coding.com/wp-content/uploads/2022/04/pic-squared.jpg")

    Button(onClick = {
        downloader.downloadFile("https://pl-coding.com/wp-content/uploads/2022/04/pic-squared.jpg")

    }) {
         Text(text = "Click me")
    }
}

@Composable
@Preview
fun PreviewStetementLists() {
    StatementList()
}
