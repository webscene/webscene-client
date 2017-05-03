package org.webscene.client.http

/**
 * General content types used in a HTTP/S request or response.
 * @property txt [String] representation of the content type.
 * @author Nick Apperley
 */
enum class ContentType(val txt: String) {
    /** HTML file. **/
    HTML("text/html"),
    /** Text file. **/
    PLAIN_TEXT("text/plain"),
    /** PDF file. **/
    PDF("application/pdf"),
    /** AVI video file. **/
    AVI_VIDEO("video/avi"),
    /** JavaScript file **/
    JAVA_SCRIPT("application/javascript"),
    /** Bitmap image file. **/
    BITMAP_IMG("image/bmp"),
    /** CSS file. **/
    CSS("text/css"),
    /** Mpeg 4 video file. **/
    MPEG4_VIDEO("video/mp4"),
    /** Mpeg 3 sound file. **/
    MPEG3_AUDIO("audio/mp3"),
    /** OGG sound file. **/
    OGG_AUDIO("audio/ogg"),
    /** Webm video file. **/
    WEBM_VIDEO("video/webm"),
    /** PNG image file. **/
    PNG_IMG("image/png"),
    /** Gif image file. **/
    GIF_IMG("image/gif"),
    /** XML file. **/
    XML("application/xml"),
    /** Zip compression file. **/
    ZIP("application/zip")
}