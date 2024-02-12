class ScoreOptimizedCards private constructor(
    private val cards: List<Card>,
) {
    companion object {
        const val BURST_THRESHOLD = 21

        fun of(cards: List<Card>): ScoreOptimizedCards {
            if (cards.sumOf { it.score() } <= BURST_THRESHOLD) {
                return ScoreOptimizedCards(cards)
            }

            val firstCard = cards.first()
            return cards.drop(1)
                .fold(ScoreOptimizedCards(listOf(firstCard))) { acc, card ->
                    acc.plus(card)
                }
        }
    }

    fun sumScores(): Int {
        return cards.sumOf { it.score() }
    }

    fun isBurst() = sumScores() > BURST_THRESHOLD

    fun plus(card: Card): ScoreOptimizedCards {
        val newCards = cards.plus(card)

        if (!newCards.isBurst()) {
            return ScoreOptimizedCards(newCards)
        }

        return newCards.find { it.isHighAce() }?.let {
            newCards.minus(it).plus(Card(it.suit, CardRank.LOW_ACE))
        }?.let {
            ScoreOptimizedCards(it)
        } ?: ScoreOptimizedCards(newCards)
    }
}

private fun List<Card>.isBurst(): Boolean {
    return sumOf { it.score() } > ScoreOptimizedCards.BURST_THRESHOLD
}
