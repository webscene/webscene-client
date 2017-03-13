@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.Document
import org.w3c.dom.asList
import org.webscene.client.html.HtmlTag
import kotlin.browser.document

// Extensions for org.w3c.dom.Document.
// Author - Nick Apperley

/**
 * Update an existing DOM element by its ID.
 * @param init Initialisation block for updating the [HTML element][HtmlTag] which updates the DOM element.
 */
fun Document.updateElementById(init: () -> HtmlTag) {
    val tag = init()
    val domElement = document.getElementById(tag.id)

    domElement?.replaceWith(tag.toDomElement())
}

/**
 * Retrieves all DOM elements that match [tagName].
 * @param tagName Name of the tag.
 * @return A list of all DOM elements that match [tagName].
 */
fun Document.findAllElementsByName(tagName: String) = document.getElementsByTagName(tagName).asList()

/**
 * Retrieves the [DOM element][org.w3c.dom.Element] that matches [id].
 * @param id Unique identifier for the [DOM element][org.w3c.dom.Element].
 * @return A [DOM element][org.w3c.dom.Element] if there is a match.
 */
fun Document.findElementById(id: String) = document.getElementById(id)