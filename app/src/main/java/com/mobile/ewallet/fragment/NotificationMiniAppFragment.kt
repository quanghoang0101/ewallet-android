package com.mobile.ewallet.fragment

import android.os.Bundle
import com.walmartlabs.ern.container.miniapps.MiniAppsConfig

class NotificationMiniAppFragment: ElectrodeMiniAppFragment() {
    companion object {
        open fun newInstance(): ElectrodeMiniAppFragment {
            val fragment: ElectrodeMiniAppFragment = NotificationMiniAppFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override val miniAppName: String = MiniAppsConfig.MiniApps.NotificationMiniapp.name
}