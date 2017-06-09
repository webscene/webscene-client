package org.webscene.client.html.bootstrap.style

/**
 * Contains all available Bootstrap Button styles.
 * @property txt Text representation of the button style.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class ButtonStyle(val txt: String) {
    /** Standard button. **/
    DEFAULT("btn-default"),
    /** Identifies a primary action. **/
    PRIMARY("btn-primary"),
    SUCCESS("btn-success"),
    INFO("btn-info"),
    WARNING("btn-warning"),
    DANGER("btn-danger"),
    /** Turns the button into a link. **/
    LINK("btn-link")
}