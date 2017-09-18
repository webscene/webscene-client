package org.webscene.client.html.bootstrap

import org.w3c.dom.Element
import org.webscene.client.html.ParentHtmlElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.createElement

/**
 * Bootstrap Row element.
 * @author Nick Apperley
 * @see org.webscene.client.html.ParentHtmlElement
 */
class Row : ParentHtmlElement() {
    override var tagName
        get() = "div"
        set(value) {}

    /**
     * Creates a new [column][Column] in [Row] that can contain HTML elements.
     * @param colSizes One or more column sizes to use for sizing the column.
     * @param block Initialisation block for setting up the column.
     * @return A new [Column].
     */
    @Suppress("unused")
    fun column(vararg colSizes: Pair<ColumnSize, Int>, block: Column.() -> Unit): Column {
        val colElement = Column()

        colSizes.forEach { colElement.colSizes[it.first] = it.second }
        children.add(colElement)
        colElement.block()
        return colElement
    }

    override fun toDomElement(): Element {
        val tmpId = id
        val tmpAttributes = attributes
        val tmpChildren = children

        return document.createElement(tagName) {
            addClass("row", *classes.toTypedArray())
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            tmpChildren.forEach { child ->
                appendChild(child.toDomElement())
            }
        }
    }
}