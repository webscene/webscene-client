package org.webscene.client.html

/**
 * Contains all available **input** element types. Normally used in a **form** element.
 * @property txt Text representation of the input type.
 * @author Nick Apperley
 */
enum class InputType(val txt: String) {
    BUTTON("button"),
    CHECKBOX("checkbox"),
    /** Input is a colour dialog box. */
    COLOUR("color"),
    DATE("date"),
    DATE_TIME("datetime-local"),
    EMAIL("email"),
    /** Input is a select file dialog box. */
    FILE("file"),
    /** A field containing hidden input. */
    HIDDEN("hidden"),
    IMAGE("image"),
    /** Input is a month and year control. */
    MONTH("month"),
    NUMBER("number"),
    PASSWORD("password"),
    RADIO("radio"),
    /** Input for entering a numerical range. Default range is 0-100. */
    SLIDER("range"),
    RESET("reset"),
    /** Input for entering some search text. */
    SEARCH("search"),
    SUBMIT("submit"),
    PHONE("tel"),
    /** Input is a text box. */
    TEXT("text"),
    TIME("time"),
    URL("url"),
    /** Input is a week and year control. */
    WEEK("week")
}