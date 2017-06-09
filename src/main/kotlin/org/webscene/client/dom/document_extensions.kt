@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.asList
import org.webscene.client.html.HtmlTag
import kotlin.browser.document

// Extensions for org.w3c.dom.Document.
// Author - Nick Apperley

/**
 * Updates an existing DOM element by its ID.
 * @param init Initialisation block for updating the [HTML element][HtmlTag] which updates the
 * [DOM element][Element].
 */
internal fun Document.updateElementById(init: () -> HtmlTag) {
    val htmlTag = init()
    val domElement = document.findElementById(htmlTag.id)

    domElement?.replaceWith(htmlTag.toDomElement())
}

/**
 * Removes an existing DOM element by its ID.
 * @param id Unique identifier of the DOM element.
 */
internal fun Document.removeElementById(id: String) {
    val domElement = document.findElementById(id)

    domElement?.remove()
}

/**
 * Prepends a [DOM element][Element] to the **body** element.
 * @param domElement The DOM element to prepend.
 */
internal fun Document.prependElementToBody(domElement: Element) {
    document.body?.prepend(domElement)
}

/**
 * Prepends a [DOM element][Element] to the **head** element.
 * @param domElement The DOM element to prepend.
 */
internal fun Document.prependElementToHead(domElement: Element) {
    document.head?.prepend(domElement)
}

/**
 * Appends a [DOM element][Element] to the **head** element.
 * @param domElement The DOM element to append.
 */
internal fun Document.appendElementToHead(domElement: Element) {
    document.head?.append(domElement)
}

/**
 * Retrieves all DOM [elements][Element] that match [tagName].
 * @param tagName Name of the tag.
 * @return A list of all [DOM elements][Element] that match [tagName].
 */
internal fun Document.findAllElementsByTagName(tagName: String) = document.getElementsByTagName(tagName).asList()

/**
 * Retrieves the [DOM element][Element] that matches [id].
 * @param id Unique identifier for the [DOM element][Element].
 * @return A [DOM element][Element] if there is a match.
 */
internal fun Document.findElementById(id: String): Element? = document.getElementById(id)

/**
 * Retrieves all DOM [elements][Element] that match one or more [class names][classNames].
 * @param classNames One or more names of classes.
 * @return A list of all [DOM elements][Element] that match [classNames].
 */
internal fun Document.findAllElementsByClassNames(vararg classNames: String) = document.getElementsByClassName(
        classNames.joinToString(separator = " ")).asList()
