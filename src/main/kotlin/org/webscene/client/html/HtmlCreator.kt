package org.webscene.client.html

import org.webscene.client.createHtmlElement
import org.webscene.client.createHtmlHeading
import org.webscene.client.createHtmlImage
import org.webscene.client.createParentHtmlElement
import org.webscene.client.html.element.HtmlElement
import org.webscene.client.html.element.ImageElement
import org.webscene.client.html.element.ParentHtmlElement

@Suppress("unused")
/**
 * Manages creating HTML elements.
 */
object HtmlCreator {
    /**
     * Creates a new parent HTML element that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    fun parentElement(tagName: String, block: ParentHtmlElement.() -> Unit) =
        createParentHtmlElement(tagName, block)

    /**
     * Creates a new HTML element which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    fun element(tagName: String, block: HtmlElement.() -> Unit) = createHtmlElement(tagName, block)

    /**
     * Creates a new HTML **span** element that can contain HTML elements, and is used to group element in a document.
     * @param block Initialisation block for setting up the span element.
     * @return A new span element.
     */
    fun span(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("span", block)

    /**
     * Creates a new HTML **div** element that can contain HTML elements, and is used to layout elements in a document.
     * @param block Initialisation block for setting up the div element.
     * @return A new div element.
     */
    fun div(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("div", block)

    /**
     * Creates a new HTML heading that can contain HTML elements, and is used to display a heading.
     * @param level A number between 1-6 for the size of the heading. Using a bigger number results in a smaller
     * heading being displayed.
     * @param block Initialisation block for setting up the heading.
     * @return A new heading.
     */
    fun heading(level: Int = 1, block: ParentHtmlElement.() -> Unit): ParentHtmlElement = createHtmlHeading(
        level, block)

    fun header(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("header", block)

    fun footer(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("footer", block)

    fun image(src: String, alt: String = "", block: ImageElement.() -> Unit): ImageElement = createHtmlImage(
        src = src, alt = alt, block = block)

    fun bold(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("b", block)

    fun italic(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = parentElement("i", block)
}
