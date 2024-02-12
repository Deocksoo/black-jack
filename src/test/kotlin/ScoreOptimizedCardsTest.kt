import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScoreOptimizedCardsTest : FreeSpec({
    "Cards의 합을 계산한다." {
        val scoreOptimizedCards = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.TWO),
            ),
        )

        scoreOptimizedCards.sumScores() shouldBe 13
    }

    "Burst 여부를 확인한다." {
        val notBurstCards = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.NINE),
                Card(CardSuit.SPADES, CardRank.LOW_ACE),
            ),
        )
        val notBurstWithAce = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.NINE),
                Card(CardSuit.SPADES, CardRank.TWO),
            ),
        )
        val burst = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.TEN),
                Card(CardSuit.DIAMONDS, CardRank.TEN),
                Card(CardSuit.SPADES, CardRank.TWO),
            ),
        )

        notBurstCards.isBurst() shouldBe false
        notBurstWithAce.isBurst() shouldBe false
        burst.isBurst() shouldBe true
    }

    "21을 넘지 않으면서 score가 가장 높도록 카드를 선택한다." {
        val scoreOptimizedCards1 = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.TEN),
                Card(CardSuit.SPADES, CardRank.TWO),
            ),
        )
        val scoreOptimizedCards2 = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.DIAMONDS, CardRank.HIGH_ACE),
            ),
        )
        val scoreOptimizedCards3 = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.DIAMONDS, CardRank.HIGH_ACE),
                Card(CardSuit.HEARTS, CardRank.HIGH_ACE),
            ),
        )
        val scoreOptimizedCards4 = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.DIAMONDS, CardRank.HIGH_ACE),
                Card(CardSuit.HEARTS, CardRank.HIGH_ACE),
                Card(CardSuit.CLUBS, CardRank.HIGH_ACE),
            ),
        )

        scoreOptimizedCards1.sumScores() shouldBe 13
        scoreOptimizedCards2.sumScores() shouldBe 12
        scoreOptimizedCards3.sumScores() shouldBe 13
        scoreOptimizedCards4.sumScores() shouldBe 14
    }

    "새로운 카드 한 장을 추가해도 burst가 아니라면, 그대로 추가한다." {
        val scoreOptimizedCards = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.TWO),
            ),
        )

        val newOptimizedCards = scoreOptimizedCards.plus(Card(CardSuit.SPADES, CardRank.TWO))
        newOptimizedCards.sumScores() shouldBe 15
    }

    "새로운 카드 한 장을 추가하면 burst가 되는 경우, HIGH_ACE가 있다면 HIGH_ACE 하나를 LOW_ACE로 변경한 뒤에 추가한다." {
        val scoreOptimizedCards = ScoreOptimizedCards.of(
            listOf(
                Card(CardSuit.SPADES, CardRank.HIGH_ACE),
                Card(CardSuit.SPADES, CardRank.TEN),
            ),
        )

        val newOptimizedCards = scoreOptimizedCards.plus(Card(CardSuit.SPADES, CardRank.TWO))
        newOptimizedCards.sumScores() shouldBe 13
    }
})
