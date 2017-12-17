package org.webscene.client.html.element

import org.w3c.dom.Element
import org.webscene.client.HttpMethod
import org.webscene.client.createHtmlInput
import org.webscene.client.html.ButtonType
import org.webscene.client.html.InputType
import org.webscene.client.html.TargetType
import org.webscene.client.html.txt

/**
 * Represents a HTML **form** element.
 */
class FormElement : ParentHtmlElement() {
    @Suppress("RedundantSetter")
    override var tagName: String
        get() = "form"
        set(value) {}

    /**
     * Specifies where to send the form data when a form is submitted (as a URL).
     */
    var action = ""
    /**
     * The HTTP method to use. Must be [HttpMethod.GET] or [HttpMethod.POST].
     */
    var method: HttpMethod = HttpMethod.GET
    /**
     * Specifies the character encodings that are to be used for the form submission.
     */
    var acceptCharset: String = ""
    /**
     * Specifies whether a form should have autocomplete on or off.
     */
    var autoComplete = true
    /**
     * Specifies how the form-data should be encoded when submitting it to the server. Only for [HttpMethod.POST].
     */
    var encodingType: FormEncoding = FormEncoding.URL
    /**
     * 	Specifies the name of a form.
     */
    var name = ""
    /**
     * Specifies where to display the response that is received after submitting the form.
     */
    var target: TargetType = TargetType.BLANK

    /**
     * 	Specifies that the form should not be validated when submitted if set to true.
     */
    var disableValidation = false

    private fun updateAttributes() {
        if (action.isNotEmpty()) attributes["action"] = action
        attributes["method"] = if (method == HttpMethod.GET || method == HttpMethod.POST) {
            method.name
        } else {
            HttpMethod.GET.name
        }
        if (acceptCharset.isNotEmpty()) attributes["accept-charset"] = acceptCharset
        attributes["autocomplete"] = if (autoComplete) "on" else "off"
        attributes["enctype"] = encodingType.txt
        attributes["name"] = name
        if (disableValidation) attributes["novalidate"] = ""
        attributes["target"] = target.txt
    }

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
    ) {
        children += createHtmlInput(
            type = type,
            disabled = disabled,
            readOnly = readOnly,
            autoFocus = autoFocus,
            name = name,
            block = block
        )
    }

    fun textArea(maxLength: Int = 50, block: ParentHtmlElement.() -> Unit) {
        val txtAreaElement = ParentHtmlElement()

        txtAreaElement.block()
        txtAreaElement.tagName = "textarea"
        txtAreaElement.attributes["maxlength"] = "$maxLength"
        children += txtAreaElement
    }

    fun button(type: ButtonType, block: ParentHtmlElement.() -> Unit) {
        val btnElement = ParentHtmlElement()

        btnElement.block()
        btnElement.tagName = "button"
        btnElement.attributes["type"] = type.name
        children += btnElement
    }

    fun select(block: ParentHtmlElement.() -> Unit) {
        val selectElement = ParentHtmlElement()

        selectElement.block()
        selectElement.tagName = "select"
        children += selectElement
    }

    fun fieldSet(block: ParentHtmlElement.() -> Unit) {
        val fieldSetElement = ParentHtmlElement()

        fieldSetElement.block()
        fieldSetElement.tagName = "fieldset"
        children += fieldSetElement
    }

    fun label(block: ParentHtmlElement.() -> Unit) {
        val labelElement = ParentHtmlElement()

        labelElement.block()
        labelElement.tagName = "label"
        children += labelElement
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