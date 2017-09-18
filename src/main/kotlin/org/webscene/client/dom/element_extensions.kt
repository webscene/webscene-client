@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.Element
import org.w3c.dom.asList
import org.w3c.dom.get
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.ParentHtmlElement

// Extensions for org.w3c.dom.Element.
// Author - Nick Apperley

/**
 * Removes all HTML attributes from the [DOM element][org.w3c.dom.Element].
 */
fun Element?.clearAttributes() {
    this?.attributes?.asList()?.map { it.name }?.forEach { this.attributes.removeNamedItem(it) }
}

/**
 * Appends a [parent HTML element][ParentHtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun Element?.appendParentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.block()
    this?.append(parentHtmlElement.toDomElement())
}

/**
 * Prepends a [parent HTML element][ParentHtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [parent HTML element][ParentHtmlElement].
 */
fun Element?.prependParentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit) {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.block()
    this?.prepend(parentHtmlElement.toDomElement())
}

/**
 * Appends a [HTML element][HtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [HTML element][HtmlElement].
 */
fun Element?.appendHtmlElement(tagName: String, block: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.block()
    this?.append(htmlElement.toDomElement())
}

/**
 * Prepends a [HTML element][HtmlElement] to this [DOM element][org.w3c.dom.Element].
 * @param tagName Name of the tag.
 * @param block Initialisation block for setting up the [HTML element][HtmlElement].
 */
fun Element?.prependHtmlElement(tagName: String, block: HtmlElement.() -> Unit) {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.block()
    this?.prepend(htmlElement.toDomElement())
}

/**
 * Changes the focus to the DOM element.
 */
fun Element?.focus() {
    if (this?.localName == "input") this.asDynamic().focus()
}

val Element?.textBoxValue
    get() = if (this?.localName == "input" && this.attributes["type"]?.value == "text") {
        this.asDynamic().value.toString()
    } else {
        ""
    }

fun Element?.textBoxValue(txt: String) {
    if (this?.localName == "input" && this.attributes["type"]?.value == "text") {
        this.asDynamic().value = txt
    }
}

val Element?.textAreaValue
    get() = if (this?.localName == "textarea") this.asDynamic().value.toString() else ""

fun Element?.textAreaValue(txt: String) {
    if (this?.localName == "textarea") this.asDynamic().value = txt
}