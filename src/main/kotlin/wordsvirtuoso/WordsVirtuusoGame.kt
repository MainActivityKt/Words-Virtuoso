package wordsvirtuoso

import utils.TEXTS
import utils.Utils.inputHasDuplicates
import utils.Utils.isInputTheCorrectLength
import utils.Utils.isInputValid
import utils.isExit
import kotlin.random.Random
import kotlin.system.exitProcess

class WordsVirtuosoGame(args: List<String> = emptyList()) : CandidateWordsValidator(args) {
    private lateinit var secretWord: String
    private lateinit var inputWord: String
    val candidateWords = mutableListOf<String>()

    fun startGame() {
        candidateWords.addAll(candidateWordsFile.readLines())
        secretWord = candidateWords[Random.nextInt(0, candidateWords.size)]
        println(secretWord)
        println(TEXTS.GET_INPUT_MESSAGE)
        inputWord = readln()

        while (!inputWord.isExit() && !inputWord.equals(secretWord, true)) {
            if (!inputIsValid(inputWord)) {
                println(message)
            } else {
                for (i in inputWord.indices) {
                    if (secretWord.lowercase().contains(inputWord[i].lowercase())) {
                        print(if (secretWord[i].equals(inputWord[i],true)) inputWord[i].uppercase() else inputWord[i].lowercase())
                    } else {
                        print(TEXTS.UNDERSCORE)
                    }
                }
            }

            println()
            println(TEXTS.GET_INPUT_MESSAGE)
            inputWord = readln()
        }
        message = if (inputWord.isExit()) TEXTS.GAME_OVER else TEXTS.CORRECT_ANSWER
    }

    private fun inputIsValid(input: String): Boolean {
        if (!isInputTheCorrectLength(input)) {
            // if input isn't a five-letter word set the message to: The input isn't a 5-letter word.
            message = TEXTS.INPUT_WRONG_LENGTH
            return false
        } else if (!isInputValid(input)) {
            // has at least one invalid character (that is, not in A to Z), set message to: One or more letters of the input aren't valid.
            message = TEXTS.INPUT_IS_INVALID
            return false
        } else if (inputHasDuplicates(input)) {
            // has duplicate letters, set message to: The input has duplicate letters.
            message = TEXTS.INPUT_HAS_DUPLICATES
            return false
        } else if (words.none { it.contentEquals(input, true) }) {
            // isn't in the words' list, set message to: The input word isn't included in my words list.
            message = TEXTS.INPUT_NOT_IN_WORDS_FILE
            return false
        }
        return true
    }

}

fun main(args: Array<String>) {
    val validator = WordsVirtuosoGame(args.toList())
    if (validator.argumentsAreValid() && validator.filesAreAvailable() && validator.filesAreValid() && validator.candidatesAreValid()) {
        validator.message = TEXTS.GAME_TITLE
        println(validator.message)
        validator.startGame()
    }
    println(validator.message)
}