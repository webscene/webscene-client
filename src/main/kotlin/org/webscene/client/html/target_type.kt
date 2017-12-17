package org.webscene.client.html

/**
 * Contains targets used by some HTML elements.
 */
enum class TargetType {
    BLANK, SELF, PARENT, TOP
}

val TargetType.txt
    get() = "_${name.toLowerCase()}"