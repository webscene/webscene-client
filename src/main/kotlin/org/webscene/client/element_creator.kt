package org.webscene.client

import org.webscene.client.html.HtmlElement
import org.webscene.client.html.InputType
import org.webscene.client.html.ParentHtmlElement
import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row
import org.webscene.client.http.HttpMethod

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

    parentHtmlElement.init()
    parentHtmlElement.tagName = tagName
    return parentHtmlElement
}

internal fun createHtmlElement(tagName: String, init: HtmlElement.() -> Unit): HtmlElement {
    val htmlElement = HtmlElement()

    htmlElement.init()
    htmlElement.tagName = tagName
    return htmlElement
}

internal fun createHtmlForm(
    action: String,
    method: HttpMethod,
    init: ParentHtmlElement.() -> Unit
): ParentHtmlElement {
    val formElement = ParentHtmlElement()

    formElement.init()
    formElement.attributes["action"] = action
    formElement.attributes["method"] = method.txt
    formElement.tagName = "form"
    return formElement
}

internal fun createHtmlDataList(
    vararg listValues: String,
    groupId: String = "",
    listId: String,
    inputId: String = "",
    inputName: String
): ParentHtmlElement = WebScene.parentHtmlElement("span") {
    if (groupId.isNotEmpty()) id = groupId
    htmlElement("input") {
        if (inputId.isNotEmpty()) id = inputId
        attributes["list"] = listId
        attributes["name"] = inputName
    }
    parentHtmlElement("datalist") {
        id = listId
        listValues.forEach { value ->
            htmlElement("option") { attributes["value"] = value }
        }
    }
}

internal fun createHtmlInput(
    type: InputType,
    disabled: Boolean = false,
    readOnly: Boolean = false,
    autoFocus: Boolean = false,
    name: String = "",
    init: HtmlElement.() -> Unit
): HtmlElement {
    val inputElement = HtmlElement()

    inputElement.init()
    inputElement.attributes["type"] = type.txt
    if (name.isNotEmpty()) inputElement.attributes["name"] = name
    if (disabled) inputElement.attributes["disabled"] = "" else inputElement.attributes -= "disabled"
    if (readOnly) inputElement.attributes["readonly"] = "" else inputElement.attributes -= "readonly"
    if (autoFocus) inputElement.attributes["autofocus"] = "" else inputElement.attributes -= "autofocus"
    inputElement.isClosed = true
    return inputElement
}