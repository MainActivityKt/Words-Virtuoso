import org.junit.jupiter.api.Test
import wordsvirtuoso.CandidateWordsValidator
import kotlin.test.assertTrue

class CandidateWordsValidatorTest {
    @Test
    fun when_givenLessThanTwoArguments_printExpectedResult() {
        val validator = CandidateWordsValidator(listOf("notExists.txt"))
        validator.argumentsAreValid()



    }


}