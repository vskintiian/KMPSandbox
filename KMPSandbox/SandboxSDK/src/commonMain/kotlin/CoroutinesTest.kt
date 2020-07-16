import kotlinx.coroutines.*

expect fun isMainThread(): Boolean

class CoroutinesTest {
    fun executeInBackground(success: (Boolean) -> Unit, failure: (Boolean) -> Unit) =  GlobalScope.launch {
        println("IsMainThread Kotlin 1: ${isMainThread()}")
        success(execute())
        delay(3000)
        withContext(Dispatchers.Main) {
            println("IsMainThread Kotlin 2: ${isMainThread()}")
            failure(execute())

            delay(2000)
            withContext(Dispatchers.Default) {
                println("IsMainThread Kotlin 3: ${isMainThread()}")
                success(execute())
                isActive
            }
        }
    }


    suspend fun execute(): Boolean = coroutineScope {
        launch {
            println("IsMainThread Kotlin 0: ${isMainThread()}")
            println("Hello From Kotlin")
        }

        println("IsMainThread Kotlin 1: ${isMainThread()}")
        true
    }

    public suspend fun exec(string: String): String {
        println("IsMainThread Kotlin 0: ${isMainThread()}")
        return withContext(Dispatchers.Default) {
            println("IsMainThread Kotlin 1: ${isMainThread()}")
            return@withContext "$string Kotlin!"
        }
    }
}