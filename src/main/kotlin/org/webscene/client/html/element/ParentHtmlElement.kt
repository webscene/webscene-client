package org.webscene.client.html.element

import org.w3c.dom.Element
import org.webscene.client.html.HtmlTag
import org.webscene.client.html.ParentHtmlTag
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.appendText
import kotlin.dom.createElement

/**
 * A parent HTML element which can contain children (HTML elements).
 * @author Nick Apperley
 */
open class ParentHtmlElement : ParentHtmlTag {
    override var id = ""
    /** Contains unique class names. **/
    val classes = mutableSetOf<String>()
    override lateinit var tagName: String
    override val attributes = mutableMapOf<String, String>()
    override var txtContent = ""
    override val children = mutableListOf<HtmlTag>()

    /**
     * Creates a new parent HTML element in [ParentHtmlElement] that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    @Suppress("unused")
    fun parentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit): ParentHtmlElement {
        val parentHtmlElement = ParentHtmlElement()

        parentHtmlElement.tagName = tagName
        children.add(parentHtmlElement)
        parentHtmlElement.block()
        return parentHtmlElement
    }

    /**
     * Creates a new HTML element in [ParentHtmlElement] which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    @Suppress("unused")
    fun htmlElement(tagName: String, block: HtmlElement.() -> Unit): HtmlElement {
        val htmlElement = HtmlElement()

        htmlElement.tagName = tagName
        children.add(htmlElement)
        htmlElement.block()
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
            if (txtContent.isNotEmpty()) appendText(txtContent)
            tmpChildren.forEach { appendChild(it.toDomElement()) }
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
     * Changes the [text][txtContent] to include in the parent HTML element.
     */
    operator fun String.unaryPlus() {
        txtContent = this
    }
}