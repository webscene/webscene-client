package org.webscene.client.html

import org.webscene.client.*
import org.webscene.client.html.element.FormElement
import org.webscene.client.html.element.HtmlElement
import org.webscene.client.html.element.LinkElement
import org.webscene.client.html.element.ParentHtmlElement

@Suppress("unused")
/**
 * Manages creating HTML elements.
 */
object HtmlInputCreator {
    /**
     * Creates a new HTML **form** element that can contain child HTML elements.
     * @param action Relative URL to the REST resource.
     * @param method The HTTP method to use.
     * @param block Initialisation block for setting up the form.
     * @return A new HTML **form** element.
     */
    fun form(action: String, method: HttpMethod, block: FormElement.() -> Unit): FormElement =
        createHtmlForm(action = action, method = method, block = block)

    /**
     * Creates a new HTML **span** element that contains a **input** and **datalist** element.
     * @param listValues One or more values that populate the **datalist** element.
     * @param groupId The ID to use for the **span** element that is grouping everything together.
     * @param listId The ID to use for the **datalist** element.
     * @param inputId The ID to use for the **input** element.
     * @param inputName Name of the **input** element which is used for accessing data in a **form** element.
     * @return A new HTML **span** element.
     */
    fun dataList(
        vararg listValues: String,
        groupId: String = "",
        listId: String,
        inputId: String = "",
        inputName: String
    ): ParentHtmlElement = createHtmlDataList(
        groupId = groupId,
        inputId = inputId,
        inputName = inputName,
        listId = listId,
        listValues = *listValues
    )

    /**
     * Creates a new HTML **input** element that doesn't contain any HTML elements.
     * @param type The type of **input** element to use (eg [InputType.TEXT]).
     * @param disabled If set to true then the element is disabled.
     * @param readOnly If set to true then the element can only be read.
     * @param autoFocus If set to true then the element gains focus after the web page loads.
     * @param name Unique name of the input. An HTML form uses [name] as a field name.
     * @param block Initialisation block for setting up the input.
     * @return A new input element.
     */
    fun input(
        type: InputType,
        disabled: Boolean = false,
        readOnly: Boolean = false,
        autoFocus: Boolean = false,
        name: String = "",
        block: HtmlElement.() -> Unit
    ): HtmlElement = createHtmlInput(
        type = type,
        disabled = disabled,
        readOnly = readOnly,
        autoFocus = autoFocus,
        name = name,
        block = block
    )

    fun button(type: ButtonType = ButtonType.BUTTON, block: ParentHtmlElement.() -> Unit): ParentHtmlElement =
        createHtmlButton(type, block)

    fun select(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = HtmlCreator.parentElement(
        "select", block)

    fun option(value: String, block: ParentHtmlElement.() -> Unit): ParentHtmlElement = createHtmlOption(value, block)

    /**
     * Creates a new HTML **a** (anchor - also known as a link) element that can contain HTML elements.
     * @param href Specifies the URL of the page the link goes to.
     * @param block Initialisation block for setting up the link element.
     * @return A new [link element][LinkElement].
     */
    fun link(href: String, block: LinkElement.() -> Unit): LinkElement = createHtmlLink(href, block)

    fun menu(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = HtmlCreator.parentElement(
        "menu", block)

    fun menuItem(block: ParentHtmlElement.() -> Unit): ParentHtmlElement = HtmlCreator.parentElement(
        "menuitem", block)
}
