package com.mobile.ewallet

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import apis.NavigationApiRequestHandler.ERN_ROUTE
import com.ewalletnavigation.ern.model.ErnRoute
import com.walmartlabs.electrode.reactnative.bridge.helpers.Logger

abstract class ElectrodeMiniAppFragment() : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    private var mElectrodeReactActivityListener: ElectrodeReactActivityListener? = null
    private var ernRoute: ErnRoute? = null
    open val miniAppName: String?
        /**
         * Registered component name of your MiniApp
         *
         * @return String
         */
        get() {
            if (null == arguments || null == arguments!!.getString(KEY_MINI_APP_NAME)) {
                throw IllegalArgumentException("Arguments missing MiniApp name")
            }
            return arguments!!.getString(KEY_MINI_APP_NAME)
        }
    private val props: Bundle?
        /**
         * Return your bundle that will be passed to the MiniApp when the MiniApp is launched.
         * Return null if no props needs to be passed to a MiniApp when started.
         *
         * @return Bundle
         */
        get() {
            return if (ernRoute != null) {
                ernRoute!!.toBundle()
            } else null
        }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null && arguments!!.getBundle(ERN_ROUTE) != null) {
            ernRoute = ErnRoute(arguments!!.getBundle(ERN_ROUTE)!!)
        } else {
            Logger.w(TAG, "Unable to retrieve Route object")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return if (this.activity != null && !this.activity!!.isFinishing) {
            Log.i(TAG, "Inflate view for MiniApp: " + miniAppName)
            mElectrodeReactActivityListener!!.electrodeDelegate!!.createReactRootView(
                miniAppName!!,
                props
            )
        } else {
            Log.i(TAG, "Activity is finishing or already destroyed.")
            null
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
//        if (ernRoute!!.navBar != null) {
//            mElectrodeReactActivityListener!!.updateTitle(ernRoute!!.navBar!!.title)
//        }
    }

    fun onButtonPressed(uri: Uri?) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(
                context
                    .toString() + " must implement OnFragmentInteractionListener"
            )
        }
        if (context is ElectrodeReactActivityListener) {
            mElectrodeReactActivityListener = context
        } else {
            throw RuntimeException(
                (context.toString()
                        + " must implement ElectrodeReactActivityListener")
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri?)
    }

    companion object {
        const val KEY_MINI_APP_NAME = "MiniAppName"
        private val TAG = ElectrodeMiniAppFragment::class.java.simpleName
        protected fun putMiniAppNameArgument(route: ErnRoute, fragment: ElectrodeMiniAppFragment) {
            val args = Bundle()
            args.putString(KEY_MINI_APP_NAME, route.path)
            args.putBundle(ERN_ROUTE, route.toBundle())
            fragment.arguments = args
        }
    }
}