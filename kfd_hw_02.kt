interface Engine{

}

sealed class Transport(val transportType:String, val price:Int, open val capacity:Int){


    fun Buy(){
        println("$transportType куплен за $price рублей")
    }


    abstract class Automobile(price: Int, capacity: Int): Transport("Automobile", price, capacity)
    abstract class Airplane(price: Int, capacity: Int): Transport("Airplane", price, capacity)
    abstract class Ship(price: Int, capacity: Int): Transport("Ship", price, capacity)


}
class Train: Transport("Train", 1_116_000_000, 800){
    var locomotive:Int = 1
    var train_carriage:Int = 20

    override var capacity: Int = 800

    fun addTrainCarriage(){
        train_carriage++
        capacity+=40
    }
}


class BMW : Transport.Automobile(5_000_000, 5){


}

//singleton
object TransportCompany{


}
fun main() {


}