package org.webscene.client.html

import org.w3c.dom.Element

/**
 * Base for a tag element.
 */
interface Tag {
    var id: String
    var tagName: String
    val attributes: MutableMap<String, String>
    var isClosed: Boolean
    var txtContent: String

    fun createText(indent: Int = 2): String

    fun toDomElement(): Element
}