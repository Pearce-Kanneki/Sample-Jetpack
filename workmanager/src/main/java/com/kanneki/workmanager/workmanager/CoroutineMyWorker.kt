package com.kanneki.workmanager.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class CoroutineMyWorker(context: Context, params: WorkerParameters)
    : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        runCatching {
            delay(3000)
        }.getOrNull()

        Log.d("TAG", "CoroutineWorker")

        return Result.success()
    }
}