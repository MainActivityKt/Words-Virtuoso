package wordsvirtuoso

import utils.Utils
import utils.Utils.isWordValid
import java.io.File
import kotlin.system.exitProcess

class TextFileValidator {
    private val inputFile: File
    private var invalidWordsNum: Int

    init {
        println("Input the words file:")
        val fileName = readln()
        inputFile = File(fileName)
        if (inputFile.exists()) {
            invalidWordsNum = 0
        } else {
            println("Error: The words file $fileName doesn't exist.")
            exitProcess(0)
        }
    }

    fun countValidWords() {
        inputFile.readLines().forEach {
            if (isWordValid(it)) {
                invalidWordsNum++
            }
        }
    }

    fun printValidWords() {
        println(if (invalidWordsNum == 0) "All words are valid!" else "Warning: $invalidWordsNum invalid words were found in the ${inputFile.name} file.")
    }


}

fun main() {
    val validator = TextFileValidator()
    validator.countValidWords()
    validator.printValidWords()
}