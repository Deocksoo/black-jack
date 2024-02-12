import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CardTest : FreeSpec({
    "Rank가 HIGH_ACE인지 확인한다." {
        val card = Card(CardSuit.SPADES, CardRank.HIGH_ACE)
        card.isHighAce() shouldBe true
    }
})
