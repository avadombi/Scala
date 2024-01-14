var list = (10 to 12).map(_ * 2)

// utility of yield
val names = List("_olivia", "_walter", "_peter")

val capNames = for name <- names yield
    val nameWithoutUnderscore = name.drop(1)
    val capName = nameWithoutUnderscore.capitalize
    capName


// use default value of match
val i = 0

i match {
    case 0 => println("1")
    case 1 => println("2")
    case what: Int => println(s"You gave me: ${what}")
}

// lower and upper variable name
// lower case gets the value entered and upper 
// get the value in the code scope
val N = 42
val j = 10

j match {
    case 0 => println("1")
    case 1 => println("2")
    case N => println("42")
    case n => println(s"You gave me: ${n}")
}


// multiple matches on one line
val z = 3

z match {
    case 1 | 3 | 5 | 7 | 9 => println("odd")
    case 2 | 4 | 6 | 8 | 10 => println("even")
    case _ => println("some other number")
}

// match again range of values
z match {
    case a if  0 to 9 contains a => println(s"0-9 range: $a")
    case b if  10 to 19 contains b => println(s"10-19 range: $b")
    case _ => println("Hmmm...")
}


/* 
Auxiliary constructors
*/

import java.time.* 

// primary constructor
class Student(var name: String, var govtId: String) {
    private var _applicationDate: Option[LocalDate] = None
    private var _studentId: Int = 0

    // a constructor for when the student has completed its application
    def this(name: String, govtId: String, applicationDate: LocalDate) = {
        this(name, govtId)
        _applicationDate = Some(applicationDate)
    }

    // a constructor for when the student is approved and now has a student id
    def this(name: String, govtId: String, studentId: Int) = {
        this(name, govtId)
        _studentId = studentId
    }
}

val s1 = Student("Naruto", "123")
val s2 = Student("Naruto", "123", LocalDate.now)
val s3 = Student("Naruto", "123", 456)

s3.name
s3.govtId
// s3._studentId will give an error bc it's a private variable



