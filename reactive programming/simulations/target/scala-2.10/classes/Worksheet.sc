package simulations

object Worksheet {
    val es = new EpidemySimulator                 //> set 155 as infected.3.0
                                                  //| set 254 as infected.3.0
                                                  //| set 81 as infected.3.0
                                                  //| es  : simulations.EpidemySimulator = simulations.EpidemySimulator@5175f214

val numInfected = es.persons.count(_.infected)    //> numInfected  : Int = 3
    val incubationTime = 6                        //> incubationTime  : Int = 6
    val dieTime = 14                              //> dieTime  : Int = 14
    val immuneTime = 16                           //> immuneTime  : Int = 16
    val healTime = 18                             //> healTime  : Int = 18

    val prevalenceRate = 0.01                     //> prevalenceRate  : Double = 0.01
    val transRate = 0.4                           //> transRate  : Double = 0.4
    val dieRate = 0.25                            //> dieRate  : Double = 0.25

    val infectedPerson = (es.persons.find{_.infected}).get
                                                  //> infectedPerson  : simulations.Worksheet.es.Person = simulations.EpidemySimul
                                                  //| ator$Person@53628ee
		es.next
		val x = infectedPerson.infected   //> x  : Boolean = true
    val y = infectedPerson.sick                   //> y  : Boolean = false
    //before incubation time
 		es.next
		val x1 = infectedPerson.infected  //> x1  : Boolean = true
    val	y1 = infectedPerson.sick                  //> y1  : Boolean = false

 		es.next
		val x2 = infectedPerson.infected  //> x2  : Boolean = true
    val	y2 = infectedPerson.sick                  //> y2  : Boolean = false
    
     		es.next
		val x3 = infectedPerson.infected  //> x3  : Boolean = true
    val	y3 = infectedPerson.sick                  //> y3  : Boolean = false
    
     		es.next
		val x4 = infectedPerson.infected  //> x4  : Boolean = true
    val	y4 = infectedPerson.sick                  //> y4  : Boolean = false
    
     		es.next
		val x5 = infectedPerson.infected  //> x5  : Boolean = true
    val	y5 = infectedPerson.sick                  //> y5  : Boolean = false
 	//es.agenda
     		es.next
		val x6 = infectedPerson.infected  //> x6  : Boolean = true
    val	y6 = infectedPerson.sick                  //> y6  : Boolean = false
    
     		es.next
		val x7 = infectedPerson.infected  //> x7  : Boolean = true
    val	y7 = infectedPerson.sick                  //> y7  : Boolean = false
    
         		es.next
		val x8 = infectedPerson.infected  //> x8  : Boolean = true
    val	y8 = infectedPerson.sick                  //> y8  : Boolean = false
    
    infectedPerson.infected                       //> res0: Boolean = true
}