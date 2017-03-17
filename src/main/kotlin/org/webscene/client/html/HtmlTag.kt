package org.webscene.client.html

import org.w3c.dom.Element

/**
 * Base for a HTML tag.
 * @author Nick Apperley
 */
interface HtmlTag {
    /**
     * Unique identifier for the HTML tag.
     */
    var id: String
    /**
     * Name of the tag.
     */
    var tagName: String
    /**
     * Multiple key/value entries that may be included in the HTML tag.
     */
    val attributes: MutableMap<String, String>
    /**
     * If true then there is only one tag for the element, two otherwise.
     */
    var isClosed: Boolean
    /**
     * Ordinary text (doesn't include HTML tags).
     */
    var txtContent: String

    /**
     * Creates a text representation of HTML.
     * @param indent Number of spaces to use for indenting HTML elements.
     * @return HTML as a [String].
     */
    fun createText(indent: Int = 2): String

    /**
     * Converts to a [DOM element][Element].
     * @return A new [DOM element][Element].
     */
    fun toDomElement(): Element
}