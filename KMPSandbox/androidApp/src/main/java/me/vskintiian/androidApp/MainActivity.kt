package me.vskintiian.androidApp

import CoroutinesTest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import isMainThread
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    val testExecutor = CoroutinesTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job = testExecutor.executeInBackground(success = {
            println("Success: Is main thread from Android: ${isMainThread()}")
        }, failure = {
            println("Failure: Is main thread from Android: ${isMainThread()}")
        })
    }
}
