package streams

class InfiniteLevel extends Solver with InfiniteTerrain {
    val startPos = Pos(1,3)
    val goal = Pos(5,8)
  }

  abstract class Level extends Solver with StringParserTerrain
   
  class Level0 extends Level {
    val level =
      """------
        |--So--
        |--To--
        |--oo--
        |------""".stripMargin
  }

  
class Level1 extends Level {
    val level =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin
  }
  
object testing {
val l0 = new Level0                               //> l0  : streams.Level0 = streams.Level0@7d4012b3
val l0sol = l0.solution                           //> l0sol  : List[streams.testing.l0.Move] = List()
	val l1 = new Level1                       //> l1  : streams.Level1 = streams.Level1@626ddb07
	val l1from = l1.pathsFromStart.take(20).toList
                                                  //> l1from  : List[(streams.testing.l1.Block, List[streams.testing.l1.Move])] = 
                                                  //| List((Block(Pos(1,1),Pos(1,1)),List()), (Block(Pos(1,2),Pos(1,3)),List(Right
                                                  //| )), (Block(Pos(2,1),Pos(3,1)),List(Down)), (Block(Pos(1,4),Pos(1,4)),List(Ri
                                                  //| ght, Right)), (Block(Pos(2,2),Pos(2,3)),List(Down, Right)), (Block(Pos(2,2),
                                                  //| Pos(3,2)),List(Right, Down)), (Block(Pos(2,4),Pos(3,4)),List(Down, Right, Ri
                                                  //| ght)), (Block(Pos(2,1),Pos(2,1)),List(Left, Down, Right)), (Block(Pos(2,4),P
                                                  //| os(2,4)),List(Right, Down, Right)), (Block(Pos(3,2),Pos(3,3)),List(Down, Dow
                                                  //| n, Right)), (Block(Pos(2,3),Pos(3,3)),List(Right, Right, Down)), (Block(Pos(
                                                  //| 1,2),Pos(1,2)),List(Up, Right, Down)), (Block(Pos(2,5),Pos(3,5)),List(Right,
                                                  //|  Down, Right, Right)), (Block(Pos(0,1),Pos(1,1)),List(Up, Left, Down, Right)
                                                  //| ), (Block(Pos(2,5),Pos(2,6)),List(Right, Right, Down, Right)), (Block(Pos(3,
                                                  //| 1),Pos(3,1)),List(Left, Down, Down, Right)), (Block(Pos(3,4),Pos(3,4)),List(
                                                  //| Right, Down, Down, Right
                                                  //| Output exceeds cutoff limit.
   val l1test = l1.Block(l1.Pos(1,4), l1.Pos(1,4))//> l1test  : streams.testing.l1.Block = Block(Pos(1,4),Pos(1,4))
   val l1testn = l1test.neighbors                 //> l1testn  : List[(streams.testing.l1.Block, streams.testing.l1.Move)] = List(
                                                  //| (Block(Pos(1,2),Pos(1,3)),Left), (Block(Pos(1,5),Pos(1,6)),Right), (Block(Po
                                                  //| s(-1,4),Pos(0,4)),Up), (Block(Pos(2,4),Pos(3,4)),Down))
   val l1to = l1.pathsToGoal                      //> l1to  : Stream[(streams.testing.l1.Block, List[streams.testing.l1.Move])] = 
                                                  //| Stream((Block(Pos(4,7),Pos(4,7)),List(Down, Right, Right, Right, Down, Right
                                                  //| , Right)), ?)
   val l1done = l1.done(l1.Block(l1.Pos(4,7), l1.Pos(4,7)))
                                                  //> l1done  : Boolean = true
   List().length                                  //> res0: Int = 0
   val l0p = l0.solution.length == List().length  //> l0p  : Boolean = true
   ///val l0px = l0.pathsToGoal.head
   val pp = Stream.empty                          //> pp  : scala.collection.immutable.Stream[Nothing] = Stream()
   val ppf = for(x <- pp) yield 10                //> ppf  : scala.collection.immutable.Stream[Int] = Stream()
   val ppq = try{pp.head}catch{case e:Exception => List()}
                                                  //> ppq  : List[Nothing] = List()
}