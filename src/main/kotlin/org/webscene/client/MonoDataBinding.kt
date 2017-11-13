package org.webscene.client

import kotlin.properties.Delegates

@Suppress("unused")
/**
 * Container for data that is observed for changes via one way data binding.
 */
class MonoDataBinding<T : Any>(val id: String = "", initialVal: T) {
    /** The data to be observed for data binding. */
    var data: T by Delegates.observable(initialVal) { _, oldVal, newVal ->
        onDataChanged.forEach { it(id, oldVal, newVal) }
    }
    /** Stores event handlers for the onDataChanged event. **/
    val onDataChanged = mutableListOf<(id: String, oldVal: T, newVal: T) -> Unit>()
}