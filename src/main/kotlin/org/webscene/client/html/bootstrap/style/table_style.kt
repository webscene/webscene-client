package org.webscene.client.html.bootstrap.style

/**
 * Contains all available Bootstrap Table styles.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class TableStyle {
    /** Zebra stripe table rows. **/
    STRIPED,
    /** Apply a border to the table. **/
    BORDERED,
    /** Enable a hover state on table rows.. **/
    HOVER,
    /** Condense a table by halving cell padding. **/
    CONDENSED
}

val TableStyle.txt
    get() = "table-${name.toLowerCase()}"