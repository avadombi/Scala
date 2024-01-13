import scala.io.StdIn.readLine

@main def getNumberSign() = 
    println("Enter a number:")
    val x = readLine().toInt
    if x < 0 then
        println("negative")
    else if x == 0 then
        println("zero")
    else
        println("positive")



