package utils

import java.io.File

object Utils {
    val areArgumentsValid = { args: List<String> -> args.size == 2 && args.first().isNotBlank() && args.last().isNotBlank() }
    val isFileAvailable = { name: String -> File(name).exists() }

    fun isWordValid(word: String): Boolean {
        word.apply {
            return length == 5 && toSet().size == length && !contains(Regex("[^A-Za-z]"))
        }
    }
}

object Messages {
    const val SUCCESSFUL_MESSAGE = "Words Virtuoso"
    const val WRONG_NUMBER_OF_ARGS = "Error: Wrong number of arguments."
    val fileUnavailable = {
            wordsFile: Boolean, name: String -> "Error: The" + (if (wordsFile) " " else " candidate ") + "words file $name doesn't exist." }

    val invalidWordsMessage = {
            count: Int, fileName: String -> "Error: $count invalid words were found in the $fileName file."
    }

    val invalidCandidateWords = {
            count: Int, fileName: String -> "Error: $count candidate words are not included in the $fileName file."
    }
}
