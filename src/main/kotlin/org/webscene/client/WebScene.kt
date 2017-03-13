package org.webscene.client

import org.w3c.xhr.JSON
import org.w3c.xhr.XMLHttpRequest
import org.w3c.xhr.XMLHttpRequestResponseType
import org.webscene.client.comms.HttpMethod
import org.webscene.client.dom.findAllNodesByName
import org.webscene.client.dom.findAllElementsByTagName
import org.webscene.client.dom.findElementById
import org.webscene.client.dom.updateElementById
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.HtmlTag
import org.webscene.client.html.ParentHtmlElement
import kotlin.browser.document
import kotlin.js.json

@Suppress("unused")
/**
 * Provides general web view functionality.
 * @author Nick Apperley
 */
object WebScene {
    object DomQuery {
        /**
         * Retrieves all DOM elements that match [tagName].
         * @param tagName Name of the tag.
         * @return A list of all DOM elements that match [tagName].
         */
        fun allElementsByTagName(tagName: String) = document.findAllElementsByTagName(tagName)

        /**
         * Retrieves all DOM [nodes][org.w3c.dom.Node] that match the [name attribute][name].
         * @param name Value of the name attribute.
         * @return A list of all DOM [nodes][org.w3c.dom.Node] that match the [name attribute][name].
         */
        fun allNodesByName(name: String) = document.findAllNodesByName(name)

        /**
         * Retrieves the [DOM element][org.w3c.dom.Element] that matches [id].
         * @param id Unique identifier for the [DOM element][org.w3c.dom.Element].
         * @return A [DOM element][org.w3c.dom.Element] if there is a match.
         */
        fun elementById(id: String) = document.findElementById(id)

        /**
         * Obtains the unique identifier for the web page (pageId).
         * @return The page ID if it is found otherwise a empty [String].
         */
        fun pageId(): String {
            val metaElements = DomQuery.allElementsByTagName("meta").filter { it.hasAttribute("pageId") }

            return if (metaElements.isNotEmpty()) metaElements[0].getAttribute("pageId") ?: "" else ""
        }
    }

    /**
     * Creates a new parent HTML element that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    fun parentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit): ParentHtmlElement {
        val parentHtmlElement = ParentHtmlElement()

        parentHtmlElement.tagName = tagName
        parentHtmlElement.init()
        return parentHtmlElement
    }

    /**
     * Creates a new HTML element which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    fun htmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
        val htmlElement = HtmlElement()

        htmlElement.tagName = tagName
        htmlElement.init()
        return htmlElement
    }

    /**
     * Update an existing DOM element by its ID.
     * @param init Initialisation block for updating the [HTML element][HtmlTag] which updates the DOM element.
     */
    fun updateDomElementById(init: () -> HtmlTag) = document.updateElementById(init)

    /**
     * Converts Kotlin objects to JSON text.
     * @param pairs One or more [Pair] objects consisting of *[Key][String], Any*.
     * @return JSON as a [String] if [pairs] isn't empty otherwise a empty [String].
     */
    fun objectsToJsonText(vararg pairs: Pair<String, *>) = if (pairs.isNotEmpty()) JSON.stringify(json(*pairs)) else ""

    /**
     * Creates a [HTTP/S client][XMLHttpRequest] to enable communication with a HTTP/S server.
     * @param method [HTTP method][HttpMethod] to use.
     * @param url Specific HTTP/S path to use.
     * @param contentType Type of content to use for both the HTTP/S request and response.
     * @param sendNow The client communicates immediately with the server if set to **true**.
     * @param init Initialisation block for setting up the [HTTP client][XMLHttpRequest].
     * @return A new [HTTP client][XMLHttpRequest].
     */
    fun httpClient(method: HttpMethod,
                   url: String,
                   contentType: XMLHttpRequestResponseType = XMLHttpRequestResponseType.JSON,
                   sendNow: Boolean = false,
                   init: XMLHttpRequest.() -> Unit): XMLHttpRequest {
        val client = XMLHttpRequest()

        client.init()
        client.setRequestHeader("content-type", contentType.toString())
        client.open(method = method.txt, url = url)
        if (sendNow) client.send()
        return client
    }
}