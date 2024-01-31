package com.mobile.ewallet.activity

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.mobile.ewallet.fragment.ElectrodeMiniAppFragment
import com.mobile.ewallet.fragment.NotificationMiniAppFragment
import com.mobile.ewallet.ui.FragmentContainer

class NotificationActivity: ElectrodeCoreActivity(), ElectrodeMiniAppFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {add(it, NotificationMiniAppFragment.newInstance())}
                )
            }
        }
    }
    override fun onFragmentInteraction(uri: Uri?) {
        TODO("Not yet implemented")
    }

    override fun updateTitle(title: String) {
        TODO("Not yet implemented")
    }
}