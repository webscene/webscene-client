package org.webscene.client

import org.webscene.client.html.HtmlCreator
import org.webscene.client.html.HtmlElement
import org.webscene.client.html.InputType
import org.webscene.client.html.ParentHtmlElement
import org.webscene.client.html.bootstrap.Column
import org.webscene.client.html.bootstrap.ColumnSize
import org.webscene.client.html.bootstrap.Container
import org.webscene.client.html.bootstrap.Row

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

internal fun createHtmlForm(
    action: String,
    method: HttpMethod,
    block: ParentHtmlElement.() -> Unit
): ParentHtmlElement {
    val formElement = ParentHtmlElement()

    formElement.block()
    formElement.attributes["action"] = action
    formElement.attributes["method"] = method.name
    formElement.tagName = "form"
    return formElement
}

internal fun createHtmlDataList(
    vararg listValues: String,
    groupId: String = "",
    listId: String,
    inputId: String = "",
    inputName: String
): ParentHtmlElement = HtmlCreator.parentElement("span") {
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
    block: HtmlElement.() -> Unit
): HtmlElement {
    val inputElement = HtmlElement()

    inputElement.block()
    inputElement.attributes["type"] = type.txt
    if (name.isNotEmpty()) inputElement.attributes["name"] = name
    if (disabled) inputElement.attributes["disabled"] = "" else inputElement.attributes -= "disabled"
    if (readOnly) inputElement.attributes["readonly"] = "" else inputElement.attributes -= "readonly"
    if (autoFocus) inputElement.attributes["autofocus"] = "" else inputElement.attributes -= "autofocus"
    inputElement.isClosed = true
    return inputElement
}