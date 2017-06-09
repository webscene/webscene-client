package org.webscene.client.html.bootstrap

/**
 * Contains all available Bootstrap column sizes.
 * @property txt Text representation of the column size.
 * @author Nick Apperley
 */
enum class ColumnSize(val txt: String) {
    /** Smartphone size. **/
    EXTRA_SMALL("col-xs"),
    /** Tablet size. **/
    SMALL("col-sm"),
    /** Desktop size. **/
    MEDIUM("col-md"),
    /** Desktop size. **/
    LARGE("col-lg")
}