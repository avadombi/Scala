class Person(firstName: String) {
    private var _lasttName: String = "Unknown"
    private var _age: Int = -1

    def this(firstName: String, lastName: String) = {
        this(firstName)
        _lasttName = lastName
    }

    def this(firstName:String, lastName: String, age: Int) = {
        this(firstName)
        _lasttName = lastName
        _age = age
    }

    override def toString(): String = {
        var displayInfo = s"${firstName}"

        if _lasttName != "Unknown" then
            displayInfo += s", ${_lasttName}"
        
        if _age >= 0 then
            displayInfo += s", ${_age}"
        
        displayInfo  // return displayInfo's value
    }
}


val p1 = Person("Naruto")
p1.toString()

val p2 = Person("Naruto", "Runner")
p2.toString()

val p3 = Person("Naruto", "Runner", 21)
p3.toString()

1 + 1