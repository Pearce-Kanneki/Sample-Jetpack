package com.kanneki.appstartup

import android.content.Context
import androidx.startup.Initializer

class MyInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        // init
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}