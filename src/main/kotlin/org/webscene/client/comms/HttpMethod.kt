package org.webscene.client.comms

enum class HttpMethod(val txt: String) {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    CONNECT("CONNECT")
}