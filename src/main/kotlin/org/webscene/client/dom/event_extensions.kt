@file:Suppress("unused")

package org.webscene.client.dom

import org.w3c.dom.events.Event

// Extensions for org.w3c.dom.events.Event.
// Author - Nick Apperley

val Event.keyCode: String
    get() = this.asDynamic().keyCode.toString()