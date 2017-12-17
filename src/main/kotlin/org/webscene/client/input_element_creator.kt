package org.webscene.client

import org.webscene.client.html.ButtonType
import org.webscene.client.html.HtmlCreator
import org.webscene.client.html.InputType
import org.webscene.client.html.element.FormElement
import org.webscene.client.html.element.HtmlElement
import org.webscene.client.html.element.LinkElement
import org.webscene.client.html.element.ParentHtmlElement

internal fun createHtmlForm(
    action: String,
    method: HttpMethod,
    block: FormElement.() -> Unit
): FormElement {
    val formElement = FormElement()

    formElement.block()
    formElement.action = action
    formElement.method = method
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
    inputElement.tagName = "input"
    inputElement.attributes["type"] = type.txt
    if (name.isNotEmpty()) inputElement.attributes["name"] = name
    if (disabled) inputElement.attributes["disabled"] = "" else inputElement.attributes -= "disabled"
    if (readOnly) inputElement.attributes["readonly"] = "" else inputElement.attributes -= "readonly"
    if (autoFocus) inputElement.attributes["autofocus"] = "" else inputElement.attributes -= "autofocus"
    inputElement.isClosed = true
    return inputElement
}

internal fun createHtmlLink(href: String, block: LinkElement.() -> Unit): LinkElement {
    val linkElement = LinkElement()

    linkElement.block()
    linkElement.href = href
    return linkElement
}

internal fun createHtmlOption(value: String, block: ParentHtmlElement.() -> Unit): ParentHtmlElement {
    val optionElement = ParentHtmlElement()

    optionElement.block()
    optionElement.tagName = "option"
    optionElement.attributes["value"] = value
    return optionElement
}

internal fun createHtmlButton(
    type: ButtonType = ButtonType.BUTTON,
    block: ParentHtmlElement.() -> Unit
): ParentHtmlElement {
    val btnElement = ParentHtmlElement()

    btnElement.block()
    btnElement.attributes["type"] = type.name.toLowerCase()
    btnElement.tagName = "button"
    return btnElement
}
