import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import utils.Messages
import wordsvirtuoso.CandidateWordsValidator
import kotlin.test.assertEquals


class CandidateWordsValidatorTest {
    private lateinit var wordsFilePath: String
    private lateinit var candidatesFilePath: String
    private lateinit var invalidWordsFilePath: String

    init {
        wordsFilePath = "C:\\Users\\Safiu\\IdeaProjects (Kotlin)\\Words-Virtuoso\\src\\test\\kotlin\\words.txt"
        candidatesFilePath = "C:\\Users\\Safiu\\IdeaProjects (Kotlin)\\Words-Virtuoso\\src\\test\\kotlin\\candidates.txt"
        invalidWordsFilePath = "C:\\Users\\Safiu\\IdeaProjects (Kotlin)\\Words-Virtuoso\\src\\test\\kotlin\\invalidWords.txt"


    }

    @Test
    fun when_givenLessThanTwoArguments_returnExpectedResult() {
        val args =listOf("insufficientArgs.txt")
        val validator = CandidateWordsValidator(args)
        validator.argumentsAreValid()
        assertEquals(validator.message, Messages.WRONG_NUMBER_OF_ARGS)
    }

    @Test
    @DisplayName("When gives more than two args, return WRONG_NUMBER_OF_ARGS message")
    fun when_givenMoreThanTwoArguments_returnExpectedResult() {
        val args =listOf("args1", "arg2", "arg2")
        val validator = CandidateWordsValidator(args)
        validator.argumentsAreValid()
        assertEquals(validator.message, Messages.WRONG_NUMBER_OF_ARGS)
    }

    @Test
    @DisplayName("When given no argument, return WRONG_NUMBER_OF_ARGS message")
    fun when_givenZeroArguments_returnExpectedResult() {
        val args = emptyList<String>()
        val validator = CandidateWordsValidator(args)
        validator.argumentsAreValid()
        assertEquals(validator.message, Messages.WRONG_NUMBER_OF_ARGS)
    }

    @Test
    fun when_givenUnavailableWordsFile_returnFileUnavailableMessage() {
        val args = listOf("notExists.txt", "candidates.txt")
        val validator = CandidateWordsValidator(args)
        validator.filesAreAvailable()
        assertEquals(validator.message, Messages.fileUnavailable(true, args.first()))
    }

    @Test
    fun when_givenUnavailableCandidatesFile_returnFileUnavailableMessage() {
        val args = listOf(wordsFilePath, "candidates.txt")
        val validator = CandidateWordsValidator(args)
        validator.filesAreAvailable()
        assertEquals(validator.message, Messages.fileUnavailable(false, args.last()))
    }

    @Test
    fun when_givenInvalidWordsFile_returnInvalidWordsMessage() {
        val args = listOf(invalidWordsFilePath, candidatesFilePath)
        val validator = CandidateWordsValidator(args)
        validator.filesAreValid()
        assertEquals(validator.message, Messages.invalidWordsMessage(5, "invalidWords.txt"))
    }

    @Test
    fun when_givenInvalidCandidatesFile_returnInvalidWordsMessage() {
        val args = listOf(wordsFilePath, invalidWordsFilePath)
        val validator = CandidateWordsValidator(args)
        validator.filesAreValid()
        assertEquals(validator.message, Messages.invalidWordsMessage(5, "invalidWords.txt"))
    }


}