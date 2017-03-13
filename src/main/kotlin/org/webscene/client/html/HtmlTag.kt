package org.webscene.client.html

import org.w3c.dom.Element

/**
 * Base for a HTML tag.
 * @author Nick Apperley
 */
interface HtmlTag {
    var id: String
    var tagName: String
    val attributes: MutableMap<String, String>
    /**
     * If true then there is only one tag for the element, two otherwise.
     */
    var isClosed: Boolean
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