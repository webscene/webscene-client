package org.webscene.client.html.bootstrap.style

/**
 * Contains all available Bootstrap Table styles.
 * @property txt Text representation of the table style.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class TableStyle(val txt: String) {
    /** Zebra stripe table rows. **/
    STRIPED("table-striped"),
    /** Apply a border to the table. **/
    BORDERED("table-bordered"),
    /** Enable a hover state on table rows.. **/
    HOVER("table-hover"),
    /** Condense a table by halving cell padding. **/
    CONDENSED("table-condensed")
}