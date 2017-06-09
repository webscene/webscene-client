package org.webscene.client.html.bootstrap.style

/**
 * Contains all available paragraph styles.
 * @property txt Text representation of the paragraph style.
 * @author Nick Apperley
 */
@Suppress("unused")
enum class ParagraphStyle(val txt: String) {
    /** Make the text look as though it is disabled. **/
    TXT_MUTED("text-muted"),
    /** Identifies important text. **/
    TXT_PRIMARY("text-primary"),
    TXT_SUCCESS("text-success"),
    TXT_WARNING("text-warning"),
    TXT_DANGER("text-danger"),
    /** Identifies an important paragraph by it's background. **/
    BG_PRIMARY("bg-primary"),
    BG_SUCCESS("bg-success"),
    BG_INFO("bg-info"),
    BG_WARNING("bg-warning"),
    BG_DANGER("bg-danger")
}