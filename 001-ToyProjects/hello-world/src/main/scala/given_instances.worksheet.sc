trait Show[A] {
  def show(value: A): String
}

given Show[Int] with {
  def show(value: Int): String = value.toString
}

given Show[String] with {
  def show(value: String): String = value
}

def printShow[A](value: A)(using showInstance: Show[A]): Unit = {
  val result = showInstance.show(value)
  println(result)
}

val intValue = 42
val stringValue = "Hello, Scala!"

printShow(intValue) // Affiche "42"
printShow(stringValue) // Affiche "Hello, Scala!"
