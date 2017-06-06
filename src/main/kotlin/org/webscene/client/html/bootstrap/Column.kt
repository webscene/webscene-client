package org.webscene.client.html.bootstrap

import org.w3c.dom.Element
import org.webscene.client.html.ParentHtmlElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.createElement

/**
 * Bootstrap Column element.
 * @author Nick Apperley
 * @see org.webscene.client.html.ParentHtmlElement
 */
class Column : ParentHtmlElement() {
    /**
     * Contains column sizes. Each entry is [column size type][ColumnSize] (key), span size (value).
     */
    val colSizes = mutableMapOf<ColumnSize, Int>()
    override var tagName
        get() = "div"
        set(value) {}

    override fun toDomElement(): Element {
        val tmpId = id
        val tmpAttributes = attributes
        val tmpChildren = children

        return document.createElement(tagName) {
            addClass(*colSizes.map { (key, value) -> "col-${key.txt}-$value" }.toTypedArray())
            addClass(*classes.toTypedArray())
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            tmpChildren.forEach { child ->
                appendChild(child.toDomElement())
            }
        }
    }
}