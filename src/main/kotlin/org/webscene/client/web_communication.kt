@file:Suppress("unused")

package org.webscene.client

import org.w3c.dom.events.Event
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import org.w3c.notifications.Notification
import org.w3c.notifications.NotificationOptions
import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType
import kotlin.browser.window
import kotlin.js.json

// Provides web communication functionality, including notifications.
// Author - Nick Apperley

/**
 * Converts Kotlin objects to JSON text.
 * @param pairs One or more [Pair] objects consisting of *[Key][String], Any*.
 * @return JSON as a [String] if [pairs] isn't empty otherwise a empty [String].
 */
fun objectsToJson(vararg pairs: Pair<String, *>) = if (pairs.isNotEmpty()) JSON.stringify(json(*pairs)) else ""

/**
 * Creates a [HTTP/S client][XMLHttpRequest] to enable communication with a HTTP/S server via the
 * [XMLHttpRequest API](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest). The request header
 * **content-type** is set to **JSON**.
 * @param method [HTTP/S method][HttpMethod] to use.
 * @param url Specific HTTP/S path to use.
 * @param reqData Request data (an array of [Pair]) to send to the server.
 * @param sendNow The client communicates immediately with the server if set to **true**.
 * @param block Initialisation block for setting up the [HTTP/S client][XMLHttpRequest].
 * @return A new [HTTP/S client][XMLHttpRequest].
 */
fun httpClient(
    method: HttpMethod,
    url: String,
    reqData: Array<Pair<String, *>> = arrayOf(),
    sendNow: Boolean = false,
    block: XMLHttpRequest.() -> Unit
): XMLHttpRequest {
    val client = XMLHttpRequest()

    client.open(method = method.name, url = url)
    client.block()
    client.setRequestHeader("content-type", XMLHttpRequestResponseType.JSON.toString())
    if (sendNow && reqData.isEmpty()) client.send()
    else if (sendNow && reqData.isNotEmpty()) client.send(objectsToJson(*reqData))
    return client
}

/**
 * Obtains data from a HTTP/S server via the [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API).
 * If the **content-type** request header isn't set then it will default to plain text type (**text/plain**).
 * @param url Specific HTTP/S path to use.
 * @param method [HTTP/S method][HttpMethod] to use.
 * @param body The data to send in the HTTP/S request.
 * @param reqHeaders The [reqHeaders][Headers] to use in the HTTP/S request.
 * @param onResponse Callback to use when there is a response from the HTTP/S server.
 * @param onError Callback to use when a error has occurred.
 */
fun <R : Any?> fetchData(
    url: String,
    method: HttpMethod,
    body: Any = "",
    onResponse: (Response) -> R,
    onError: (Throwable) -> R,
    reqHeaders: Headers = Headers()
) {
    val request = RequestInit(method = method.name, body = body, headers = reqHeaders)
    val plainTxtType = "text/plain"

    if (!reqHeaders.has("content-type")) reqHeaders.append("content-type", plainTxtType)
    window.fetch(input = url, init = request).then(onResponse, onError)
}

/**
 * Creates a [notification][Notification] which will be displayed if the user has granted permission.
 * @param title Name to use in the notification.
 * @param options The [options][NotificationOptions] to use for the notification.
 * @param onClick Callback to use when a user has clicked on a notification.
 * @return A [Notification] object if the user has granted permission, otherwise null is returned.
 */
fun notification(
    title: String,
    options: NotificationOptions,
    onClick: (Event) -> Unit = {}
): Notification? {
    var result: Notification? = null

    Notification.requestPermission { status ->
        if (status.toString() == NotificationPermission.GRANTED.txt) result =
            Notification(title = title, options = options).apply { onclick = onClick }
    }
    return result
}