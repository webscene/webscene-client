@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.asList
import org.webscene.client.html.HtmlSection
import org.webscene.client.html.HtmlTag
import kotlin.browser.document

// Extensions for org.w3c.dom.Document.
// Author - Nick Apperley

internal fun Document.replaceElement(block: () -> HtmlTag) {
    val htmlTag = block()
    val domElement = document.findElementById(htmlTag.id)

    domElement?.replaceWith(htmlTag.toDomElement())
}

/**
 * Removes an existing DOM element. The element **MUST** have its ID attribute set.
 * @param domElement The [element][Element] to remove from the DOM.
 */
internal fun Document.removeElement(domElement: Element) = document.removeElementById(domElement.id)

/**
 * Removes an existing DOM element from a [section][htmlSection]. The element **MUST** have its ID attribute set.
 * @param domElement The [element][Element] to remove from the DOM.
 * @param htmlSection The HTML section to use.
 */
internal fun Document.removeElement(domElement: Element, htmlSection: HtmlSection) {
    when (htmlSection) {
        HtmlSection.HEAD -> document.head?.removeChild(domElement)
        else -> document.body?.removeChild(domElement)
    }
}

internal fun Document.removeElementById(id: String) {
    val domElement = document.findElementById(id)

    domElement?.remove()
}

/**
 * Prepends a [DOM element][Element] to a [HTML section][htmlSection].
 * @param domElement The DOM element to prepend.
 * @param htmlSection The HTML section to use.
 */
internal fun Document.prependElement(domElement: Element, htmlSection: HtmlSection) {
    when (htmlSection) {
        HtmlSection.HEAD -> document.head?.prepend(domElement)
        else -> document.body?.prepend(domElement)
    }
}

/**
 * Appends a [DOM element][Element] to a [HTML section][htmlSection].
 * @param domElement The DOM element to append.
 * @param htmlSection The HTML section to use.
 */
internal fun Document.appendElement(domElement: Element, htmlSection: HtmlSection) {
    when (htmlSection) {
        HtmlSection.HEAD -> document.head?.append(domElement)
        else -> document.body?.append(domElement)
    }
}

internal fun Document.findAllElementsByTagName(tagName: String) = document.getElementsByTagName(tagName).asList()

internal fun Document.findElementById(id: String): Element? = document.getElementById(id)

internal fun Document.findAllElementsByClassNames(vararg classNames: String) = document.getElementsByClassName(
        classNames.joinToString(separator = " ")).asList()
