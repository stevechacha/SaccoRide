package com.dev.chacha.presentation.profile

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.dev.chacha.presentation.R

class ProfileListRepository {
    fun getAllData(): List<ProfileListModel> {
        return listOf(
            ProfileListModel(
                imageVector = Icons.Default.InvertColors,
                contentDescription = R.string.app_theme,
                text = R.string.app_theme
            ),
            ProfileListModel(
                imageVector = Icons.Default.NoAccounts,
                contentDescription = R.string.turn_on_incognito,
                text = R.string.turn_on_incognito
            ),
            ProfileListModel(
                imageVector = Icons.Default.Language,
                contentDescription = R.string.app_language,
                text = R.string.app_language
            ),
            ProfileListModel(
                imageVector = Icons.Default.Settings,
                contentDescription = R.string.settings,
                text = R.string.settings
            ),
            ProfileListModel(
                imageVector = Icons.Default.ModeOfTravel,
                contentDescription = R.string.drive_mode,
                text = R.string.drive_mode
            ),
            ProfileListModel(
                imageVector = Icons.Default.ChildCare,
                contentDescription = R.string.child_mode,
                text = R.string.child_mode
            ),
            ProfileListModel(
                imageVector = Icons.Default.BarChart,
                contentDescription = R.string.time_watched,
                text = R.string.time_watched
            ),
            ProfileListModel(
                imageVector = Icons.Default.Storage,
                contentDescription = R.string.account_data,
                text = R.string.account_data
            ),
            ProfileListModel(
                imageVector = Icons.Default.StarRate,
                contentDescription = R.string.rate_us_on_play_store,
                text = R.string.rate_us_on_play_store
            ),
            ProfileListModel(
                imageVector = Icons.Default.Feedback,
                contentDescription = R.string.feedback,
                text = R.string.feedback
            ),
            ProfileListModel(
                imageVector = Icons.Default.Forum,
                contentDescription = R.string.forum,
                text = R.string.forum
            ),
            ProfileListModel(
                imageVector = Icons.Default.HelpCenter,
                contentDescription = R.string.help_center,
                text = R.string.help_center
            ),
            ProfileListModel(
                imageVector = Icons.Default.PrivacyTip,
                contentDescription = R.string.privacy_policy,
                text = R.string.privacy_policy
            ),
            ProfileListModel(
                imageVector = Icons.Default.ControlPointDuplicate,
                contentDescription = R.string.terms_of_service,
                text = R.string.terms_of_service
            )
        )
    }
}

data class ProfileListModel(
    val imageVector: ImageVector,
    @StringRes val contentDescription: Int,
    @StringRes val text: Int
)