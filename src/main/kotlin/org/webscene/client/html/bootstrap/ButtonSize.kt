package org.webscene.client.html.bootstrap

/**
 * Contains all available Bootstrap Button sizes.
 * @property txt Text representation of the button size.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class ButtonSize(val txt: String) {
    LARGE("btn-lg"),
    SMALL("btn-sm"),
    EXTRA_SMALL("btn-xs"),
    /** Make the button use the full width of the parent. **/
    FULL_WIDTH("btn-block")
}