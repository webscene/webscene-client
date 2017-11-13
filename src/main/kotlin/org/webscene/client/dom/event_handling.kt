@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.events.Event

/**
 * Manages multiple event handlers for a item.
 * @author Nick Apperley
 */
class EventHandlerManager(val item: String) {
    /**
     * Holds event handlers for multiple events. Key - Event Name, Value - Event Handler.
     */
    val evtHandlers = mutableMapOf<String, (Event) -> Unit>()
}

val evtHandlerManagers = mutableListOf<EventHandlerManager>()