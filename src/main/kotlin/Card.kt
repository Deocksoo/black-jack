class Card(
    private val suit: CardSuit,
    private val rank: CardRank,
) {
    fun isHighAce() = rank == CardRank.HIGH_ACE
}
