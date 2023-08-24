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

fun main() {

    println("Write how many ml of water the coffee machine has:")
    val waterLeft = readln().toInt()
    println("Write how many ml of milk the coffee machine has:")
    val milkLeft = readln().toInt()
    println("Write how many grams of coffee beans the coffee machine has:")
    val beansLeft = readln().toInt()

    val coffeeStock = mutableListOf(waterLeft, milkLeft, beansLeft)
    val possibleCups = mutableListOf<Int>()

    for (index in coffeeStock.indices) {
        when (index) {
            0 -> possibleCups.add(getCupsWater(coffeeStock[index]))
            1 -> possibleCups.add(getCupsMilk(coffeeStock[index]))
            2 -> possibleCups.add(getCupsBeans(coffeeStock[index]))
        }
    }
    val maxCup = possibleCups.min()
    println("Write how many cups of coffee you will need:")
    val cups = readln().toInt()

    println(when {
        cups > maxCup -> "No, I can make only ${maxCup} cups of coffee"
        cups == maxCup -> "Yes, I can make that amount of coffee"
        cups < maxCup -> "Yes, I can make that amount of coffee (and even ${maxCup - cups} more than that)"
        else-> "Yes, I can make that amount of coffee"
    })
}
