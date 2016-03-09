package loodakrawa.blackjack;

/**
 * Representation of a French playing card
 */
public class Card
{
    private Rank rank;
    private Suit suit;

    public Card(Rank value, Suit suit)
    {
        this.rank = value;
        this.suit = suit;
    }

    public Rank getRank()
    {
        return rank;
    }

    public Suit getSuit()
    {
        return suit;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card other = (Card) o;
        return rank == other.rank && suit == other.suit;
    }

    @Override
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 23 + rank.hashCode();
        hash = hash * 23 + suit.hashCode();
        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(rank).append(" of ").append(suit);
        return sb.toString();
    }
}
