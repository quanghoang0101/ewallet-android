package apis

import android.content.Intent
import android.net.Uri
import com.ewalletnavigation.ern.api.ErnNavigationApi
import com.walmartlabs.electrode.reactnative.bridge.BridgeFailureMessage
import com.walmartlabs.electrode.reactnative.bridge.None
import com.walmartlabs.ern.container.ElectrodeReactContainer

object NavigationApiRequestHandler {
    const val ERN_ROUTE = "ernRoute"
    fun register() {
        ErnNavigationApi.requests().registerNavigateRequestHandler { ernRoute, responseListener ->
            if (ernRoute != null && ElectrodeReactContainer.getCurrentActivity() != null) {
                val uri = "electrode-native://ern.walmartlabs.com/" + ernRoute.path
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(uri)
                )
                intent.putExtra(ERN_ROUTE, ernRoute.toBundle())
                ElectrodeReactContainer.startActivitySafely(intent)
                responseListener.onSuccess(None.NONE)
            } else {
                responseListener.onFailure(
                    BridgeFailureMessage.create(
                        "NAVIGATION_FAILED",
                        "Activity context not found to fire the intent"
                    )
                )
            }
        }
    }
}