@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.HTMLElement
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.ParentHtmlElement

// Extensions for the org.w3c.dom.HTMLElement.
// Author - Nick Apperley

/**
 * Prepends a [parent HTML element][ParentHtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun HTMLElement?.prependParentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.init()
    this?.prepend(parentHtmlElement.toDomElement())
}

/**
 * Appends a [parent HTML element][ParentHtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun HTMLElement?.appendParentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.init()
    this?.append(parentHtmlElement.toDomElement())
}

/**
 * Prepends a [HTML element][org.webscene.client.html.HtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [HTML element][org.webscene.client.html.HtmlElement]
 */
fun HTMLElement?.prependHtmlElement(tagName: String, init: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.init()
    this?.prepend(htmlElement.toDomElement())
}

/**
 * Appends a [HTML element][org.webscene.client.html.HtmlElement] to this [DOM HTML element][org.w3c.dom.HTMLElement].
 * @param tagName Name of the tag.
 * @param init Initialisation block for setting up the [HTML element][org.webscene.client.html.HtmlElement]
 */
fun HTMLElement?.appendHtmlElement(tagName: String, init: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.init()
    this?.append(htmlElement.toDomElement())
}