package com.dev.chacha.presentation.download_manager

interface Downloader {
    fun downloadFile(url: String): Long
}