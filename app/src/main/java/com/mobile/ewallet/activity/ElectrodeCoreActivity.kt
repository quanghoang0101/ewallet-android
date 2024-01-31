package com.mobile.ewallet.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.ewallet.listener.ElectrodeReactActivityListener
import com.walmartlabs.ern.container.ElectrodeReactActivityDelegate

abstract class ElectrodeCoreActivity : AppCompatActivity(),
    ElectrodeReactActivityDelegate.BackKeyHandler,
    ElectrodeReactActivityListener {
    private var mReactActivityDelegate: ElectrodeReactActivityDelegate? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mReactActivityDelegate = ElectrodeReactActivityDelegate(this)
        mReactActivityDelegate?.setBackKeyHandler(this)
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
        //mReactActivityDelegate?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mReactActivityDelegate?.onDestroy()
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