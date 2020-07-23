import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

actual val applicationDispatcher: CoroutineContext = Dispatchers.IO