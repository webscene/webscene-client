package org.webscene.client.html.bootstrap.style

/**
 * Contains all available Bootstrap Image styles.
 * @property txt Text representation of the image style.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class ImageStyle(val txt: String) {
    /** Makes the image a rounded square. **/
    ROUNDED("img-rounded"),
    CIRCLE("img-circle"),
    THUMBNAIL("img-thumbnail"),
    /** Image automatically scales to the parent's size. **/
    SCALABLE("img-responsive")
}