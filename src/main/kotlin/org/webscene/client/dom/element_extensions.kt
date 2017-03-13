@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.Element
import org.w3c.dom.asList
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.ParentHtmlElement

// Extensions for org.w3c.dom.Element.
// Author - Nick Apperley

/**
 * Removes all HTML attributes from the [DOM element][org.w3c.dom.Element].
 */
fun Element?.clearAttributes() {
    val oldAttrNames = mutableListOf<String>()

    this?.attributes?.asList()?.forEach { oldAttrNames.add(it.name) }
    oldAttrNames.forEach { this?.attributes?.removeNamedItem(it) }
}

/**
 * Appends a [parent HTML element][ParentHtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun Element?.appendParentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.init()
    this?.append(parentHtmlElement.toDomElement())
}

/**
 * Prepends a [parent HTML element][ParentHtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun Element?.prependParentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.init()
    this?.prepend(parentHtmlElement.toDomElement())
}

/**
 * Appends a [HTML element][HtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [HTML element][HtmlElement].
 */
fun Element?.appendHtmlElement(tagName: String, init: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.init()
    this?.append(htmlElement.toDomElement())
}

/**
 * Prepends a [HTML element][HtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [HTML element][HtmlElement].
 */
fun Element?.prependHtmlElement(tagName: String, init: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.init()
    this?.prepend(htmlElement.toDomElement())
}