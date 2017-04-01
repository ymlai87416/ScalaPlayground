package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = 
	if(r==0) 1
	else if (c==r) 1
	else if (c==0) 1
	else {pascal (c-1, r-1) + pascal(c, r-1)}

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceHelp(x: Int, chars:List[Char]): Boolean= {
      if (x < 0)
        false
      else if (chars.isEmpty)
        (x==0)
      else if (chars.head == '(') {
        balanceHelp(x + 1, chars.tail)
      } else if (chars.head == ')') {
        balanceHelp(x - 1, chars.tail)
      } else balanceHelp(x, chars.tail);
    }
    balanceHelp(0, chars);	
  }
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if(money == 0) 1
    else if(money < 0) 0
    else if(coins.isEmpty) 0
    else if(money >= coins.head)
      countChange(money-coins.head, coins) + countChange(money, coins.tail)
    else countChange(money, coins.tail)
  }
}
