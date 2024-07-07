package wordsvirtuoso

import utils.Messages
import java.io.File
import kotlin.system.exitProcess
import utils.Utils.areArgumentsValid
import utils.Utils.isFileAvailable
import utils.Utils.isWordValid

class CandidateWordsValidator (args: List<String> = emptyList()){
    private val wordsFile: File
    private val candidateWordsFile: File

    init {
        if (!areArgumentsValid(args)) {
            println(Messages.WRONG_NUMBER_OF_WORDS)
            exitProcess(0)
        } else if (!isFileAvailable(args.first())) {
            println(Messages.fileUnavailable(true, args.first()))
            exitProcess(0)
        } else if(!isFileAvailable(args.last())) {
            println(Messages.fileUnavailable(true, args.last()))
            exitProcess(0)
        }
        wordsFile = File("${args.first()}.txt")
        candidateWordsFile = File("${args.last()}.txt")
    }

    fun startValidating() {
        validateFile(wordsFile)
        validateFile(candidateWordsFile)
        validateCandidateWords()
        println(Messages.SUCCESSFUL_MESSAGE)
    }

    private fun validateFile(file: File) {
        var invalidWords = 0
        file.readLines().forEach {
            if (!isWordValid(it)) {
                invalidWords++
            }
        }
        if (invalidWords > 0) {
            println(Messages.invalidWordsMessage(invalidWords, file.name))
            exitProcess(0)
        }
    }

    private fun validateCandidateWords() {
        val words = wordsFile.readLines()
        var counter = 0
        candidateWordsFile.readLines().forEach {
            if (!words.contains(it)) {
                counter++
            }
        }
        if (counter > 0) {
            println(Messages.invalidCandidateWords(counter, candidateWordsFile.name))
            exitProcess(0)
        }
    }
}

fun main(args: Array<String>) {
    val validator = CandidateWordsValidator(args.toList())
}

