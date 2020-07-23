import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.coroutines.CoroutineContext

@OptIn(UnstableDefault::class)
internal val kotlinxSerializer = KotlinxSerializer(
    Json(
        JsonConfiguration(isLenient = true, ignoreUnknownKeys = true)
    )
)

internal val ktorClient = HttpClient {
    install(JsonFeature) {
        serializer = kotlinxSerializer
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}

internal const val MEMBERS_URL = "https://api.github.com/orgs/jetbrains/members"

class GithubApi(private val client: HttpClient = ktorClient) {
    suspend fun getMembers(): List<Member> = client.get(MEMBERS_URL)
}

@Serializable
data class Member(
    val id: Long,
    val login: String,
    @SerialName("avatar_url") val avatarUrl: String
)

class RefreshDataException : Throwable()

class Repository(private val api: GithubApi) {
    constructor(): this(api = GithubApi())

    fun getMembers(): Flow<List<Member>> {
        return flow {
            println("### getMembers: Is main: ${isMainThread()}")
            val members = api.getMembers()
            emit(members)
        }
            .catch { error(RefreshDataException()) }
            .flowOn(applicationDispatcher)
    }

    fun fetchMembers(success: (List<Member>) -> Unit, failure: (Throwable) -> Unit) {
        GlobalScope.launch(applicationDispatcher) {
            println("### fetchMembers: Is main: ${isMainThread()}")
            getMembers()
                .catch { failure(it) }
                .collect { success(it) }
        }
    }

    fun executeInBackground(success: (Member) -> Unit, failure: (Throwable) -> Unit) =  GlobalScope.launch(applicationDispatcher) {
        println("IsMainThread Kotlin 1: ${isMainThread()}")
        success(execute())
    }

    private suspend fun execute(): Member = coroutineScope {
        println("IsMainThread Kotlin 2: ${isMainThread()}")
        val members = api.getMembers()
        withContext(Dispatchers.Default) {
            println("IsMainThread Kotlin 3: ${isMainThread()}")
            members.first()
        }
    }
}

expect val applicationDispatcher: CoroutineContext