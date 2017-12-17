package org.webscene.client.html

/**
 * Contains all HTML sections that might be in a web page.
 */
enum class HtmlSection(val tagName: String) {
    HEAD("head"),
    BODY("body"),
    HEADER("heading"),
    FOOTER("footer"),
    NAVIGATION("nav")
}