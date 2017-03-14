package org.webscene.client.comms

@Suppress("unused")
enum class ContentType(val txt: String) {
    HTML("text/html"),
    PLAIN_TEXT("text/plain"),
    PDF("application/pdf"),
    AVI_VIDEO("video/avi"),
    JAVA_SCRIPT("application/javascript"),
    BITMAP_IMG("image/bmp"),
    CSS("text/css"),
    MPEG4_VIDEO("video/mp4"),
    MPEG3_AUDIO("audio/mp3"),
    OGG_AUDIO("audio/ogg"),
    WEBM_VIDEO("video/webm"),
    PNG_IMG("image/png"),
    GIF_IMG("image/gif"),
    XML("application/xml"),
    ZIP("application/zip")
}