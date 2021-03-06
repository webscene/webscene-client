@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.HTMLElement
import org.webscene.client.html.element.HtmlElement
import org.webscene.client.html.element.ParentHtmlElement

// Extensions for org.w3c.dom.HTMLElement.
// Author - Nick Apperley

/**
 * Prepends a [parent HTML element][ParentHtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun HTMLElement?.prependParentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.block()
    this?.prepend(parentHtmlElement.toDomElement())
}

/**
 * Appends a [parent HTML element][ParentHtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun HTMLElement?.appendParentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.block()
    this?.append(parentHtmlElement.toDomElement())
}

/**
 * Prepends a [HTML element][org.webscene.client.html.element.HtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [HTML element][org.webscene.client.html.element.HtmlElement]
 */
fun HTMLElement?.prependHtmlElement(tagName: String, block: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.block()
    this?.prepend(htmlElement.toDomElement())
}

/**
 * Appends a [HTML element][org.webscene.client.html.element.HtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [HTML element][org.webscene.client.html.element.HtmlElement]
 */
fun HTMLElement?.appendHtmlElement(tagName: String, block: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.block()
    this?.append(htmlElement.toDomElement())
}