package com.mobile.ewallet.activity

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentManager
import com.mobile.ewallet.fragment.ElectrodeMiniAppFragment
import com.mobile.ewallet.fragment.NotificationMiniAppFragment
import com.mobile.ewallet.ui.FragmentContainer

class NotificationActivity: ElectrodeCoreActivity(), ElectrodeMiniAppFragment.OnFragmentInteractionListener {

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                AppContent(fragmentManager = supportFragmentManager)
            }
        }
    }
    override fun onFragmentInteraction(uri: Uri?) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
    }
}

@Composable
fun AppContent(fragmentManager: FragmentManager) {
    val activity = (LocalContext.current as? Activity)
    Scaffold (
        topBar = {
                 TopAppBar(
                     title = { 
                             Text(text = "Notification", textAlign = TextAlign.Center)
                     },
                     backgroundColor = Color.White,
                     elevation = 0.5.dp,
                     navigationIcon = {
                         IconButton(onClick = {
                             activity?.finish()
                         }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                         }
                     }
                 )
        },
        content = { padding ->
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                FragmentContainer(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    fragmentManager = fragmentManager,
                    commit = {add(it, NotificationMiniAppFragment.newInstance())}
                )
            }
        }
    )
}