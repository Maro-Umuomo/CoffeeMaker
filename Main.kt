package machine

const val WATER = 200
const val MILK = 50
const val COFFEE_BEANS = 15

fun getWater(cups: Int) = cups * WATER
fun getMilk(cups: Int) = cups * MILK
fun getBeans(cups: Int) = cups * COFFEE_BEANS

fun main() {
    println("Write how many cups of coffee you will need:")
    val userInput = readln().toInt()
    println("""
            For ${userInput} cups of coffee you will need:
${getWater(userInput)} ml of water
${getMilk(userInput)} ml of milk
${getBeans(userInput)} g of coffee beans
""")
}
