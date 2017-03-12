package org.webscene.client.html

import org.w3c.dom.Element
import kotlin.browser.document
import kotlin.dom.appendText
import kotlin.dom.createElement

/**
 * Creates a HTML element.
 */
open class HtmlElement : Tag {
    override var id = ""
    val classes = mutableListOf<String>()
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
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            if (txtContent.isNotEmpty()) appendText(txtContent)
        }
    }

    override fun createText(indent: Int) = buildString {
        (1..indent).forEach { append(" ") }
        append("<$tagName${createClasses()}${createAttributes()}")
        if (isClosed) append(" />") else append(">$txtContent</$tagName>")
    }

    operator fun String.unaryPlus() {
        txtContent = this
    }
}