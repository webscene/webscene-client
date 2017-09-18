package org.webscene.client

/**
 * Has all notification permission types that are used with the W3C Permission API.
 */
enum class NotificationPermission {
    /** Permission hasn't been requested from the user yet. No notification will be displayed. **/
    DEFAULT,
    /** User has granted permission to display notifications. Notifications will be displayed. **/
    GRANTED,
    /** User has denied permission to display notifications. Notifications will *NOT* be displayed. **/
    DENIED
}

val NotificationPermission.txt
    get() = name.toLowerCase()