package wordsvirtuoso

import utils.TEXTS
import java.io.File
import utils.Utils.areArgumentsValid
import utils.Utils.isFileAvailable
import utils.Utils.isWordValid

open class CandidateWordsValidator (private val args: List<String> = emptyList()){
    private lateinit var wordsFile: File
    protected lateinit var candidateWordsFile: File
    lateinit var message: String
    val words = mutableListOf<String>()


    fun argumentsAreValid(): Boolean {
        if (!areArgumentsValid(args)) {
            message = TEXTS.WRONG_NUMBER_OF_ARGS
            return false
        }
        return true
    }

    fun filesAreAvailable(): Boolean {
        if (!isFileAvailable(args.first())) {
            message = TEXTS.fileUnavailable(true, args.first())
            return false
        } else if (!isFileAvailable(args.last())) {
            message = TEXTS.fileUnavailable(false, args.last())
            return false
        }
        return true
    }

    fun filesAreValid(): Boolean {
        wordsFile = File(args.first())
        candidateWordsFile = File(args.last())
        return isFileValid(wordsFile) && isFileValid(candidateWordsFile)
    }

    fun candidatesAreValid(): Boolean {
        return validateCandidateWords()
    }

    private fun isFileValid(file: File): Boolean {
        var invalidWords = 0
        file.readLines().forEach {
            if (!isWordValid(it)) {
                invalidWords++
            }
        }
        if (invalidWords > 0) {
            message = TEXTS.invalidWordsMessage(invalidWords, file.name)
            return false
        }
        return true
    }

    private fun validateCandidateWords() : Boolean{
        words.addAll(wordsFile.readLines())
        var counter = 0
        candidateWordsFile.readLines().forEach {
            if (words.none { list -> list.contains(it, true) }) {
                counter++
            }
        }
        if (counter > 0) {
            message = TEXTS.invalidCandidateWords(counter, wordsFile.name)
            return false
        }
        return true
    }
}

fun main(args: Array<String>) {
    val validator = CandidateWordsValidator(args.toList())
    if (validator.argumentsAreValid() && validator.filesAreAvailable() && validator.filesAreValid() && validator.candidatesAreValid()) {
        validator.message = TEXTS.GAME_TITLE
    }
    println(validator.message)
}
