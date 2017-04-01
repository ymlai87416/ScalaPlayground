object Worksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(62); 
  println("Welcome to the Scala worksheet");$skip(12); val res$0 = 
  0.9.toInt;System.out.println("""res0: Int = """ + $show(res$0));$skip(22); val res$1 = 
  0.99999999999.toInt;System.out.println("""res1: Int = """ + $show(res$1));$skip(10); val res$2 = 
  1.toInt
  
  import simulations;System.out.println("""res2: Int = """ + $show(res$2));$skip(59); 
  
  val es = new EpidemySimulator;System.out.println("""es  : <error> = """ + $show(es ));$skip(36); 
  
  val es2 = new EpidemySimulator;System.out.println("""es2  : <error> = """ + $show(es2 ))}
}
