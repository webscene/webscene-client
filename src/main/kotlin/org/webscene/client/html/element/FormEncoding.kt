package org.webscene.client.html.element

enum class FormEncoding(val txt: String) {
    URL("application/x-www-form-urlencoded"),
    MULTI_PART("multipart/form-data"),
    TEXT("text/plain")
}