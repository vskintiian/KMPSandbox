import platform.Foundation.NSThread

actual fun isMainThread(): Boolean = NSThread.isMainThread