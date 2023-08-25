package machine

import kotlin.system.exitProcess

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
    println()
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
    println()
}

fun calculateBalance(coffeeType: String)
{
    when (coffeeType) {
        "1" -> {
            COFFEEMACHINE.water -= ESPRESSO.water
            COFFEEMACHINE.milk -= ESPRESSO.milk
            COFFEEMACHINE.beans -= ESPRESSO.beans
            COFFEEMACHINE.cash += ESPRESSO.price
            --COFFEEMACHINE.cups
        }
        "2" -> {
            COFFEEMACHINE.water -= LATTE.water
            COFFEEMACHINE.milk -= LATTE.milk
            COFFEEMACHINE.beans -= LATTE.beans
            COFFEEMACHINE.cash += LATTE.price
            --COFFEEMACHINE.cups
        }
        "3" -> {
            COFFEEMACHINE.water -= CAPPUCCINO.water
            COFFEEMACHINE.milk -= CAPPUCCINO.milk
            COFFEEMACHINE.beans -= CAPPUCCINO.beans
            COFFEEMACHINE.cash += CAPPUCCINO.price
            --COFFEEMACHINE.cups
        }
        else -> return
    }
}

fun espressoRequest(): IntArray {
    val ingredients = IntArray(3)
    when {
        COFFEEMACHINE.water < ESPRESSO.water -> ++ingredients[0]
        COFFEEMACHINE.milk < ESPRESSO.milk -> ++ingredients[1]
        COFFEEMACHINE.beans < ESPRESSO.beans -> ++ingredients[2]
    }
    return ingredients
}

fun latteRequest(): IntArray {
    val ingredients = IntArray(3)
    when {
        COFFEEMACHINE.water < LATTE.water -> ++ingredients[0]
        COFFEEMACHINE.milk < LATTE.milk -> ++ingredients[1]
        COFFEEMACHINE.beans < LATTE.beans -> ++ingredients[2]
    }
    return ingredients
}

fun cappuccinoRequest(): IntArray {
    val ingredients = IntArray(3)
    when {
        COFFEEMACHINE.water < CAPPUCCINO.water -> ++ingredients[0]
        COFFEEMACHINE.milk < CAPPUCCINO.milk -> ++ingredients[1]
        COFFEEMACHINE.beans < CAPPUCCINO.beans -> ++ingredients[2]
    }
    return ingredients
}

fun fillMachine()
{
    println("\nWrite how many ml of water you want to add: ")
    var userInput = readln().toInt()
    COFFEEMACHINE.water += userInput
    println("Write how many ml of milk you want to add: ")
    userInput = readln().toInt()
    COFFEEMACHINE.milk += userInput
    println("Write how many grams of coffee beans you want to add: ")
    userInput = readln().toInt()
    COFFEEMACHINE.beans += userInput
    println("Write how many disposable cups you want to add: ")
    userInput = readln().toInt()
    COFFEEMACHINE.cups += userInput
    println()
    //printStatus()
}

fun buyCoffee()
{
    println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    val choice = readln()

    if (choice == "back") {
        println()
        return
    }

    val availability = requestPossible(choice)

    if (availability.sum() == 0) {
        calculateBalance(choice)
        println("I have enough resources, making you a coffee!\n")
    }else
    {
        val outputPrint = availability.joinToString("")
        when(outputPrint) {
            "001" -> println("Sorry, not enough beans!\n")
            "010" -> println("Sorry, not enough milk!\n")
            "011" -> println("Sorry, not enough milk and beans!\n")
            "100" -> println("Sorry, not enough water!\n")
            "101" -> println("Sorry, not enough water and beans!\n")
            "110" -> println("Sorry, not enough water and milk!\n")
            "111" -> println("Sorry, not enough resources!\n")
            else -> println("Sorry, not enough resources!\n")
        }
    }
    //println()
    //printStatus()
}

fun requestPossible(choice: String): IntArray{
    var ingredients = IntArray(3)
    when (choice.toInt()) {
        1 -> ingredients = espressoRequest()
        2 -> ingredients = latteRequest()
        3 -> ingredients = cappuccinoRequest()
    }
    return ingredients
}

fun takeMoney()
{
    println("\nI gave you ${'$'}${COFFEEMACHINE.cash}")
    COFFEEMACHINE.cash = 0
    println()
    //printStatus()
}

fun main() {

    while (true)
    {
        println("Write action (buy, fill, take, remaining, exit): ")
        val action = readln()
        when (action) {
            "buy" -> buyCoffee()
            "fill" -> fillMachine()
            "take" -> takeMoney()
            "remaining" -> printStatus()
            "exit" -> break
        }
    }
}