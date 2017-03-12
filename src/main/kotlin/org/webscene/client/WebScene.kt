package org.webscene.client

import org.webscene.client.html.HtmlElement
import org.webscene.client.html.ParentHtmlElement

/**
 * Provides general view functionality.
 */
object WebScene {
    fun parentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit): ParentHtmlElement {
        val parentHtmlElement = ParentHtmlElement()

        parentHtmlElement.tagName = tagName
        parentHtmlElement.init()
        return parentHtmlElement
    }

    fun htmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
        val htmlElement = HtmlElement()

        htmlElement.tagName = tagName
        htmlElement.init()
        return htmlElement
    }
}