package wordsvirtuoso

fun validateInput(input: String) {
    input.apply {
        if (length != 5) {
            println("The input isn't a 5-letter string.")
        } else if(toSet().size != length) {
            println("The input has duplicate letters.")
        } else if(contains(Regex("[^A-Za-z]"))) {
            println("The input has invalid characters.")
        } else {
            println("The input is a valid string.")
        }
    }
}
fun main() {
    println("Input a 5-letter string:")
    val input = readln()
    validateInput(input)
}