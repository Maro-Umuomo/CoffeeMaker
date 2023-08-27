package machine

import kotlin.system.exitProcess

data class Espresso(
    val water: Int = 250,
    val milk: Int = 0,
    val beans: Int = 16,
    val price: Int = 4
)

data class Latte(
    val water: Int = 350,
    val milk: Int = 75,
    val beans: Int = 20,
    val price: Int = 7
)

data class Cappuccino(
    val water: Int = 200,
    val milk: Int = 100,
    val beans: Int = 12,
    val price: Int = 6
)

data class Stock(
    var water: Int = 400,
    var beans: Int = 120,
    var cups: Int = 9,
    var cash: Int = 550,
    var milk: Int = 540
)

class coffeeMachine
{
    val espresso = Espresso()
    val latte = Latte()
    val cappuccino = Cappuccino()
    val stock = Stock()

    private fun printStatus()
    {
        println()
        println(
            """
        The coffee machine has:
        ${stock.water} ml of water
        ${stock.milk} ml of milk
        ${stock.beans} g of coffee beans
        ${stock.cups} disposable cups
        ${'$'}${stock.cash} of money 
        """.trimIndent()
        )
        println()
    }

    private fun calculateBalance(coffeeType: String)
    {
        when (coffeeType) {
            "1" -> {
                stock.water -= espresso.water
                stock.milk -= espresso.milk
                stock.beans -= espresso.beans
                stock.cash += espresso.price
                --stock.cups
            }
            "2" -> {
                stock.water -= latte.water
                stock.milk -= latte.milk
                stock.beans -= latte.beans
                stock.cash += latte.price
                --stock.cups
            }
            "3" -> {
                stock.water -= cappuccino.water
                stock.milk -= cappuccino.milk
                stock.beans -= cappuccino.beans
                stock.cash += cappuccino.price
                --stock.cups
            }
            else -> return
        }
    }

    private fun espressoRequest(): IntArray {
        val ingredients = IntArray(3)
        when {
            stock.water < espresso.water -> ++ingredients[0]
            stock.milk < espresso.milk -> ++ingredients[1]
            stock.beans < espresso.beans -> ++ingredients[2]
        }
        return ingredients
    }

    private fun latteRequest(): IntArray {
        val ingredients = IntArray(3)
        when {
            stock.water < latte.water -> ++ingredients[0]
            stock.milk < latte.milk -> ++ingredients[1]
            stock.beans < latte.beans -> ++ingredients[2]
        }
        return ingredients
    }

    private fun cappuccinoRequest(): IntArray {
        val ingredients = IntArray(3)
        when {
            stock.water < cappuccino.water -> ++ingredients[0]
            stock.milk < cappuccino.milk -> ++ingredients[1]
            stock.beans < cappuccino.beans -> ++ingredients[2]
        }
        return ingredients
    }

    private fun fillMachine()
    {
        println("\nWrite how many ml of water you want to add: ")
        var userInput = readln().toInt()
        stock.water += userInput
        println("Write how many ml of milk you want to add: ")
        userInput = readln().toInt()
        stock.milk += userInput
        println("Write how many grams of coffee beans you want to add: ")
        userInput = readln().toInt()
        stock.beans += userInput
        println("Write how many disposable cups you want to add: ")
        userInput = readln().toInt()
        stock.cups += userInput
        println()
    }

    private fun requestPossible(choice: String): IntArray{
        var ingredients = IntArray(3)
        when (choice.toInt()) {
            1 -> ingredients = espressoRequest()
            2 -> ingredients = latteRequest()
            3 -> ingredients = cappuccinoRequest()
        }
        return ingredients
    }

    private fun buyCoffee()
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
    }

    private fun takeMoney()
    {
        println("\nI gave you ${'$'}${stock.cash}")
        stock.cash = 0
        println()
    }

    fun control()
    {
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

}

fun main()
{
    val coffeeVendor = coffeeMachine()
    coffeeVendor.control()
}