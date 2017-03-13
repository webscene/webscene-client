package org.webscene.client

import org.webscene.client.dom.findAllElementsByName
import org.webscene.client.dom.findElementById
import org.webscene.client.dom.updateElementById
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.HtmlTag
import org.webscene.client.html.ParentHtmlElement
import kotlin.browser.document

/**
 * Provides general web view functionality.
 * @author Nick Apperley
 */
object WebScene {
    /**
     * Creates a new parent HTML element that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
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
     */
    fun htmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
        val htmlElement = HtmlElement()

        htmlElement.tagName = tagName
        htmlElement.init()
        return htmlElement
    }

    /**
     * Retrieves all DOM elements that match [tagName].
     * @param tagName Name of the tag.
     * @return A list of all DOM elements that match [tagName].
     */
    fun findAllDomElementsByName(tagName: String) = document.findAllElementsByName(tagName)

    /**
     * Retrieves the [DOM element][org.w3c.dom.Element] that matches [id].
     * @param id Unique identifier for the [DOM element][org.w3c.dom.Element].
     * @return A [DOM element][org.w3c.dom.Element] if there is a match.
     */
    fun findDomElementById(id: String) = document.findElementById(id)

    /**
     * Update an existing DOM element by its ID.
     * @param init Initialisation block for updating the [HTML element][HtmlTag] which updates the DOM element.
     */
    fun updateDomElementById(init: () -> HtmlTag) = document.updateElementById(init)

    /**
     * Obtains the unique identifier for the web page (pageId).
     * @return The page ID if it is found otherwise a empty [String].
     */
    fun pageId(): String {
        val metaElements = findAllDomElementsByName("meta").filter { it.hasAttribute("pageId") }

        return if (metaElements.isNotEmpty()) metaElements[0].getAttribute("pageId") ?: "" else ""
    }
}