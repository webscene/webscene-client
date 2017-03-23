package org.webscene.client.http

/**
 * General HTTP/S methods.
 * @property txt String representation of the method.
 * @author Nick Apperley
 */
enum class HttpMethod(val txt: String) {
    /** Read a resource. **/
    GET("GET"),
    /** Sent a request containing data. **/
    POST("POST"),
    /** Upload data. **/
    PUT("PUT"),
    /** Remove a resource. **/
    DELETE("DELETE"),
    /** Read all header information. **/
    HEAD("HEAD"),
    /** Obtain a list of all HTTP/S methods that the server supports. **/
    OPTIONS("OPTIONS"),
    /** Convert request connection into TCP/IP tunnel. **/
    CONNECT("CONNECT")
}