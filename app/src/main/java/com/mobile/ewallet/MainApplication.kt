package com.mobile.ewallet

import android.app.Application
import com.walmartlabs.ern.container.ElectrodeReactContainer

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ElectrodeReactContainer.initialize(this, ElectrodeReactContainer.Config().isReactNativeDeveloperSupport(false))
    }
}