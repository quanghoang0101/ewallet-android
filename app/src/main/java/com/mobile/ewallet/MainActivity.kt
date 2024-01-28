package com.mobile.ewallet

import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentTransaction
import com.mobile.ewallet.fragment.AuthMiniAppFragment
import com.mobile.ewallet.ui.theme.EwalletandroidTheme

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EwalletandroidTheme {
        Greeting("Android")
    }
}