package utils

import java.io.File

object Utils {
    val areArgumentsValid = { args: List<String> -> args.size == 2 && args.first().isNotBlank() && args.last().isNotBlank() }
    val isFileAvailable = { name: String -> File(name).exists() }
    const val WORD_SIZE = 5

    fun isWordValid(word: String): Boolean {
        word.apply {
            return length == WORD_SIZE && toSet().size == length && !contains(Regex("[^A-Za-z]"))
        }
    }

    val isInputTheCorrectLength = { s: String -> s.length == WORD_SIZE }
    val isInputValid = { s: String -> !s.contains(Regex("[^A-Za-z]")) }
    val inputHasDuplicates = { s: String -> s.toSet().size != s.length }

}

fun String.isExit() = this.equals("exit", true)

object TEXTS {
    const val GAME_TITLE = "Words Virtuoso\n"
    const val WRONG_NUMBER_OF_ARGS = "Error: Wrong number of arguments."
    const val INPUT_WRONG_LENGTH = "The input isn't a 5-letter word."
    const val INPUT_IS_INVALID = "One or more letters of the input aren't valid."
    const val INPUT_HAS_DUPLICATES = "The input has duplicate letters."
    const val INPUT_NOT_IN_WORDS_FILE = "The input word isn't included in my words list."
    const val GET_INPUT_MESSAGE = "Input a 5-letter word:"
    const val GAME_OVER = "The game is over."
    const val UNDERSCORE = "_"
    const val CORRECT_ANSWER = "Correct!"
    val fileUnavailable = {
            wordsFile: Boolean, name: String -> "Error: The" + (if (wordsFile) " " else " candidate ") + "words file $name doesn't exist." }

    val invalidWordsMessage = {
            count: Int, fileName: String -> "Error: $count invalid words were found in the $fileName file."
    }

    val invalidCandidateWords = {
            count: Int, fileName: String -> "Error: $count candidate words are not included in the $fileName file."
    }
}
