package org.webscene.client.html

import org.w3c.dom.Element
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.createElement

/**
 * A parent HTML element which can contain children (HTML elements).
 * @author Nick Apperley
 */
open class ParentHtmlElement : ParentHtmlTag {
    override var id = ""
    /**
     * Contains class names.
     */
    val classes = mutableListOf<String>()
    override lateinit var tagName: String
    override val attributes = mutableMapOf<String, String>()
    override var txtContent = ""
    override val children = mutableListOf<HtmlTag>()

    /**
     * Creates a new parent HTML element in [ParentHtmlElement] that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    @Suppress("unused")
    fun parentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit): ParentHtmlElement {
        val parentHtmlElement = ParentHtmlElement()

        parentHtmlElement.tagName = tagName
        children.add(parentHtmlElement)
        parentHtmlElement.init()
        return parentHtmlElement
    }

    /**
     * Creates a new HTML element in [ParentHtmlElement] which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param init Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    @Suppress("unused")
    fun htmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
        val htmlElement = HtmlElement()

        htmlElement.tagName = tagName
        children.add(htmlElement)
        htmlElement.init()
        return htmlElement
    }

    private fun createStartTag(indent: Int) = buildString {
        if (indent > 0) (1..indent).forEach { append(" ") }
        append("<$tagName${createClasses()}${createAttributes()}>")
    }

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
        val tmpId = id
        val tmpAttributes = attributes
        val tmpChildren = children

        return document.createElement(tagName) {
            addClass(*classes.toTypedArray())
            tmpAttributes.forEach { (key, value) -> setAttribute(key, value) }
            id = tmpId
            tmpChildren.forEach { child ->
                appendChild(child.toDomElement())
            }
        }
    }

    override fun createText(indent: Int) = buildString {
        val newIndent = indent + 2

        append("\n${createStartTag(indent)}")
        if (txtContent.isNotEmpty()) {
            append("\n")
            (1..newIndent).forEach { append(" ") }
            append(txtContent)
        }
        children.forEach { append(it.createText(newIndent)) }
        append("\n")
        (1..indent).forEach { append(" ") }
        append("</$tagName>")
    }

    /**
     * Changes the text to include in the parent HTML element.
     */
    operator fun String.unaryPlus() {
        txtContent = this
    }
}