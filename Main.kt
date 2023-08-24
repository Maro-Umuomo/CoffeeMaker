package machine

const val WATER = 200
const val MILK = 50
const val COFFEE_BEANS = 15

fun getWater(cups: Int) = cups * WATER
fun getMilk(cups: Int) = cups * MILK
fun getBeans(cups: Int) = cups * COFFEE_BEANS
fun getCupsWater(waterLeft: Int) = waterLeft / WATER
fun getCupsMilk(milkLeft: Int) = milkLeft / MILK
fun getCupsBeans(beansLeft: Int) = beansLeft / COFFEE_BEANS

data class Espresso(
    val water: Int = 250,
    val milk: Int = 0,
    val beans: Int = 16,
    val price: Int = 4
)
val ESPRESSO = Espresso()

data class Latte(
    val water: Int = 350,
    val milk: Int = 75,
    val beans: Int = 20,
    val price: Int = 7
)
val LATTE = Latte()

data class Cappuccino(
    val water: Int = 200,
    val milk: Int = 100,
    val beans: Int = 12,
    val price: Int = 6
)
val CAPPUCCINO = Cappuccino()

data class CoffeeMachine(
    var water: Int = 400,
    var beans: Int = 120,
    var cups: Int = 9,
    var cash: Int = 550,
    var milk: Int = 540,

    )
val COFFEEMACHINE = CoffeeMachine()

fun printStatus()
{
    println(
        """
        The coffee machine has:
        ${COFFEEMACHINE.water} ml of water
        ${COFFEEMACHINE.milk} ml of milk
        ${COFFEEMACHINE.beans} g of coffee beans
        ${COFFEEMACHINE.cups} disposable cups
        ${'$'}${COFFEEMACHINE.cash} of money 
        """.trimIndent()
    )
}

fun calculateBalance(coffeeType: Int)
{
    when (coffeeType) {
        1 -> {
            COFFEEMACHINE.water -= ESPRESSO.water
            COFFEEMACHINE.milk -= ESPRESSO.milk
            COFFEEMACHINE.beans -= ESPRESSO.beans
            COFFEEMACHINE.cash += ESPRESSO.price
            --COFFEEMACHINE.cups
        }
        2 -> {
            COFFEEMACHINE.water -= LATTE.water
            COFFEEMACHINE.milk -= LATTE.milk
            COFFEEMACHINE.beans -= LATTE.beans
            COFFEEMACHINE.cash += LATTE.price
            --COFFEEMACHINE.cups
        }
        3 -> {
            COFFEEMACHINE.water -= CAPPUCCINO.water
            COFFEEMACHINE.milk -= CAPPUCCINO.milk
            COFFEEMACHINE.beans -= CAPPUCCINO.beans
            COFFEEMACHINE.cash += CAPPUCCINO.price
            --COFFEEMACHINE.cups
        }
    }
}

fun fillMachine()
{
    println("Write how many ml of water you want to add: ")
    var userInput = readln().toInt()
    COFFEEMACHINE.water += userInput
    println("Write how many ml of milk you want to add: ")
    userInput = readln().toInt()
    COFFEEMACHINE.milk += userInput
    println("Write how many grams of coffee beans you want to add: ")
    userInput = readln().toInt()
    COFFEEMACHINE.beans += userInput
    println("Write how many disposable cups you want to add:")
    userInput = readln().toInt()
    COFFEEMACHINE.cups += userInput
    println()
    printStatus()
}

fun buyCoffee()
{
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ")
    val choice = readln().toInt()
    calculateBalance(choice)
    println()
    printStatus()
}

fun takeMoney()
{
    println("I gave you ${'$'}${COFFEEMACHINE.cash}")
    COFFEEMACHINE.cash = 0
    println()
    printStatus()
}

fun main() {

    printStatus()
    println("\nWrite action (buy, fill, take): ")
    val action = readln()

    when (action) {
        "buy" -> buyCoffee()
        "fill" -> fillMachine()
        "take" -> takeMoney()
    }
}
