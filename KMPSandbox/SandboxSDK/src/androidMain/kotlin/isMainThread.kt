import android.os.Looper

actual fun isMainThread(): Boolean {
    return Looper.myLooper() == Looper.getMainLooper()
}