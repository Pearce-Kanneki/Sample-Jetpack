package com.kanneki.resultapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class DetailContract: ActivityResultContract<Int, String?>() {

    companion object {
        const val TAG_VALUE = "value"
    }

    override fun createIntent(context: Context, input: Int?): Intent {
        // 發送資料
        return Intent(context, DetailActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        // 回傳資料
        return if (resultCode == Activity.RESULT_OK) {
            return intent?.getStringExtra(TAG_VALUE)
        } else {
            null
        }
    }
}