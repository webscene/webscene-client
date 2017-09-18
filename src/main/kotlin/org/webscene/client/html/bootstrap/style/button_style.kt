package org.webscene.client.html.bootstrap.style

/**
 * Contains all available Bootstrap Button styles.
 * @property txt Text representation of the button style.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class ButtonStyle {
    /** Standard button. **/
    DEFAULT,
    /** Identifies a primary action. **/
    PRIMARY,
    SUCCESS,
    INFO,
    WARNING,
    DANGER,
    /** Turns the button into a link. **/
    LINK
}

val ButtonStyle.txt
    get() = "btn-${name.toLowerCase()}"