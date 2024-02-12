class Card(
    val suit: CardSuit,
    private val rank: CardRank,
) {
    fun score() = rank.score
    fun isHighAce() = rank == CardRank.HIGH_ACE
}
