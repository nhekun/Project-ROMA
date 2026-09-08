package com.roma.roman

/**
 * Main formatter interface for the ROMA launcher.
 * Every number displayed by the launcher must use this formatter.
 */
object RomanFormatter {

    /**
     * Formats an integer as a Roman numeral.
     * @param number The integer to format
     * @return Roman numeral string
     */
    fun format(number: Int): String {
        return RomanConverter.toRoman(number)
    }

    /**
     * Formats an integer as a Roman numeral with a minimum width (padding with N for zero).
     * @param number The integer to format
     * @param minWidth Minimum width of the result
     * @return Roman numeral string padded if necessary
     */
    fun format(number: Int, minWidth: Int): String {
        val roman = format(number)
        return if (roman.length < minWidth) {
            "N".repeat(minWidth - roman.length) + roman
        } else {
            roman
        }
    }

    /**
     * Formats a two-digit number (like hours or minutes) as Roman numerals with zero-padding.
     * @param number The integer to format (0-59 typically)
     * @return Two-character Roman numeral string
     */
    fun formatTwoDigit(number: Int): String {
        return format(number, 2)
    }
}