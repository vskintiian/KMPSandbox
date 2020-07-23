package me.vskintiian.androidApp

import CoroutinesTest
import Repository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import isMainThread
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    val testExecutor = CoroutinesTest()
    val api = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val job = testExecutor.executeInBackground(success = {
//            println("Success: Is main thread from Android: ${isMainThread()}")
//        }, failure = {
//            println("Failure: Is main thread from Android: ${isMainThread()}")
//        })

        GlobalScope.launch {
            api.getMembers()
                .collect {
                    println("Hello")
                    println(it)
                }
        }

//        api.executeInBackground(success = {
//            println(it)
//        }, failure = {
//            println(it)
//        })
    }
}
