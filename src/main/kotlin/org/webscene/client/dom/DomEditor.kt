package org.webscene.client.dom

import org.w3c.dom.Element
import org.webscene.client.html.HtmlSection
import org.webscene.client.html.HtmlTag
import kotlin.browser.document

@Suppress("unused")
/**
 * Contains common functionality for editing the DOM.
 */
object DomEditor {
    /**
     * Supplies a function for editing a [section][htmlSection] of the DOM.
     * @param htmlSection The HTML section to apply the edit.
     * @return A function that will perform the edit.
     */
    infix fun editSection(htmlSection: HtmlSection): (domElement: Element, editType: DomEditType) -> Unit {
        /**
         * Makes changes to the HTML body section.
         * @param domElement The DOM element used in the edit.
         * @param editType Type of DOM edit to apply. Only [DomEditType.PREPEND] is supported for
         * [performance reasons](https://stackoverflow.com/questions/4396849/does-the-script-tag-position-in-html-affects-performance-of-the-webpage).
         */
        fun editBody(domElement: Element, editType: DomEditType) {
            if (editType == DomEditType.PREPEND) document.prependElement(domElement, HtmlSection.BODY)
        }

        /**
         * Makes changes to the HTML head section.
         * @param domElement The DOM element used in the edit.
         * @param editType Type of DOM edit to apply.
         */
        fun editHead(domElement: Element, editType: DomEditType) {
            when (editType) {
                DomEditType.APPEND -> document.appendElement(domElement, HtmlSection.HEAD)
                DomEditType.PREPEND -> document.prependElement(domElement, HtmlSection.HEAD)
                DomEditType.REMOVE -> document.removeElement(domElement, HtmlSection.HEAD)
            }
        }

        // TODO: Remove the block below.
        @Suppress("LiftReturnOrAssignment")
        when (htmlSection) {
            HtmlSection.HEAD -> return ::editHead
            else -> return ::editBody
        }

        // TODO: Uncomment the block below
//        return when(htmlSection) {
//            HtmlSection.HEAD -> ::editHead
//            else -> ::editBody
//        }
    }

    /**
     * Replaces an existing [DOM element][Element] with a new one. The ID for the element to be replaced **MUST** exist
     * in the DOM.
     * @param block Function for replacing the old element with the [new element][HtmlTag] (must have its ID set),
     * which must be returned in the last line.
     */
    fun replaceElement(block: () -> HtmlTag) = document.replaceElement(block)

    /**
     * Removes an existing DOM element by its ID.
     * @param id Unique identifier of the DOM element.
     */
    fun removeElementById(id: String) = document.removeElementById(id)
}