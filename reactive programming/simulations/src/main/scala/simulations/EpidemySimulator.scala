package simulations

import math.random

class EpidemySimulator extends Simulator {

  def randomBelow(i: Int) = (random * i).toInt

  protected[simulations] object SimConfig {
    val population: Int = 300
    val roomRows: Int = 8
    val roomColumns: Int = 8

    val incubationTime = 6
    val dieTime = 8
    val immuneTime = 2
    val healTime = 2

    val prevalenceRate = 0.01
    val transRate = 0.4
    val dieRate = 0.25

    // to complete: additional parameters of simulation
  }

  import SimConfig._

  val persons: List[Person] = // to complete: construct list of persons
    {
      var idx = 0
      var plist = List[Person]()
      for (idx <- 1 to population)
        plist = new Person(idx) :: plist

      for (idx <- 1 to (population * prevalenceRate).toInt) {
        var rn = 0
        do {
          rn = randomBelow(population)
        } while (plist(rn).infected)
        plist(rn).infected = true
        afterDelay(0) { plist(rn).infectedAction }
      }

      plist
    }

  for (idx <- 0 to population - 1) {
    afterDelay(0) { persons(idx).moveAction }
  }

  class Person(val id: Int) {
    var infected = false
    var sick = false
    var immune = false
    var dead = false

    // demonstrates random number generation
    var row: Int = randomBelow(roomRows)
    var col: Int = randomBelow(roomColumns)

    def up = if (row - 1 < 0) row + roomRows - 1 else row - 1
    def down = if (row + 1 == roomRows) 0 else row + 1
    def left = if (col + 1 == roomColumns) 0 else col + 1
    def right = if (col - 1 < 0) col + roomColumns - 1 else col - 1

    private var actions: List[Simulator#Action] = List()

    def neighbor: List[(Int, Int)] =
      List((up, col), (down, col), (row, left), (row, right))

    def legalNeigbhor: List[(Int, Int)] = {
      val p = for (
        room <- neighbor;
        ps <- persons;
        if ps.row == room._1;
        if ps.col == room._2;
        if ps.sick
      ) yield room

      for (
        room <- neighbor;
        if (!p.contains(room))
      ) yield room
    }

    def infectedAction() {
      infected = true
      afterDelay(incubationTime) { sickAction }
    }

    def sickAction() {
      sick = true
      afterDelay(dieTime) { dieAction }
    }

    def dieAction() {
      if (random < dieRate) { dead = true; sick = true; infected = true; immune = false } //die
      else {
        afterDelay(immuneTime) { immuneAction }
      }
    }

    def immuneAction() {
      sick = false
      infected = true
      immune = true
      afterDelay(healTime) { healthAction }
    }

    def healthAction() {
      immune = false
      sick = false
      infected = false
    }

    def moveAction() {
      val good = legalNeigbhor
      val p = good.size
      if (dead) {}
      else if (p == 0) {}
      else {
        var pick = 0;
        try {
          pick = randomBelow(p)
          row = good(pick)._1
          col = good(pick)._2
        } catch {
          case e: Exception => println(pick + "x" + p)
        }

        val infectPeople = for (
          p <- persons;
          if p.row == row;
          if p.col == col;
          if p.infected
        ) yield p

        if (infectPeople.size != 0 && random < transRate && !infected && !immune) { infectedAction }

      }

      val nextDelay = randomBelow(5) + 1
      afterDelay(nextDelay) {
        moveAction
      }

    }
  }
}

