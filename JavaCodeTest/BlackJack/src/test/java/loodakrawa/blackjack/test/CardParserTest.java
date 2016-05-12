package loodakrawa.blackjack.test;

import loodakrawa.blackjack.Card;
import loodakrawa.blackjack.CardParser;
import loodakrawa.blackjack.Rank;
import loodakrawa.blackjack.Suit;
import org.junit.Assert;
import org.junit.Test;

public class CardParserTest
{
    @Test
    public void testParseNumericRank()
    {
        Rank rank = CardParser.parseRank("10");
        Assert.assertEquals(Rank.TEN, rank);
    }

    @Test
    public void testParseSpecialRank()
    {
        Rank rank = CardParser.parseRank("q");
        Assert.assertEquals(Rank.QUEEN, rank);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidRank()
    {
        CardParser.parseRank("14");
    }

    @Test
    public void testParseSuit()
    {
        Suit suit = CardParser.parseSuit("s");
        Assert.assertEquals(Suit.SPADES, suit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidSuit()
    {
        Suit suit = CardParser.parseSuit("not a suit");
    }

    @Test
    public void testParseCardNumericCard()
    {
        Card card = CardParser.parse("2c");
        Assert.assertEquals(Suit.CLUBS, card.getSuit());
        Assert.assertEquals(Rank.TWO, card.getRank());
    }

    @Test
    public void testParseCardSpecialCard()
    {
        Card card = CardParser.parse("js");
        Assert.assertEquals(Suit.SPADES, card.getSuit());
        Assert.assertEquals(Rank.JACK, card.getRank());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseInvalidInput()
    {
        CardParser.parse("not a card");
    }
}
