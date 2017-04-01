package simulations

object Worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(73); 
    val es = new EpidemySimulator;System.out.println("""es  : simulations.EpidemySimulator = """ + $show(es ));$skip(48); 

val numInfected = es.persons.count(_.infected);System.out.println("""numInfected  : Int = """ + $show(numInfected ));$skip(27); 
    val incubationTime = 6;System.out.println("""incubationTime  : Int = """ + $show(incubationTime ));$skip(21); 
    val dieTime = 14;System.out.println("""dieTime  : Int = """ + $show(dieTime ));$skip(24); 
    val immuneTime = 16;System.out.println("""immuneTime  : Int = """ + $show(immuneTime ));$skip(22); 
    val healTime = 18;System.out.println("""healTime  : Int = """ + $show(healTime ));$skip(31); 

    val prevalenceRate = 0.01;System.out.println("""prevalenceRate  : Double = """ + $show(prevalenceRate ));$skip(24); 
    val transRate = 0.4;System.out.println("""transRate  : Double = """ + $show(transRate ));$skip(23); 
    val dieRate = 0.25;System.out.println("""dieRate  : Double = """ + $show(dieRate ));$skip(60); 

    val infectedPerson = (es.persons.find{_.infected}).get;System.out.println("""infectedPerson  : simulations.Worksheet.es.Person = """ + $show(infectedPerson ));$skip(10); 
		es.next;$skip(34); 
		val x = infectedPerson.infected;System.out.println("""x  : Boolean = """ + $show(x ));$skip(32); 
    val y = infectedPerson.sick;System.out.println("""y  : Boolean = """ + $show(y ));$skip(40); 
    //before incubation time
 		es.next;$skip(35); 
		val x1 = infectedPerson.infected;System.out.println("""x1  : Boolean = """ + $show(x1 ));$skip(33); 
    val	y1 = infectedPerson.sick;System.out.println("""y1  : Boolean = """ + $show(y1 ));$skip(12); 

 		es.next;$skip(35); 
		val x2 = infectedPerson.infected;System.out.println("""x2  : Boolean = """ + $show(x2 ));$skip(33); 
    val	y2 = infectedPerson.sick;System.out.println("""y2  : Boolean = """ + $show(y2 ));$skip(20); 
    
     		es.next;$skip(35); 
		val x3 = infectedPerson.infected;System.out.println("""x3  : Boolean = """ + $show(x3 ));$skip(33); 
    val	y3 = infectedPerson.sick;System.out.println("""y3  : Boolean = """ + $show(y3 ));$skip(20); 
    
     		es.next;$skip(35); 
		val x4 = infectedPerson.infected;System.out.println("""x4  : Boolean = """ + $show(x4 ));$skip(33); 
    val	y4 = infectedPerson.sick;System.out.println("""y4  : Boolean = """ + $show(y4 ));$skip(20); 
    
     		es.next;$skip(35); 
		val x5 = infectedPerson.infected;System.out.println("""x5  : Boolean = """ + $show(x5 ));$skip(33); 
    val	y5 = infectedPerson.sick;System.out.println("""y5  : Boolean = """ + $show(y5 ));$skip(29); 
 	//es.agenda
     		es.next;$skip(35); 
		val x6 = infectedPerson.infected;System.out.println("""x6  : Boolean = """ + $show(x6 ));$skip(33); 
    val	y6 = infectedPerson.sick;System.out.println("""y6  : Boolean = """ + $show(y6 ));$skip(20); 
    
     		es.next;$skip(35); 
		val x7 = infectedPerson.infected;System.out.println("""x7  : Boolean = """ + $show(x7 ));$skip(33); 
    val	y7 = infectedPerson.sick;System.out.println("""y7  : Boolean = """ + $show(y7 ));$skip(24); 
    
         		es.next;$skip(35); 
		val x8 = infectedPerson.infected;System.out.println("""x8  : Boolean = """ + $show(x8 ));$skip(33); 
    val	y8 = infectedPerson.sick;System.out.println("""y8  : Boolean = """ + $show(y8 ));$skip(34); val res$0 = 
    
    infectedPerson.infected;System.out.println("""res0: Boolean = """ + $show(res$0))}
}
