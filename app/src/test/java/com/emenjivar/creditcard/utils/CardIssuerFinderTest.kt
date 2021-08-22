package com.emenjivar.creditcard.utils

import com.emenjivar.creditcard.model.CardIssuer
import org.junit.Assert.assertEquals
import org.junit.Test

class CardIssuerFinderTest {

    private val finder = CardIssuerFinder()

    @Test
    fun `test when card number has the minimum required visa format`() {
        // Given a minimum card number following visa constraints
        val number = "4"

        // Then verify the output is VISA
        assertEquals(CardIssuer.VISA, finder.detect(number))
    }

    @Test
    fun `test when complete card number has the visa format`() {
        // Given a 16-char card number
        val number = "4200220033004400"

        // Then verify the output is VISA
        assertEquals(CardIssuer.VISA, finder.detect(number))
    }

    @Test
    fun `test when card number has the minimum required mastercard format`() {
        // Given the following list of numbers
        val fifty = "50" // Out of range
        val fiftyOne = "51"
        val fiftyTwo = "52"
        val fiftyThree = "53"
        val fiftyFour = "54"
        val fiftyFive = "55"
        val fiftySix = "56" // Out of range

        // Then verify the outputs are MASTERCARD
        assertEquals(CardIssuer.MASTERCARD, finder.detect(fiftyOne))
        assertEquals(CardIssuer.MASTERCARD, finder.detect(fiftyTwo))
        assertEquals(CardIssuer.MASTERCARD, finder.detect(fiftyThree))
        assertEquals(CardIssuer.MASTERCARD, finder.detect(fiftyFour))
        assertEquals(CardIssuer.MASTERCARD, finder.detect(fiftyFive))

        // Then verify also a bad input, which is OTHER
        assertEquals(CardIssuer.OTHER, finder.detect(fifty))
        assertEquals(CardIssuer.OTHER, finder.detect(fiftySix))
    }

    @Test
    fun `test when complete card number has the mastercard format`() {
        // Given a 16-char card number
        val number = "5228791099000012"

        // Then verify the output is VISA
        assertEquals(CardIssuer.MASTERCARD, finder.detect(number))
    }

    @Test
    fun `test when card number has the minimum required american express format`() {
        // Given the following list of numbers
        val thirtyThree = "33" // Out of range
        val thirtyFour = "34"
        val thirtyFive = "35"
        val thirtySix = "36"
        val thirtySeven = "37"
        val thirtyEight = "38" // Out of range

        // Then verify the outputs are AMERICAN_EXPRESS
        assertEquals(CardIssuer.AMERICAN_EXPRESS, finder.detect(thirtyFour))
        assertEquals(CardIssuer.AMERICAN_EXPRESS, finder.detect(thirtyFive))
        assertEquals(CardIssuer.AMERICAN_EXPRESS, finder.detect(thirtySix))
        assertEquals(CardIssuer.AMERICAN_EXPRESS, finder.detect(thirtySeven))

        // Then verify also a bad input, which is OTHER
        assertEquals(CardIssuer.OTHER, finder.detect(thirtyThree))
        assertEquals(CardIssuer.OTHER, finder.detect(thirtyEight))
    }

    @Test
    fun `test when complete card number has the american express format`() {
        // Given a 15-char card number
        val number = "375987654321001"

        // Then verify the output is VISA
        assertEquals(CardIssuer.AMERICAN_EXPRESS, finder.detect(number))
    }
}