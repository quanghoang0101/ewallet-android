package com.mobile.ewallet.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.auth.ern.api.AuthUserApi
import com.mobile.ewallet.fragment.AuthMiniAppFragment
import com.mobile.ewallet.fragment.ElectrodeMiniAppFragment
import com.mobile.ewallet.listener.ElectrodeReactActivityListener
import com.walmartlabs.ern.container.ElectrodeReactActivityDelegate
import java.util.UUID

class MainActivity : ElectrodeCoreActivity(), ElectrodeMiniAppFragment.OnFragmentInteractionListener {
    private val CONTENT_VIEW_ID = 10101010

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frame = FrameLayout(this)
        frame.id = CONTENT_VIEW_ID
        setContentView(
            frame, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
        )

        if (savedInstanceState == null) {
            val newFragment: ElectrodeMiniAppFragment = AuthMiniAppFragment.newInstance()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(CONTENT_VIEW_ID, newFragment).commit()
        }
    }

    override fun onResume() {
        super.onResume()
    }
    override fun updateTitle(title: String) {
        TODO("Not yet implemented")
    }

    override fun onFragmentInteraction(uri: Uri?) {
        throw IllegalStateException("TODO: handle fragment interactions");
    }
}

abstract class ElectrodeCoreActivity : AppCompatActivity(),
    ElectrodeReactActivityDelegate.BackKeyHandler,
    ElectrodeReactActivityListener {
    private var mReactActivityDelegate: ElectrodeReactActivityDelegate? = null
    private var authUserIdentifier: UUID? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mReactActivityDelegate = ElectrodeReactActivityDelegate(this)
        mReactActivityDelegate?.setBackKeyHandler(this)

        authUserIdentifier = AuthUserApi.events().addAuthUserEventEventListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent);
        }
    }

    override fun onBackKey() {
        //TODO: handle what needs to happen when a back key is pressed on a react native view.
        onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        mReactActivityDelegate?.onResume()
    }

    override fun onPause() {
        super.onPause()
        //mReactActivityDelegate!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mReactActivityDelegate?.onDestroy()
        authUserIdentifier?.let { uuid ->  AuthUserApi.events().removeAuthUserEventEventListener(uuid)}
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        mReactActivityDelegate?.onBackPressed()
    }

    override val electrodeDelegate: ElectrodeReactActivityDelegate
        get() {
            checkNotNull(mReactActivityDelegate) { "Something is not right, looks like reactActivityDelegate is not initialized" }
            return mReactActivityDelegate as ElectrodeReactActivityDelegate
        }

    companion object {
        private val TAG = ElectrodeCoreActivity::class.java.simpleName
    }
}