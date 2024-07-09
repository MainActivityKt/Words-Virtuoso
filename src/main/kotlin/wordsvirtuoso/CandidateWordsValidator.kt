package wordsvirtuoso

import utils.Messages
import java.io.File
import kotlin.system.exitProcess
import utils.Utils.areArgumentsValid
import utils.Utils.isFileAvailable
import utils.Utils.isWordValid

class CandidateWordsValidator (private val args: List<String> = emptyList()){
    lateinit var wordsFile: File
    lateinit var candidateWordsFile: File
    lateinit var message: String

    fun argumentsAreValid(): Boolean {
        if (!areArgumentsValid(args)) {
            message = Messages.WRONG_NUMBER_OF_WORDS
            return false
        }
        return true
    }

    fun filesAreAvailable(): Boolean {
        if (!isFileAvailable(args.first())) {
            message = Messages.fileUnavailable(true, args.first())
            return false
        } else if (!isFileAvailable(args.last())) {
            message = Messages.fileUnavailable(false, args.last())
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
            message = Messages.invalidWordsMessage(invalidWords, file.name)
            return false
        }
        return true
    }

    private fun validateCandidateWords() : Boolean{
        val words = wordsFile.readLines()
        var counter = 0
        candidateWordsFile.readLines().forEach {
            if (words.none { list -> list.contains(it, true) }) {
                counter++
            }
        }
        if (counter > 0) {
            message = Messages.invalidCandidateWords(counter, wordsFile.name)
            return false
        }
        return true
    }
}

fun main(args: Array<String>) {
    val validator = CandidateWordsValidator(args.toList())
    if (validator.argumentsAreValid() && validator.filesAreAvailable() && validator.filesAreValid() && validator.candidatesAreValid()) {
        validator.message = Messages.SUCCESSFUL_MESSAGE
    }
    println(validator.message)
}
