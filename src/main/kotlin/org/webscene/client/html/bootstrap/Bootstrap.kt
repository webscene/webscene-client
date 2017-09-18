package org.webscene.client.html.bootstrap

import org.webscene.client.createBootstrapColumn
import org.webscene.client.createBootstrapContainer
import org.webscene.client.createBootstrapRow

@Suppress("unused")
/**
 * Contains common functionality for Bootstrap (CSS framework).
 * @author Nick Apperley
 */
object Bootstrap {
    /**
     * Creates a new [container][Container] that holds one or more rows.
     * @param block Initialisation block for setting up the [container][Container].
     * @return A new [Container].
     */
    fun container(block: Container.() -> Unit) = createBootstrapContainer(block)

    /**
     * Creates a new [row][Row] that holds one or more columns.
     * @param block Initialisation block for setting up the row.
     * @return A new [Row].
     */
    fun row(block: Row.() -> Unit) = createBootstrapRow(block)

    /**
     * Creates a new [column][Column] that can contain HTML elements.
     * @param colSizes One or more column sizes to use for sizing the column.
     * @param block Initialisation block for setting up the column.
     * @return A new [Column].
     */
    fun column(vararg colSizes: Pair<ColumnSize, Int>, block: Column.() -> Unit) =
        createBootstrapColumn(colSizes = arrayOf(*colSizes), block = block)
}