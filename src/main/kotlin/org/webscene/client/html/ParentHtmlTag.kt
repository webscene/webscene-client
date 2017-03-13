package org.webscene.client.html

/**
 * Base for a parent HTML tag that contains [children].
 * @author Nick Apperley
 */
interface ParentHtmlTag : HtmlTag {
    override var isClosed: Boolean
        get() = false
        set(value) {}
    val children: MutableList<HtmlTag>
}