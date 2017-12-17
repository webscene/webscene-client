package org.webscene.client

import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row
import org.webscene.client.html.element.HtmlElement
import org.webscene.client.html.element.ImageElement
import org.webscene.client.html.element.ParentHtmlElement

// Creates Bootstrap and HTML elements.
// Author - Nick Apperley

internal fun createBootstrapContainer(block: Container.() -> Unit): Container {
    val containerElement = Container()

    containerElement.block()
    return containerElement
}

internal fun createBootstrapRow(block: Row.() -> Unit): Row {
    val rowElement = Row()

    rowElement.block()
    return rowElement
}

internal fun createBootstrapColumn(colSizes: Array<Pair<ColumnSize, Int>>, block: Column.() -> Unit): Column {
    val colElement = Column()

    colSizes.forEach { colElement.colSizes[it.first] = it.second }
    colElement.block()
    return colElement
}

internal fun createParentHtmlElement(tagName: String, block: ParentHtmlElement.() -> Unit): ParentHtmlElement {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.block()
    parentHtmlElement.tagName = tagName
    return parentHtmlElement
}

internal fun createHtmlElement(tagName: String, block: HtmlElement.() -> Unit): HtmlElement {
    val htmlElement = HtmlElement()

    htmlElement.block()
    htmlElement.tagName = tagName
    return htmlElement
}

internal fun createHtmlImage(src: String, alt: String = "", block: ImageElement.() -> Unit): ImageElement {
    val imgElement = ImageElement()

    imgElement.block()
    imgElement.src = src
    imgElement.alt = alt
    return imgElement
}

internal fun createHtmlHeading(level: Int = 1, block: ParentHtmlElement.() -> Unit): ParentHtmlElement {
    val headerElement = ParentHtmlElement()

    headerElement.block()
    headerElement.tagName = if (level in 1..6) "h$level" else "h1"
    return headerElement
}
