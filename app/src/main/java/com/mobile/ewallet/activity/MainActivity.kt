package com.mobile.ewallet.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.auth.ern.api.AuthUserApi
import com.mobile.ewallet.fragment.AuthMiniAppFragment
import com.mobile.ewallet.fragment.ElectrodeMiniAppFragment
import com.mobile.ewallet.ui.FragmentContainer
import java.util.UUID

class MainActivity : ElectrodeCoreActivity(), ElectrodeMiniAppFragment.OnFragmentInteractionListener {
    private var authUserIdentifier: UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null);
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {add(it, AuthMiniAppFragment.newInstance())}
                )
            }
        }

        authUserIdentifier = AuthUserApi.events().addAuthUserEventEventListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent);
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        authUserIdentifier?.let { uuid ->
            AuthUserApi.events().removeAuthUserEventEventListener(uuid)
        }
    }
    override fun updateTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun onFragmentInteraction(uri: Uri?) {
        throw IllegalStateException("TODO: handle fragment interactions");
    }
}