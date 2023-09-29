interface AutoSpecifications{

    fun tuning()

    fun changeCollor(collor: String)
}

interface AirplaneSpecifications{

    fun increaseCapacity()
}

//val test: ()->()->Unit = {{ println("test") }}

sealed class Transport(val transportType:String, val price:Int, open val capacity:Int){


    fun Buy(){
        println("$transportType bought for $price $")
    }


    abstract class Automobile(price: Int, capacity: Int, var maxSpeed:Int, var horsepower:Int): Transport("Automobile", price, capacity){
        var collor:String = "Black"
    }
    abstract class Airplane(price: Int, capacity: Int): Transport("Airplane", price, capacity), AirplaneSpecifications
    abstract class Ship(capacity: Int): Transport("Ship", 1_000_000_000, capacity)


}

class Train: Transport("Train", 11_160_000, 800){
    var locomotive:Int = 1
    var train_carriage:Int = 20

    override var capacity: Int = 800

    fun addTrainCarriage(){
        train_carriage++
        capacity+=40
        println("You have connected the carriage. New capacity $capacity people.")
    }

    fun removeTrainCarriage(){
        if (train_carriage>=1) {
            train_carriage--
            capacity -= 40
            println("You have disconnected the carriage. New capacity\n $capacity people.")
        }
        else println("You can't disconnect something that isn't there\n!")
    }
}

class CruiseShip(var cruise:String): Transport.Ship(5400){

    fun changeCruise(cruise: String){
        this.cruise = cruise
        println("The cruise will take place in the $cruise")
    }
}
class CargoShip(var cargoType:String = "Oil", var cargoCapacity:Int = 250_000): Transport.Ship(0){

}

class Boeing(override var capacity: Int = 550): Transport.Airplane(120_000_000, capacity){
    override fun increaseCapacity() {
        capacity+=100
        println("You've shrunk the legroom and now the plane can fit $capacity people!")
    }

}
class AirbusA380(override var capacity: Int = 850): Transport.Airplane(480_000_000, capacity){
    override fun increaseCapacity() {
        capacity+=150
        println("You've shrunk the legroom and now the plane can fit $capacity people!")
    }

}

class BMW() : Transport.Automobile(50_000, 3,  280, 340), AutoSpecifications{

    override fun tuning() {
        maxSpeed+=40
        horsepower+=50
        println("\n" +
                "After tuning, BMW has the following characteristics::\n\tMaximum speed $maxSpeed km/h\n \n\tpower $horsepower hp")
    }

    override fun changeCollor(collor: String) {
        this.collor = collor
        println("New BMW color now\n $collor (at least we'll try to find that color :) )")

    }


}
class Mercedes() : Transport.Automobile(60_000, 5,  220, 170), AutoSpecifications{



    override fun tuning() {
        maxSpeed+=30
        horsepower+=80
        println("\n" +
                "After tuning, Mercedes has the following characteristics::\n\tMaximum speed $maxSpeed km/h\n \n\tpower $horsepower hp")
    }

    override fun changeCollor(collor: String) {
        this.collor = collor
        println("New Mercedes color now\n $collor (at least we'll try to find that color :) )")

    }
}
class Lada(): Transport.Automobile(10_000, 4, 140, 90){}

//singleton
object TransportCompany{
    var name:String = "Kotlin Transport Company"
    var transport = mutableListOf<Transport>()
    var expenses:Long = 0


}
fun main() {
    var input : String
    println("Hi, friend! \nI need to run now, but I urgently need to order transport for our transport company! \nI leave all purchasing decisions up to you! My Assistant will help you!")
    println("...\n...\n...\n")
    println("Good afternoon I will help you with ordering transport! \n It's time to start making decisions!")
    while (true){
        println("What should we order now?\nTrain\nAutomobile\nAirplane\nShip\nIf you want to finish just say 'finish'\n")
        input = readln()
        when(input){
            "Train" -> {
                val train = Train()
                while (true) {
                    println("We can ADD train carriage or REMOVE train carriage. With any other answer, I will consider that the order has been completed!")
                    input = readln()
                    when(input){
                        "ADD" -> train.addTrainCarriage()
                        "REMOVE" -> train.removeTrainCarriage()
                        else -> break
                    }
                }
                println("Great! I'm sending a order: \nprice ${train.price} $\n capacity ${train.capacity} people")
                TransportCompany.transport.add(train)
                TransportCompany.expenses+=train.price
            }
            "Ship" -> {
                println("What kind of ship will we order? Cruise or Cargo? Any other answer will assume that we are ordering a Cruise.")
                input = readln()
                when(input){
                    "Cargo" -> {
                        val ship = CargoShip()
                        println("Great! I'm sending a order: \nprice ${ship.price} $\n cargo capacity ${ship.cargoCapacity} t")
                        TransportCompany.transport.add(ship)
                        TransportCompany.expenses+=ship.price
                    }
                    else -> {
                        println("Choose which cruise we will go on: \nPacific Ocean\nMediterranean Sea")
                        input = readln()
                        var ship:CruiseShip
                        when(input){
                            "Pacific Ocean" -> {
                                ship = CruiseShip("Pacific Ocean")
                            }
                            "Mediterranean Sea" -> {
                                ship = CruiseShip("Mediterranean Sea")
                            }
                            else ->{
                                ship = CruiseShip("Pacific Ocean")
                                println("Since you can't decide, it will be Pacific Ocean")
                            }
                        }
                        println("Great! I'm sending a order: \nprice ${ship.price} $\n capacity ${ship.capacity} people")
                        TransportCompany.transport.add(ship)
                        TransportCompany.expenses+=ship.price
                    }
                }
            }
            "Airplane" ->{
                println("Which one will we take? Boeing or AirbusA380?")
                input = readln()
                when(input){
                    "Boeing" -> {
                        val airplane = Boeing()
                        println("A good choice! We can increase the number of places. Just say YES")
                        input = readln()
                        if (input=="YES"){
                            airplane.increaseCapacity()
                        }
                        else println("No so no\n")

                        println("Great! I'm sending a order: \nprice ${airplane.price} $\n capacity ${airplane.capacity} people")
                        TransportCompany.transport.add(airplane)
                        TransportCompany.expenses+=airplane.price
                    }
                    "AirbusA380" ->{
                        val airplane = AirbusA380()
                        println("A good choice! We can increase the number of places. Just say YES")
                        input = readln()
                        if (input=="YES"){
                            airplane.increaseCapacity()
                        }
                        else println("No so no\n")

                        println("Great! I'm sending a order: \nprice ${airplane.price} $\n capacity ${airplane.capacity} people")
                        TransportCompany.transport.add(airplane)
                        TransportCompany.expenses+=airplane.price
                    }
                    else -> {
                        val airplane = AirbusA380()
                        println("If you can't choose, then I will do it. We can increase the number of places. Just say YES")
                        input = readln()
                        if (input=="YES"){
                            airplane.increaseCapacity()
                        }
                        else println("No so no\n")

                        println("Great! I'm sending a order: \nprice ${airplane.price} $\n capacity ${airplane.capacity} people")
                        TransportCompany.transport.add(airplane)
                        TransportCompany.expenses+=airplane.price
                    }
                }

            }
            "Automobile" -> {
                println("Which one will we take? BMW or Mercedes?")
                input = readln()
                var automobile: Transport.Automobile
                when(input){
                    "BMW" -> automobile = BMW()
                    "Mercedes" -> automobile = Mercedes()
                    else -> {
                        println("I think you'll like Lada...")
                        automobile = Lada()

                    }
                }
                println("We can tune this car. If you want say YES")
                input = readln()
                if(input == "YES") {
                    if(automobile is BMW)automobile.tuning()
                    else if(automobile is Mercedes)automobile.tuning()
                    else println("I looked... Unfortunately, nothing will work")
                }
                println("Great! I'm sending a order: \nprice ${automobile.price} $\n capacity ${automobile.capacity} people")
                TransportCompany.transport.add(automobile)
                TransportCompany.expenses+=automobile.price

            }

            "finish" -> break
            else -> println("I do not understand. Let's try again.")
        }

    }
    println("Let's hope that the boss will like the list that we have compiled!")
    for (i in TransportCompany.transport) {
        when(i){
            is Train -> {
                println("\n ${i.transportType}:\n\tPrice ${i.price}$\n\tCapacity ${i.capacity}\n\tTrain carriage ${i.train_carriage}\n")
            }
            is Transport.Automobile -> {
                println("\n ${i.transportType}:\n\tPrice ${i.price}\$\n\tCapacity ${i.capacity}\n\tMax speed ${i.maxSpeed} km/h\n\tHorsepower ${i.horsepower}\n\tCollor ${i.collor}\n")
            }
            is Transport.Airplane -> {
                println("\n ${i.transportType}:\n\tPrice ${i.price}\$\n\tCapacity ${i.capacity}\n")
            }
            is Transport.Ship ->{
                when(i){
                    is CruiseShip -> {
                        println("\n ${i.transportType}:\n\tPrice ${i.price}\$\n\tCapacity ${i.capacity}\n")
                    }
                    is CargoShip -> {
                        println("\n ${i.transportType}:\n\tPrice ${i.price}\$\n\tCargo type ${i.cargoType}\n\tCargo capacity ${i.cargoCapacity} t")
                    }
                }
            }
        }
//        println(i.transportType)
    }
    println("We spent ${TransportCompany.expenses} $")
}




