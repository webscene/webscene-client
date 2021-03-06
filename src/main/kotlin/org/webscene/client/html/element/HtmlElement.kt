package org.webscene.client.html.element

import org.w3c.dom.Element
import org.webscene.client.html.HtmlTag
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.appendText
import kotlin.dom.createElement

/**
 * Basic HTML element which doesn't contain any children.
 * @author Nick Apperley
 */
open class HtmlElement : HtmlTag {
    override var id = ""
    /** Contains unique class names. **/
    val classes = mutableSetOf<String>()
    override val attributes = mutableMapOf<String, String>()
    override lateinit var tagName: String
    override var isClosed = false
    override var txtContent = ""

    private fun createAttributes() = buildString {
        if (attributes.isNotEmpty()) {
            append(" ")
            attributes.keys.forEach { append("$it = \"${attributes[it]}\" ") }
        }
    }

    private fun createClasses() = buildString {
        if (classes.isNotEmpty()) append(" class = \"${classes.joinToString(" ")}\"")
    }

    override fun toDomElement(): Element {
        val tmpAttributes = attributes
        val tmpId = id

        return document.createElement(tagName) {
            addClass(*classes.toTypedArray())
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            if (txtContent.isNotEmpty()) appendText(txtContent)
        }
    }

    override fun createText(indent: Int): String = buildString {
        (1..indent).forEach { append(" ") }
        append("<$tagName${createClasses()}${createAttributes()}")
        if (isClosed) append(" />") else append(">$txtContent</$tagName>")
    }

    /**
     * Changes the [text][txtContent] to include in the HTML element.
     */
    operator fun String.unaryPlus() {
        txtContent = this
    }
}