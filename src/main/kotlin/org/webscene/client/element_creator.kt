package org.webscene.client

import org.webscene.client.html.HtmlElement
import org.webscene.client.html.ParentHtmlElement
import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row

// Creates Bootstrap and HTML elements.
// Author - Nick Apperley

internal fun createBootstrapContainer(init: Container.() -> Unit): Container {
    val containerElement = Container()

    containerElement.init()
    return containerElement
}

internal fun createBootstrapRow(init: Row.() -> Unit): Row {
    val rowElement = Row()

    rowElement.init()
    return rowElement
}

internal fun createBootstrapColumn(colSizes: Array<Pair<ColumnSize, Int>>, init: Column.() -> Unit): Column {
    val colElement = Column()

    colSizes.forEach { colElement.colSizes[it.first] = it.second }
    colElement.init()
    return colElement
}

internal fun createParentHtmlElement(tagName: String, init: ParentHtmlElement.() -> Unit): ParentHtmlElement {
    val parentHtmlElement = ParentHtmlElement()

    parentHtmlElement.tagName = tagName
    parentHtmlElement.init()
    return parentHtmlElement
}

internal fun createHtmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
    val htmlElement = HtmlElement()

    htmlElement.tagName = tagName
    htmlElement.init()
    return htmlElement
}