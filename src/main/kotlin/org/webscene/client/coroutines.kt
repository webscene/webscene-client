@file:Suppress("unused")

package org.webscene.client

import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.CoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.js.Promise

// Provides coroutines support for the web client. Based on the coroutines implementation in the Kotlin Fullstack
// sample:
//     https://github.com/Kotlin/kotlin-fullstack-sample/blob/master/frontend/src/org/jetbrains/common/coroutines.kt
// Author - Nick Apperley

/**
 * Waits for a result to be returned from a [Promise].
 * @param T Result type.
 */
suspend fun <T> Promise<T>.await() = suspendCoroutine<T> { cont ->
    then({ value -> cont.resume(value) },
        { exception -> cont.resumeWithException(exception) })
}

/**
 * Allows work to be done asynchronously. Consider using [launch] function instead.
 * @param T Type of result returned from the work that is done.
 * @param block A lambda containing the work to be done.
 * @return A JS [Promise].
 */
fun <T> async(block: suspend () -> T): Promise<T> = Promise { resolve, reject ->
    block.startCoroutine(object : Continuation<T> {
        override val context: CoroutineContext get() = EmptyCoroutineContext
        override fun resume(value: T) {
            resolve(value)
        }

        override fun resumeWithException(exception: Throwable) {
            reject(exception)
        }
    })
}

/**
 * Does some work which will be non blocking, aka *fire and forget*.
 * @param block A lambda containing the work to be done.
 */
fun launch(block: suspend () -> Unit) {
    async(block).catch { exception -> console.log("Failed with $exception") }
}
