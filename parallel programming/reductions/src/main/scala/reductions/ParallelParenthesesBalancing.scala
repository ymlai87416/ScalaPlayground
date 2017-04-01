package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    var i = 0
    var cnt = 0

    while(i < chars.length && cnt >=0){
      chars(i) match{
        case '(' => cnt = cnt+1;
        case ')' => cnt = cnt-1;
        case _ =>
      }
      i = i+1
    }

    cnt == 0
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int) : (Int, Int) = {
      var cnt = 0
      var front = 0
      var i = idx

      while( i < until){
        chars(i) match{
          case '(' => cnt = cnt+1
          case ')' =>
            if(cnt == 0)
              front = front+1
            else
              cnt = cnt-1
          case _ =>
        }
        i = i + 1
      }

      (front, cnt)
    }

    def reduce(from: Int, until: Int) : (Int, Int) = {
      if(until-from <= threshold)
        traverse(from, until, 0 , 0)
      else{
        val mid = from + (until - from) / 2
        val (a, b) = parallel(reduce(from, mid), reduce(mid, until))

        val openRight = a._1 + Math.max(b._1 - a._2, 0)
        val openLeft = b._2 + Math.max(a._2 - b._1, 0)

        (openRight, openLeft)
      }
    }

    reduce(0, chars.length) == (0, 0)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
