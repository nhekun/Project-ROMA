package com.roma.roman

/**
 * Converts integers to Roman numerals using Unicode characters.
 * Supports numbers from 1 to 3999 (standard Roman numeral range).
 * Returns "N" for 0 (nulla - Latin for zero).
 */
object RomanConverter {

    private val romanMap = mapOf(
        1000 to "Ⅿ",
        900 to "ⅭⅯ",
        500 to "Ⅾ",
        400 to "ⅭⅮ",
        100 to "Ⅽ",
        90 to "ⅩⅭ",
        50 to "Ⅼ",
        40 to "ⅩⅬ",
        10 to "Ⅹ",
        9 to "ⅠⅩ",
        5 to "Ⅴ",
        4 to "ⅠⅤ",
        1 to "Ⅰ"
    )

    private val sortedValues = romanMap.keys.sortedDescending()

    /**
     * Converts an integer to a Roman numeral string using Unicode characters.
     * @param number The integer to convert (0-3999)
     * @return Roman numeral string, or "N" for 0
     */
    fun toRoman(number: Int): String {
        if (number == 0) return "N"
        if (number < 0 || number > 3999) {
            throw IllegalArgumentException("Roman numerals support range 0-3999")
        }

        var remaining = number
        val result = StringBuilder()

        for (value in sortedValues) {
            while (remaining >= value) {
                result.append(romanMap[value])
                remaining -= value
            }
        }

        return result.toString()
    }
}