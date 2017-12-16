package org.webscene.client.dom

import org.w3c.dom.Element
import kotlin.browser.document

@Suppress("unused")
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
        val metaElements = DomQuery.allElementsByTagName("meta").filter { element ->
            element.hasAttribute("pageId")
        }

        return if (metaElements.isNotEmpty()) metaElements[0].getAttribute("pageId") ?: "" else ""
    }

    /**
     * Retrieves all DOM [elements][Element] that match one or more [class names][classNames].
     * @param classNames One or more names of classes.
     * @return A list of all [DOM elements][Element] that match [classNames].
     */
    fun allElementsByClassNames(vararg classNames: String) = document.findAllElementsByClassNames(*classNames)
}