package org.webscene.client.html.bootstrap

import org.w3c.dom.Element
import org.webscene.client.html.ParentHtmlElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.createElement

/**
 * Bootstrap Container element.
 * @author Nick Apperley
 * @see org.webscene.client.html.ParentHtmlElement
 */
class Container : ParentHtmlElement() {
    override var tagName
        get() = "div"
        set(value) {}
    /** [Container] uses the full width of the screen if set to true. **/
    var fullWidth = true

    /**
     * Creates a new [row][Row] in [Container] that holds one or more columns.
     * @param init Initialisation block for setting up the row.
     * @return A new [Row].
     */
    @Suppress("unused")
    fun row(init: Row.() -> Unit): Row {
        val rowElement = Row()

        children.add(rowElement)
        rowElement.init()
        return rowElement
    }

    override fun toDomElement(): Element {
        val tmpId = id
        val tmpAttributes = attributes
        val tmpChildren = children

        return document.createElement(tagName) {
            addClass(*classes.toTypedArray())
            if (fullWidth) className += "container-fluid" else className += " container"
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            tmpChildren.forEach { child ->
                appendChild(child.toDomElement())
            }
        }
    }
}