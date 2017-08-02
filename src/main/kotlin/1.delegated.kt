import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class Example {
    var p: String by DelegateTest()
}

class DelegateTest {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

class UserMap(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

fun main(args: Array<String>) {
    val e = Example()
    println(e.p)

    val lazyValue: String by lazy {
        println("computed!")
        "Hello"
    }

    println(lazyValue)
    println(lazyValue)

    val user = User()
    user.name = "first"
    user.name = "second"

    val userMap = UserMap(mapOf(
            "name" to "John Doe",
            "age" to 25
    ))

    println("Name is ${userMap.name} / Age is ${userMap.age}")
}