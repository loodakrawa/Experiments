package loodakrawa.blackjack.test;

import loodakrawa.blackjack.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BlackJackCalculatorTest
{
    private static CardValueCalculator calculator;

    @BeforeClass
    public static void setUp()
    {
        calculator = new BlackJackCardValueCalculator();
    }

    @Test
    public void testNumericalValue()
    {
        Card card = new Card(Rank.FIVE, Suit.CLUBS);
        int value = calculator.getCardValue(card);
        Assert.assertEquals(5, value);
    }

    @Test
    public void testSpecialValue()
    {
        Card card = new Card(Rank.KING, Suit.SPADES);
        int value = calculator.getCardValue(card);
        Assert.assertEquals(10, value);
    }

    @Test
    public void testAceValue()
    {
        Card card = new Card(Rank.ACE, Suit.HEARTS);
        int value = calculator.getCardValue(card);
        Assert.assertEquals(11, value);
    }

    @Test
    public void testDifferentSuitsHaveSameValue()
    {
        Card card1 = new Card(Rank.SEVEN, Suit.SPADES);
        Card card2 = new Card(Rank.SEVEN, Suit.CLUBS);
        int value1 = calculator.getCardValue(card1);
        int value2 = calculator.getCardValue(card2);
        Assert.assertEquals(value1, value2);
    }
}
