import scala.compiletime.ops.double
import fansi.Str
import java.io.IOException
import scala.util.matching.Regex.Match
val ints = List(1, 2, 3, 4, 5)
for
    i <- ints // generator
    if i > 2  // this is a guard
do
    println(i)

// multiple generator inside a for loop

for
    i <- 1 to 3
    j <- 'a' to 'c'
    if i == 2
    if j == 'b'
do
    println(s"i = $i, j = $j")


// expressions: returns something - statements: do not
// for expressions using yield
val doubles = for i <- ints yield i * 2

// capitalize the first character in each string of a list
val names = List("chris", "ed", "maurice")
val capNames = for name <- names yield name.capitalize

// another example
val fruits = List("apple", "banana", "lime", "orange")
val fruitLengths = for 
    fruit <- fruits
    if (fruit.length > 4)
yield
    fruit.length

// match expression (equivalent to switch in Java and JS)
val i = 1
i match
    case 1 => println("one")
    case 2 => println("two")
    case _ => println("other")  // default value

// as match is an expression we can retrieve some values
val result = i match
    case 1 => "one"
    case 2 =>"two"
    case _ => "other"  // default value

// other example
val result2 = i match
    case 1
        if (i == 1) => "class 1"
    case 2 if (i == 2) => "class 2"
    case 3 if (i == 3) =>
        println("Good !")
    case _ => "other"  // default value

// match as the body of a method
def getClassAsString(x: Matchable): String = x match
    case s: String => s"$s is a String"
    case i: Int => "Int"
    case d: Double => "Double"
    case i: List[?] => "List"
    case _ => "Unkown"

getClassAsString(1)
getClassAsString("Hello")
getClassAsString(List(1, 2, 3))


// try/catch/finally
def writeTexteToFile(text: String) = 
    println("OK boy !")


val text = "Hello World!"
try
    writeTexteToFile(text)
catch
    case ioe: IOException => println("Got an IOException.")
    case nfe: NumberFormatException => println("Got a NumberFormatExeption.")
finally
    println("Clean up your resources here.")


// while loop
var x = 0
while
    x < 5
do
    println(x)
    x += 1



/* 
traits and classes
traits define behaviors and cannot be instantiated
classes defines data (attributes) and behaviors (methods)
*/

trait Speaker:
    def speak(): String  // an abstract method

trait TailWagger:
    def startTail(): Unit = println("tail is wagging")
    def stopTail(): Unit = println("tail is stopped")

trait Runner:
    def startRunning(): Unit = println("I'm running")
    def stopRunning(): Unit = println("Stopped running")
 
class Dog(name: String) extends Speaker, TailWagger, Runner:
    def speak(): String = "Woof!"

class Cat(name: String) extends Speaker, TailWagger, Runner:
    def speak(): String = "Meow"
    override def startRunning(): Unit = println("Yeah ... I don't run")
    override def stopRunning(): Unit = println("No need to stop")

// let's use these classes
val d = Dog(name = "Rover")
println(d.speak())

val c = Cat("Morris")
println(c.speak())
c.startRunning()
c.stopRunning()

// create a Person's class
// in classes, field are typically mutable so we can declare them as var
class Person(var firstName: String, var lastName: String = "Unknown"):
    def printFullName() = println(s"$firstName $lastName")

val p = Person(firstName = "Pierre")
println(p.printFullName())
println(p.firstName)
println(p.lastName)
p.lastName = "Naruto"
println(p.printFullName())


// enumerations
enum CrustSize:
    case Small, Medium, Large

enum CrustType:
    case Thin, Thick, Regular

enum Topping:
    case Cheese, Pepperoni, BlackOlives, GreenOlives, Onions

import CrustSize.*  // import all values of the enum
val currentCrustSize = Small

currentCrustSize match
    case Small => println("Small crust size")
    case Medium => println("Medium crust size")
    case Large => println("Large crust size")

if currentCrustSize == Small then println("Small crust size")

// this is not an enumeratioin because the Succ case has params
enum Nat:
    case Zero
    case Succ(pred: Nat)


/* 
case classes - advantages
comes with a bunch of methods such as:
    toString: for debugging
    equals and hashCode: for structural equality
    copy: create updated copies of the object
    etc.
NOTE: its fields are immutable (val), so they values cannot be changed
 */

case class BigPerson(
    name: String,
    vocation: String
)

val bp = BigPerson("Naruto", "Boxer")
bp.name
bp.vocation
// bp.name = "DBZ" => error

// to make a change, make a copy
val bp2 = bp.copy(name = "DBZ the best")
val bp3 = bp.copy()
bp.equals(bp2)
bp.equals(bp3)
bp.equals(BigPerson("Naruto", "Boxer"))

val bp4 = new BigPerson("Aya", "Yopougon")
s"${bp4.name} de ${bp4.vocation}"

/* 
Use extension to add new method to a given Object
Here our method makeInt allows to convert the string s into
an integer using the specified base (radix)
 */

 extension (s: String) {  // s represents our String object
    def makeInt(radix: Int): Int = Integer.parseInt(s, radix)
    def printBeautifulText(name: String): Unit = println(s"$s - $name")
}

"10".makeInt(2)
"10".printBeautifulText("Naruto")


/* 
High-order function such as map of List objects
map(function) e.g. f = i => i * 2
    where i is an element of the list
    here (i * 2) is a Lambda function
 */

val a = List(1, 2, 3).map(f = i => i * 2)
val b = List(1, 2, 3).map(_ * 2)

// use of the double method instead of a lambda
def double(i: Int): Int = i * 2

val x1 = List(1, 2, 3).map(i => double(i))
val x2 = List(1, 2, 3).map(double)

// a range
1 to 9
(1 to 9)

/* 
Filter a collection twice and then multiply each element in the remaining
collection
 */

// a sample list
val nums = (1 to 9).toList

// chain methods together
val x3 = nums.filter(_ > 3)
            .filter(_ < 7)
            .map(_ * 10)


/* 
object defines a class that has exactly one instance
    a companion object is an object that has the same name as
    the class it shares a file with. This class is also called a companion class.
    One can access the private method of the other
objects are used to implement traits to create modules
As object is a singleton, its methods can be accessed like static methods in Java class
 */


object StringUtils {
    def isNullOrEmpty(s: String): Boolean = s == null || s.trim.isEmpty
    def leftTrim(s: String): String = s.replaceAll("^\\s+", "")
    def rightTrim(s: String): String = s.replaceAll("\\s+$", "")
}

val s1 = StringUtils.isNullOrEmpty("")
val s2 = StringUtils.leftTrim("  Naruto is there... right?")
val s3 = StringUtils.rightTrim("Naruto is there... right?   ")


// companion class and object
import scala.math.* 

object Circle {
    private def calculateArea(radius: Double): Double = {
        Pi * pow(radius, 2.0)
    }
}

class Circle(radius: Double) {
    import Circle.* // we import the companion object methods
    def area: Double = {
        calculateArea(radius)
    }
}

val c1 = Circle(radius = 5.0).area


/* 
Create a module by combining traits and objects
 */

trait AddService {
    def add(a: Int, b: Int) = a + b
}

trait MultiplyService {
    def multiply(a: Int, b: Int) = a * b
}

object MathService extends AddService, MultiplyService {

}

import MathService.{add, multiply}

val m1 = add(10, 15)
val m2 = multiply(10, 15)

/* 
Collections (available in immutable and mutable forms):
    List
    Tuples
 */

// immutable List
val l1 = List(1, 2, 3)
val l2 = (1 to 5).toList
val l3 = (1 until 10).toList
val l4 = List.range(start = 1, end = 10, step = 2)

// functional methods of list (functional means that they do not modify the object, instead,
// they return a new object with the updated elements)
val myList = List(10, 20, 30, 40, 10)

myList.drop(n = 2)
myList.dropWhile(_ < 25)
myList.filter(_ < 25)
myList.slice(from = 2, until = 4)
myList.tail
myList.take(n = 3)
myList.takeWhile(_ < 30)

val list2 = List(List(1, 2), List(3, 4))
list2.flatten

val list3 = List("one", "two")
list3.map(_.toUpperCase)
list3.flatMap(_.toUpperCase)

val firstTen = (1 to 10).toList
firstTen.reduceLeft(_ + _) // ((1 + 2) + 3) + ...
firstTen.reduceRight(_ + _)
firstTen.foldLeft(100)(_ + _)  // ((100 + 1) + 2) + ...


// Tuple
case class TinyPerson(name: String)

val t = (11, "eleven", TinyPerson("Naruto"))
t(0)
t(1)
t(2)

val (numTuple, strTuple, personTuple) = t

/* 
Context params: params that are omitted when calling a method.
They're inferred by the compiler from the context surronding the method call
 */

 /* 
 Definitions into packages and "inheritence" of packages
 Example with: foo and foo.bar packages
  */

/* 

package foo {
    // definition
    def double(i: Int) = {
        i * 2
    }
}

package foo {
    package bar {
        @main def fooBarMain = {
            println(s"${double(1)}")
        }
    }
}

 */


 val quote = """The essence of Scala:
                |Fusion of functional and objected-oriented
                |programming in a typed setting.""".stripMargin

/* 
Interpolation using s
 */

val name = "Naruto"
val age = 18

println(s"""{"name": "James"}""")
println(s"""name: "$name",
        |age: ${age}""".stripMargin)

/* 
Interpolation using f
Allow formating stringswith styles such as %d, %s, %2.2f etc.
 */

val height = 1.9d
val namePerson = "Naruto"
println(f"$namePerson%s is $height%2.2f meters tall")
println(f"3/19 is less than 20%%")

/* 
Raw interpolator: similar to s except it performs no escaping of literals within
string.
 */

s"a\nb"
raw"a\nb"

/* 
create our own interpolator
 */

 case class Point(x: Double, y: Double)

extension (sc: StringContext) {
    // p is our interpolator that extend StringContext
    // StringContext is a class providing the basic mechanism to do
    // String interpolation
    def p(args: Double*): Point = {
        val pts = sc.s(args: _*).split(",", 2).map(_.toDoubleOption.getOrElse(0.0))
        Point(pts(0), pts(1))
    }
}

val xm = 5
val xn = 10

p"1, 5"
p"$xm, $xn"

/* 
Map = equivalent to dictionaries (key -> value)
 */

val states = Map(
    "AK" -> "Alaska",
    "AL" -> "Alabama",
    "AR" -> "Arizona"
)

states("AK")
states.keys
states.values
for
    z <- states.keys
do
    println(z)

for
    (abb, compName) <- states
do
    println(s"${abb}: ${compName}")

// create a vector
val list4 = 
    for i <- 10 to 12
    yield i * 2