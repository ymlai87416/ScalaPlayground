package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }
  
  property("min2") = forAll { (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    findMin(h) == (a min b)
  }
  
  property("delete") = forAll { a:Int =>
    val h = deleteMin(insert(a, empty))
    h == empty
  }
  
  property("meld") = forAll { (a: Int, b: Int, c: Int, d: Int) =>
    val h1 = insert(b, insert(a, empty))
    val h2 = insert(c, insert(d, empty))
    val h = meld(h1, h2)
    findMin(h) == (a min b min c min d)  
  }
  
  property("sorted seq") = forAll{(a:Int, b:Int, c:Int, d: Int) =>
  	val h = insert(a, insert(b, insert(c, insert(d, empty))))
  	def helper(h: H) : List[Int] = {
  	   if(isEmpty(h)) List()
  	   else {
  	     val c = findMin(h)
  	     c :: helper(deleteMin(h))
  	   }
  	}
  	helper(h) == (List(a,b,c,d)).sortWith(_ < _)
  }
  

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[Int]
    m <- oneOf(empty, genHeap)
  } yield insert(k, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
