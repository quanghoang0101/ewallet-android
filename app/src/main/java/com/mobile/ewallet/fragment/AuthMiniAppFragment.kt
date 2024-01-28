package com.mobile.ewallet.fragment

import android.os.Bundle
import com.mobile.ewallet.ElectrodeMiniAppFragment
import com.walmartlabs.ern.container.miniapps.MiniAppsConfig.MiniApps


class AuthMiniAppFragment: ElectrodeMiniAppFragment() {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ElectrodeMiniAppFragment.
     */

    companion object {
        open fun newInstance(): ElectrodeMiniAppFragment {
            val fragment: ElectrodeMiniAppFragment = AuthMiniAppFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override val miniAppName: String = MiniApps.AuthMiniapp.name
}