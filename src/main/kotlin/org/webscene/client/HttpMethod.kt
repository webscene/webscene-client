package org.webscene.client

/**
 * General HTTP/S methods.
 * @property txt String representation of the method.
 * @author Nick Apperley
 */
enum class HttpMethod {
    /** Read a resource. **/
    GET,
    /** Sent a request containing data. **/
    POST,
    /** Upload data. **/
    PUT,
    /** Remove a resource. **/
    DELETE,
    /** Read all heading information. **/
    HEAD,
    /** Obtain a list of all HTTP/S methods that the server supports. **/
    OPTIONS,
    /** Convert request connection into TCP/IP tunnel. **/
    CONNECT
}