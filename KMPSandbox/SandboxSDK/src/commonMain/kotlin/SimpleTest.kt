class KotlinTestClass {
}

expect fun KotlinTestClass.expectedString(): String
fun KotlinTestClass.commonString() = "Hello Common String"

class GenericWrapper<T>(value: T) {
    private val some = value
    fun pull(): T = some
}