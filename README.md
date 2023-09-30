# Transport Company
Cоздана иерархия классов для описания нескольких видов транспорта.
sealed class Transport имеет поля transportType, price, capacity и имеет четыре наследника:
- class Train
- abstract class Airplane
- abstract class Ship
- abstract class Automobile

Train имеет дополнительными полями locomotive и train_carriag и функции addTrainCarriage() и removeTrainCarriage(), конечный сласс.

Airplane реализует интерфейс AirplaneSpecifications, имеет два наследни:
- Boeing конечный класс
- AirbusA380 конечный класс
  
Ship имеет два наследника:
- CruiseShip имеет дополнительное поле cruise и функцию changeCruise(cruise: String), конечный класс
- CargoShip имеет дополнительные поля cargoType и cargoCapacity, конечный класс

Automobile имеет дополнительные поля maxSpeed и horsepower, имеет три наследника:
- BMW реализует интерфейс AutoSpecifications с функциями tuning() и changeCollor(collor: String), конечный класс
- Mercedes реализует интерфейс AutoSpecifications с функциями tuning() и changeCollor(collor: String), конечный класс
- Lada конечный класс

object TransportCompany хранит в себе название компании "Kotlin Transport Company", список транспорта и потраченную сумму.

Реализован пользовательский интерфейс.
Пользователь помогает составить список транспорта на покупку в транспортной компании. После выбора конкретного транспорта пользователь может его модифицировать, если такое предусмотренно. После завершения составления списка пользователь увидит список, который он составил и сколько денег он потратил.
