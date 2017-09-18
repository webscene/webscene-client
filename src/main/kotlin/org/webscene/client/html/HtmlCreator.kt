package org.webscene.client.html

import org.webscene.client.*
import org.webscene.client.HttpMethod

@Suppress("unused")
/**
 * Manages creating HTML elements.
 */
object HtmlCreator {
    /**
     * Creates a new parent HTML element that can contain child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [parent HTML element][ParentHtmlElement].
     */
    fun parentElement(tagName: String, block: ParentHtmlElement.() -> Unit) =
        createParentHtmlElement(tagName, block)

    /**
     * Creates a new HTML element which doesn't have any child HTML elements.
     * @param tagName Name of the tag.
     * @param block Initialisation block for setting up the HTML element.
     * @return A new [HTML element][HtmlElement].
     */
    fun element(tagName: String, block: HtmlElement.() -> Unit) = createHtmlElement(tagName, block)

    /**
     * Creates a new HTML **form** element that can contain child HTML elements.
     * @param action Relative URL to the REST resource.
     * @param block Initialisation block for setting up the form.
     * @return A new HTML **form** element.
     */
    fun form(action: String, method: HttpMethod, block: ParentHtmlElement.() -> Unit): ParentHtmlElement =
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
     * @return A new HTML **input** element.
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
}