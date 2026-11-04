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

    /**
     * Formats a range of integers as Roman numerals (e.g., "I-X").
     * @param start Start of range (inclusive)
     * @param endInclusive End of range (inclusive)
     * @return Roman numeral range string
     */
    fun formatRange(start: Int, endInclusive: Int): String {
        return "${format(start)}-${format(endInclusive)}"
    }

    /**
     * Converts a Roman numeral string back to an integer.
     * Used for accessibility (content descriptions).
     * @param roman Roman numeral string
     * @return Integer value, or -1 if invalid
     */
    fun toArabic(roman: String): Int {
        var result = 0
        var i = 0
        val values = mapOf(
            'Ⅿ' to 1000, 'Ⅾ' to 500, 'Ⅽ' to 100, 'Ⅼ' to 50, 'Ⅹ' to 10, 'Ⅴ' to 5, 'Ⅰ' to 1, 'N' to 0
        )
        while (i < roman.length) {
            val current = values[roman[i]] ?: return -1
            val next = if (i + 1 < roman.length) values[roman[i + 1]] ?: 0 else 0
            if (current < next) {
                result += next - current
                i += 2
            } else {
                result += current
                i++
            }
        }
        return result
    }
}