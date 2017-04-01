package simulations

import common._

class Wire {
  private var sigVal = false
  private var actions: List[Simulator#Action] = List()

  def getSignal: Boolean = sigVal
  
  def setSignal(s: Boolean) {
    if (s != sigVal) {
      sigVal = s
      actions.foreach(action => action())
    }
  }

  def addAction(a: Simulator#Action) {
    actions = a :: actions
    a()
  }
}

abstract class CircuitSimulator extends Simulator {

  val InverterDelay: Int
  val AndGateDelay: Int
  val OrGateDelay: Int

  def probe(name: String, wire: Wire) {
    wire addAction {
      () => afterDelay(0) {
        println(
          "  " + currentTime + ": " + name + " -> " +  wire.getSignal)
      }
    }
  }

  def inverter(input: Wire, output: Wire) {
    def invertAction() {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) { output.setSignal(!inputSig) }
    }
    input addAction invertAction
  }

  def andGate(a1: Wire, a2: Wire, output: Wire) {
    def andAction() {
      val a1Sig = a1.getSignal
      val a2Sig = a2.getSignal
      afterDelay(AndGateDelay) { output.setSignal(a1Sig & a2Sig) }
    }
    a1 addAction andAction
    a2 addAction andAction
  }

  //
  // to complete with orGates and demux...
  //

  def orGate(a1: Wire, a2: Wire, output: Wire) {
    def orAction() {
      val a1Sig = a1.getSignal
      val a2Sig = a2.getSignal
      afterDelay(OrGateDelay) { output.setSignal(a1Sig | a2Sig) }
    }
    a1 addAction orAction
    a2 addAction orAction    
  }
  
  def orGate2(a1: Wire, a2: Wire, output: Wire) {
    val notIn1, notIn2, notOut = new Wire
	inverter(a1, notIn1); inverter(a2, notIn2)
	andGate(notIn1, notIn2, notOut)
	inverter(notOut, output)
  }

  def demux(in: Wire, c: List[Wire], out: List[Wire]) {
    if(c.isEmpty){throw new Exception("Cannot be empty")}
	if(c.length == 1){
	  val notIn1, in1, in2 = new Wire
	  inverter(c(0), notIn1)
	  andGate(in, notIn1, out(0))  //for the last first of output
	  andGate(in, c(0), out(1))	//for the first part of output
	}
	else{
	  val notIn1, in1, in2 = new Wire
	  inverter(c(0), notIn1)
	  andGate(in, notIn1, in1)  //for the last first of output
	  andGate(in, c(0), in2)	//for the first part of output
	  demux(in1, c.tail, out.take(out.length/2))
	  demux(in2, c.tail, out.takeRight(out.length/2))
	}
  }

}

object Circuit extends CircuitSimulator {
  val InverterDelay = 1
  val AndGateDelay = 3
  val OrGateDelay = 5

  def andGateExample {
    val in1, in2, out = new Wire
    andGate(in1, in2, out)
    probe("in1", in1)
    probe("in2", in2)
    probe("out", out)
    in1.setSignal(false)
    in2.setSignal(false)
    run

    in1.setSignal(true)
    run

    in2.setSignal(true)
    run
  }
  
  def orGateExample {
    val in1, in2, out = new Wire
    orGate(in1, in2, out)
    probe("in1", in1)
    probe("in2", in2)
    probe("out", out)
    in1.setSignal(false)
    in2.setSignal(false)
    run

    in1.setSignal(true)
    run

    in2.setSignal(true)
    run
  }
    
  def demuxExample {
    val out1, out2, out3, out4, out5, out6, out7, out8, in = new Wire
    val c1, c2, c3 = new Wire
    
    val out = List(out1, out2, out3, out4, out5, out6, out7, out8)
    val cnt = List(c3, c2, c1)

    demux(in, cnt, out)
    probe("in", in)
    probe("c1", c1)
    probe("c2", c2)
    probe("c3", c3)
    probe("out1", out1)
    probe("out2", out2)
    probe("out3", out3)
    probe("out4", out4)
    probe("out5", out5)
    probe("out6", out6)
    probe("out7", out7)
    probe("out8", out8)
    
    in.setSignal(true)
    c1.setSignal(false)
    c2.setSignal(true)
    c3.setSignal(true)
    run

    in.setSignal(false)
    run

    in.setSignal(true)
    c1.setSignal(true)
    c2.setSignal(false)
    run
  }

  //
  // to complete with orGateExample and demuxExample...
  //
}

object CircuitMain extends App {
  // You can write tests either here, or better in the test class CircuitSuite.
  Circuit.andGateExample
  Circuit.orGateExample
  Circuit.demuxExample
}
