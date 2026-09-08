package com.roma.roman

import org.junit.Assert.assertEquals
import org.junit.Test

class RomanConverterTest {

    @Test
    fun `zero should return N`() {
        assertEquals("N", RomanConverter.toRoman(0))
    }

    @Test
    fun `one should return I`() {
        assertEquals("Ⅰ", RomanConverter.toRoman(1))
    }

    @Test
    fun `four should return IV`() {
        assertEquals("ⅠⅤ", RomanConverter.toRoman(4))
    }

    @Test
    fun `five should return V`() {
        assertEquals("Ⅴ", RomanConverter.toRoman(5))
    }

    @Test
    fun `nine should return IX`() {
        assertEquals("ⅠⅩ", RomanConverter.toRoman(9))
    }

    @Test
    fun `ten should return X`() {
        assertEquals("Ⅹ", RomanConverter.toRoman(10))
    }

    @Test
    fun `forty should return XL`() {
        assertEquals("ⅩⅬ", RomanConverter.toRoman(40))
    }

    @Test
    fun `fortyOne should return XLI`() {
        assertEquals("ⅩⅬⅠ", RomanConverter.toRoman(41))
    }

    @Test
    fun `fifty should return L`() {
        assertEquals("Ⅼ", RomanConverter.toRoman(50))
    }

    @Test
    fun `ninety should return XC`() {
        assertEquals("ⅩⅭ", RomanConverter.toRoman(90))
    }

    @Test
    fun `oneHundred should return C`() {
        assertEquals("Ⅽ", RomanConverter.toRoman(100))
    }

    @Test
    fun `fourHundred should return CD`() {
        assertEquals("ⅭⅮ", RomanConverter.toRoman(400))
    }

    @Test
    fun `fiveHundred should return D`() {
        assertEquals("Ⅾ", RomanConverter.toRoman(500))
    }

    @Test
    fun `nineHundred should return CM`() {
        assertEquals("ⅭⅯ", RomanConverter.toRoman(900))
    }

    @Test
    fun `oneThousand should return M`() {
        assertEquals("Ⅿ", RomanConverter.toRoman(1000))
    }

    @Test
    fun `twoThousandTwentySix should return MMXXVI`() {
        assertEquals("ⅯⅯⅩⅩⅤⅠ", RomanConverter.toRoman(2026))
    }

    @Test
    fun `threeThousandNineHundredNinetyNine should return MMMCMXCIX`() {
        assertEquals("ⅯⅯⅯⅭⅯⅩⅭⅠⅩ", RomanConverter.toRoman(3999))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `negativeNumberShouldThrowException`() {
        RomanConverter.toRoman(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `numberOver3999ShouldThrowException`() {
        RomanConverter.toRoman(4000)
    }
}