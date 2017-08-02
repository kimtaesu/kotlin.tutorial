class Foo {
    fun doSomething() {
        println("doSomething")
    }
}

fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)
    println("${memoizedFoo.javaClass}")

    memoizedFoo.doSomething()
}

fun main(args: Array<String>) {
    example { Foo() }
}

