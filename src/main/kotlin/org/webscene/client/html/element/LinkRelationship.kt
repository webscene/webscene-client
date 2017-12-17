package org.webscene.client.html.element

/**
 * Contains relationship types for the [link element][org.webscene.client.html.element.LinkElement].
 */
enum class LinkRelationship(val txt: String) {
    ALTERNATE("alternate"),
    AUTHOR("author"),
    BOOKMARK("bookmark"),
    EXTERNAL("external"),
    HELP("help"),
    LICENSE("license"),
    NEXT("next"),
    NO_FOLLOW("nofollow"),
    NO_REFERRER("noreferrer"),
    NO_OPENER("noopener"),
    PREV("prev"),
    SEARCH("search"),
    TAG("tag"),
    NONE("")
}