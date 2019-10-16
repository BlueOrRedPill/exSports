package com.android.sportnmedc

import android.content.Intent
import android.os.Handler
import com.android.sportnmedc.ui.activities.LoginActivity

/**
 * Created by AndroidEB on 1/8/2018.
 */
class LandingActivityEx: ExBaseActivity(R.layout.activity_landing,0) {

    private val handler: Handler = Handler()
    private val runnable: Runnable =  Runnable {
        nextActivity()
        finish()
    }
    private var delay_time: Long = 0
    private var time = 1500L

    override fun onActivityCreated() {

    }

    override fun onResume() {
        super.onResume()
        delay_time = time
        handler.postDelayed(runnable, delay_time)
        time = System.currentTimeMillis()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
        time = delay_time - (System.currentTimeMillis() - time)
    }


    private fun nextActivity(){
        var startIntent: Intent
        if(!BaseApplication.prefs.accessToken.isNullOrEmpty() && BaseApplication.prefs.accessToken != "" && BaseApplication.prefs.profile.isNullOrEmpty() && BaseApplication.prefs.profile != "") {
            startIntent = HomeActivityEx.newIntent(mActivity)
        } else {
            startIntent = LoginActivity.newIntent(mActivity)
            if (intent.hasExtra("target")) {
                startIntent.putExtra("target", intent.getStringExtra("target"))
            }
        }
        startActivity(startIntent)
    }
}