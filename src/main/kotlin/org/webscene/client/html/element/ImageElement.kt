package org.webscene.client.html.element

import org.w3c.dom.Element

/**
 * Represents a HTML **img** (image) element.
 */
class ImageElement : ParentHtmlElement() {
    companion object {
        const val ORIGIN_ANON = "anonymous"
        const val ORIGIN_CREDENTIALS = "use-credentials"
    }

    @Suppress("RedundantSetter")
    override var tagName: String
        get() = "img"
        set(value) {}

    /**
     * Image URL.
     */
    var src = ""
    /**
     * Alternative text for the image.
     */
    var alt = ""
    /**
     * Allow images from third-party sites that allow cross-origin access to be used with canvas.
     */
    var crossorigin: String = ""
    var width = 0
    var height = 0
    /**
     * Specifies an image as a server-side image map.
     */
    var isMap = false
    /**
     * Specifies a URL to a detailed description of an image.
     */
    var descUrl = ""
    /**
     * Specifies image sizes for different page layouts.
     */
    var sizes: String = ""
    /**
     * Specifies the URL of the image to use in different situations.
     */
    var srcSet: String = ""
    /**
     * Specifies an image as a client-side image map.
     */
    var useMap: String = ""

    private fun updateAttributes() {
        attributes["src"] = src
        attributes["alt"] = alt
        if (descUrl.isNotEmpty()) attributes["longdesc"] = descUrl
        if (sizes.isNotEmpty()) attributes["sizes"] = sizes
        if (srcSet.isNotEmpty()) attributes["srcset"] = srcSet
        if (useMap.isNotEmpty()) attributes["usemap"] = useMap
        if (crossorigin == ORIGIN_ANON || crossorigin == ORIGIN_CREDENTIALS) attributes["crossorigin"] = crossorigin
        if (isMap) attributes["ismap"] = ""
        if (width > 0) attributes["width"] = "$width"
        if (height > 0) attributes["height"] = "$height"
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