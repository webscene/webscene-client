package org.webscene.client

import org.w3c.dom.Element
import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType
import org.webscene.client.http.HttpMethod
import org.webscene.client.dom.*
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.HtmlTag
import org.webscene.client.html.ParentHtmlElement
import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row
import kotlin.browser.document
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
         * @param init Initialisation block for updating the [HTML element][HtmlTag] which updates the DOM element.
         */
        fun updateElementById(init: () -> HtmlTag) = document.updateElementById(init)

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
        fun column(vararg colSizes: Pair<ColumnSize, Int>, init: Column.() -> Unit): Column {
            val tmp = arrayOf(*colSizes)

            return createBootstrapColumn(tmp, init)
        }
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
    fun httpClient(method: HttpMethod,
                   url: String,
                   reqData: Array<Pair<String, *>> = arrayOf(),
                   sendNow: Boolean = false,
                   init: XMLHttpRequest.() -> Unit): XMLHttpRequest {
        val client = XMLHttpRequest()

        client.open(method = method.txt, url = url)
        client.init()
        client.setRequestHeader("content-type", XMLHttpRequestResponseType.JSON.toString())
        if (sendNow && reqData.isEmpty()) client.send()
        else if (sendNow && reqData.isNotEmpty()) client.send(objectsToJsonText(*reqData))
        return client
    }
}