package org.webscene.client

/**
 * Has all notification permission types that are used with the W3C Permission API.
 */
enum class NotificationPermission(val txt: String) {
    /** Permission hasn't been requested from the user yet. No notification will be displayed. **/
    DEFAULT("default"),
    /** User has granted permission to display notifications. Notifications will be displayed. **/
    GRANTED("granted"),
    /** User has denied permission to display notifications. Notifications will *NOT* be displayed. **/
    DENIED("denied")
}