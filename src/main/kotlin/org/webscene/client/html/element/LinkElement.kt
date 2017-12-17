package org.webscene.client.html.element

import org.w3c.dom.Element

/**
 * Represents a HTML **a** (anchor - also known as a link) element.
 */
class LinkElement : ParentHtmlElement() {
    @Suppress("RedundantSetter")
    override var tagName: String
        get() = "a"
        set(value) {}
    /**
     * The filename. Specifies that the target will be downloaded when a user clicks on the hyperlink if set
     * (not empty).
     */
    var download: String = ""
    /**
     * 	Specifies the URL of the page the link goes to.
     */
    var href: String = ""
    /**
     * Specifies the language of the linked document.
     */
    var hrefLang: String = ""
    /**
     * Specifies what media/device the linked document is optimized for.
     */
    var media: String = ""
    /**
     * Specifies the relationship between the current document and the linked document.
     */
    var rel: LinkRelationship = LinkRelationship.NONE
    var target: String = ""
    var type = ""

    private fun updateAttributes() {
        if (download.isNotEmpty()) attributes["download"] = download
        if (href.isNotEmpty()) attributes["href"] = href
        if (hrefLang.isNotEmpty()) attributes["hreflang"] = hrefLang
        if (media.isNotEmpty()) attributes["media"] = media
        if (rel != LinkRelationship.NONE) attributes["rel"] = rel.txt
        if (target.isNotEmpty()) attributes["target"] = target
        if (type.isNotEmpty()) attributes["type"] = type
    }

    override fun toDomElement(): Element {
        updateAttributes()
        return super.toDomElement()
    }

    override fun createText(indent: Int): String {
        updateAttributes()
        return super.createText(indent)
    }
}