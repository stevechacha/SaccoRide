package com.dev.chacha.presentation.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.chacha.presentation.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    showSearchBar: Boolean = false,
    initialValue: String,
    onSearchParamChange: (searchParam: String) -> Unit,
    showMenuBar: Boolean = false,
    showBackArrow: Boolean = false
) {
    Surface(
       color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                },
                navigationIcon = {
                    if (showBackArrow) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_back),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                },

                actions = {
                    if (showMenuBar) {
                        IconButton(onClick = { }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
            AnimatedVisibility(visible = showSearchBar) {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, bottom = 4.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = .08F))
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    var searchParam: String by remember { mutableStateOf(initialValue) }
                    val focusRequester = remember { FocusRequester() }
                    val focusManager = LocalFocusManager.current

                    TextField(
                        value = searchParam,
                        onValueChange = { newValue ->
                            searchParam = if (newValue.trim().isNotEmpty()) newValue else ""
                            onSearchParamChange(newValue)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .focusRequester(focusRequester = focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Search...",
                                color = colorScheme.onSurface.copy(alpha = .32F)
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ), keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearchParamChange(searchParam)
                            }
                        ),
                        trailingIcon = {
                            Row {
                                AnimatedVisibility(visible = searchParam.trim().isNotEmpty()) {
                                    IconButton(onClick = {
                                        focusManager.clearFocus()
                                        searchParam = ""
                                        onSearchParamChange(searchParam)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                }

                                IconButton(onClick = {
                                    onSearchParamChange(searchParam)
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewAppTopBar() {
    AppTopBar(
        title = "Search",
        initialValue = "",
        onSearchParamChange = {},
        showMenuBar = true,
        showBackArrow = true,
        showSearchBar = true
    )
}