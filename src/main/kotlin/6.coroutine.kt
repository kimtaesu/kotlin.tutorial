import kotlinx.coroutines.experimental.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.coroutines.experimental.buildSequence

fun main(args: Array<String>) {
    lazys()


}

fun firstCoroutine() {
    println("Start")

    // Start a coroutine
    launch(CommonPool) {
        delay(1000)
        println("Hello")
    }

    Thread.sleep(2000) // wait for 2 seconds
    println("Stop")

    runBlocking {
        delay(2000)
    }

}

fun aa() {
    val c = AtomicInteger()

    for (i in 1..1_000_000)
        thread(start = true) {
            c.addAndGet(i)
        }

    println(c.get())


}

suspend fun workload(n: Int): Int {
    delay(1000)
    return n
}

fun build() {
    val lazySeq = buildSequence {
        print("START ")
        for (i in 1..5) {
            yield(i)
            print("STEP ")
        }
        print("END")
    }

// Print the first three elements of the sequence
    lazySeq.take(4).forEach { print("$it ") }
}

fun lazys()
{
    val lazySeq = buildSequence {
        yield(0)
        yieldAll(1..10)
    }

    lazySeq.forEach { print("$it ") }
}