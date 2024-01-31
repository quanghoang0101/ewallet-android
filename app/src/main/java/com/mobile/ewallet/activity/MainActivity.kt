package com.mobile.ewallet.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import com.auth.ern.api.AuthUserApi
import com.mobile.ewallet.fragment.AuthMiniAppFragment
import com.mobile.ewallet.fragment.ElectrodeMiniAppFragment
import java.util.UUID

class MainActivity : ElectrodeCoreActivity(), ElectrodeMiniAppFragment.OnFragmentInteractionListener {
    private val CONTENT_VIEW_ID = 10101010
    private var authUserIdentifier: UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frame = FrameLayout(this)
        frame.id = CONTENT_VIEW_ID
        setContentView(
            frame,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
        )

        if (savedInstanceState == null) {
            val newFragment: ElectrodeMiniAppFragment = AuthMiniAppFragment.newInstance()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(CONTENT_VIEW_ID, newFragment).commit()
        }

        authUserIdentifier = AuthUserApi.events().addAuthUserEventEventListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
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