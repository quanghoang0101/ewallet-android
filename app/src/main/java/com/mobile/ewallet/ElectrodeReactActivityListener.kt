package com.mobile.ewallet

import com.walmartlabs.ern.container.ElectrodeReactActivityDelegate

interface ElectrodeReactActivityListener {
    val electrodeDelegate: ElectrodeReactActivityDelegate?

    fun updateTitle(title: String)
}