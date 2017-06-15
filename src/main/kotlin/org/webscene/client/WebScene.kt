package org.webscene.client

import org.w3c.dom.Element
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import org.w3c.notifications.Notification
import org.w3c.notifications.NotificationOptions
import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType
import org.webscene.client.dom.*
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.HtmlTag
import org.webscene.client.html.InputType
import org.webscene.client.html.ParentHtmlElement
import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row
import org.webscene.client.http.ContentType
import org.webscene.client.http.HttpMethod
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.json

/**
 * Provides general web view functionality.
 * @author Nick Apperley
 */
@Suppress("unused")
object WebScene {
    /**
     * Contains common functionality for doing a DOM query.
     */
    object DomQuery {
        /**
         * Retrieves all DOM elements that match [tagName].
         * @param tagName Name of the tag.
         * @return A list of all DOM elements that match [tagName].
         */
        fun allElementsByTagName(tagName: String) = document.findAllElementsByTagName(tagName)

        /**
         * Retrieves the [DOM element][Element] that matches [id].
         * @param id Unique identifier for the [DOM element][Element].
         * @return A [DOM element][Element] if there is a match.
         */
        fun elementById(id: String): Element? = document.findElementById(id)

        /**
         * Obtains the unique identifier for the web page (pageId).
         * @return The page ID if it is found otherwise a empty [String].
         */
        fun pageId(): String {
            val metaElements = DomQuery.allElementsByTagName("meta").filter { it.hasAttribute("pageId") }

            return if (metaElements.isNotEmpty()) metaElements[0].getAttribute("pageId") ?: "" else ""
        }

        /**
         * Retrieves all DOM [elements][Element] that match one or more [class names][classNames].
         * @param classNames One or more names of classes.
         * @return A list of all [DOM elements][Element] that match [classNames].
         */
        fun allElementsByClassNames(vararg classNames: String) = document.findAllElementsByClassNames(*classNames)
    }

    /**
     * Contains common functionality for editing the DOM.
     */
    object DomEditor {
        /**
         * Prepends a [DOM element][Element] to the **body** element.
         * @param domElement The DOM element to prepend.
         */
        fun prependElementToBody(domElement: Element) = document.prependElementToBody(domElement)

        /**
         * Prepends a [DOM element][Element] to the **head** element.
         * @param domElement The DOM element to prepend.
         */
        fun prependElementToHead(domElement: Element) = document.prependElementToHead(domElement)

        /**
         * Appends a [DOM element][Element] to the **head** element.
         * @param domElement The DOM element to append.
         */
        fun appendElementToHead(domElement: Element) = document.appendElementToHead(domElement)

        /**
         * Updates an existing DOM element by its ID.
         * @param block Function for updating the [HTML element][HtmlTag] which updates the DOM element.
         */
        fun updateElementById(block: () -> HtmlTag) = document.updateElementById(block)

        /**
         * Removes an existing DOM element by its ID.
         * @param id Unique identifier of the DOM element.
         */
        fun removeElementById(id: String) = document.removeElementById(id)
    }

    /**
     * Contains common functionality for Bootstrap (CSS framework).
     */
    object Bootstrap {
        /**
         * Creates a new [container][Container] that holds one or more rows.
         * @param init Initialisation block for setting up the [container][Container].
         * @return A new [Container].
         */
        fun container(init: Container.() -> Unit) = createBootstrapContainer(init)

        /**
         * Creates a new [row][Row] that holds one or more columns.
         * @param init Initialisation block for setting up the row.
         * @return A new [Row].
         */
        fun row(init: Row.() -> Unit) = createBootstrapRow(init)

        /**
         * Creates a new [column][Column] that can contain HTML elements.
         * @param colSizes One or more column sizes to use for sizing the column.
         * @param init Initialisation block for setting up the column.
         * @return A new [Column].
         */
        fun column(vararg colSizes: Pair<ColumnSize, Int>, init: Column.() -> Unit) =
            createBootstrapColumn(colSizes = arrayOf(*colSizes), init = init)
    }

    /**
     * Creates a new parent HTML element that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    fun parentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit) = createParentHtmlElement(tagName, init)

    /**
     * Creates a new HTML element which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    fun htmlElement(tagName: String, init: HtmlElement.() -> Unit) = createHtmlElement(tagName, init)

    /**
     * Creates a new HTML **form** element that can contain child HTML elements.
     * @param action Relative URL to the REST resource.
     * @param init Initialisation block for setting up the form.
     * @return A new HTML **form** element.
     */
    fun htmlForm(action: String, method: HttpMethod, init: ParentHtmlElement.() -> Unit): ParentHtmlElement =
        createHtmlForm(action = action, method = method, init = init)

    /**
     * Creates a new HTML **span** element that contains a **input** and **datalist** element.
     * @param listValues One or more values that populate the **datalist** element.
     * @param groupId The ID to use for the **span** element that is grouping everything together.
     * @param listId The ID to use for the **datalist** element.
     * @param inputId The ID to use for the **input** element.
     * @param inputName Name of the **input** element which is used for accessing data in a **form** element.
     * @return A new HTML **span** element.
     */
    fun htmlDataList(
        vararg listValues: String,
        groupId: String = "",
        listId: String,
        inputId: String = "",
        inputName: String
    ): ParentHtmlElement = createHtmlDataList(
        groupId = groupId,
        inputId = inputId,
        inputName = inputName,
        listId = listId,
        listValues = *listValues
    )

    /**
     * Creates a new HTML **input** element that doesn't contain any HTML elements.
     * @param type The type of **input** element to use (eg [InputType.TEXT]).
     * @param disabled If set to true then the element is disabled.
     * @param readOnly If set to true then the element can only be read.
     * @param autoFocus If set to true then the element gains focus after the web page loads.
     * @param name Unique name of the input. An HTML form uses [name] as a field name.
     * @param init Initialisation block for setting up the input.
     * @return A new HTML **input** element.
     */
    fun htmlInput(
        type: InputType,
        disabled: Boolean = false,
        readOnly: Boolean = false,
        autoFocus: Boolean = false,
        name: String = "",
        init: HtmlElement.() -> Unit
    ): HtmlElement = createHtmlInput(
        type = type,
        disabled = disabled,
        readOnly = readOnly,
        autoFocus = autoFocus,
        name = name,
        init = init
    )

    /**
     * Converts Kotlin objects to JSON text.
     * @param pairs One or more [Pair] objects consisting of *[Key][String], Any*.
     * @return JSON as a [String] if [pairs] isn't empty otherwise a empty [String].
     */
    fun objectsToJsonText(vararg pairs: Pair<String, *>) = if (pairs.isNotEmpty()) JSON.stringify(json(*pairs)) else ""

    /**
     * Creates a [HTTP/S client][XMLHttpRequest] to enable communication with a HTTP/S server. The request
     * header **content-type** is set to **JSON**.
     * @param method [HTTP/S method][HttpMethod] to use.
     * @param url Specific HTTP/S path to use.
     * @param reqData Request data (an array of [Pair]) to send to the server.
     * @param sendNow The client communicates immediately with the server if set to **true**.
     * @param init Initialisation block for setting up the [HTTP/S client][XMLHttpRequest].
     * @return A new [HTTP/S client][XMLHttpRequest].
     */
    fun httpClient(
        method: HttpMethod,
        url: String,
        reqData: Array<Pair<String, *>> = arrayOf(),
        sendNow: Boolean = false,
        init: XMLHttpRequest.() -> Unit
    ): XMLHttpRequest {
        val client = XMLHttpRequest()

        client.open(method = method.txt, url = url)
        client.init()
        client.setRequestHeader("content-type", XMLHttpRequestResponseType.JSON.toString())
        if (sendNow && reqData.isEmpty()) client.send()
        else if (sendNow && reqData.isNotEmpty()) client.send(objectsToJsonText(*reqData))
        return client
    }

    /**
     * Obtains data from a HTTP/S server. If the **content-type** request header isn't set then it will default to
     * [plain text][ContentType.PLAIN_TEXT].
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
        val request = RequestInit(method = method.txt, body = body, headers = reqHeaders)

        if (!reqHeaders.has("content-type")) reqHeaders.append("content-type", ContentType.PLAIN_TEXT.txt)
        window.fetch(input = url, init = request).then(onResponse, onError)
    }

    /**
     * Creates a [notification][Notification] which will be displayed if the user has granted permission.
     * @param title Name to use in the notification.
     * @param options The [options][NotificationOptions] to use for the notification.
     * @return A [Notification] object if the user has granted permission, otherwise null is returned.
     */
    fun notification(title: String, options: NotificationOptions): Notification? {
        var result: Notification? = null

        Notification.requestPermission { status ->
            if (status.toString() == NotificationPermission.GRANTED.txt) result =
                Notification(title = title, options = options)
        }
        return result
    }
}