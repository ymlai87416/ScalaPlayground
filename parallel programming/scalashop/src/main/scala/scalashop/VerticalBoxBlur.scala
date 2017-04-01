package scalashop

import org.scalameter._
import common._

object VerticalBoxBlurRunner {

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 5,
    Key.exec.maxWarmupRuns -> 10,
    Key.exec.benchRuns -> 10,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val radius = 3
    val width = 1920
    val height = 1080
    val src = new Img(width, height)
    val dst = new Img(width, height)
    val seqtime = standardConfig measure {
      VerticalBoxBlur.blur(src, dst, 0, width, radius)
    }
    println(s"sequential blur time: $seqtime ms")

    val numTasks = 32
    val partime = standardConfig measure {
      VerticalBoxBlur.parBlur(src, dst, numTasks, radius)
    }
    println(s"fork/join blur time: $partime ms")
    println(s"speedup: ${seqtime / partime}")
  }

}

/** A simple, trivially parallelizable computation. */
object VerticalBoxBlur {

  /** Blurs the columns of the source image `src` into the destination image
   *  `dst`, starting with `from` and ending with `end` (non-inclusive).
   *
   *  Within each column, `blur` traverses the pixels by going from top to
   *  bottom.
   */
  def blur(src: Img, dst: Img, from: Int, end: Int, radius: Int): Unit = {
    // TODO implement this method using the `boxBlurKernel` method

    //dst.update(10, 10, scalashop.boxBlurKernel(src, 10, 10, radius))
    //(from to end).toStream map (i => (0 to src.height).toStream map (j => dst.update(i, j, scalashop.boxBlurKernel(src, i, j, radius)) ))

    for{
      i <- (from to end-1).toStream
      j <- (0 to src.height-1).toStream
    } dst.update(i, j, scalashop.boxBlurKernel(src, i, j, radius))
  }

  /** Blurs the columns of the source image in parallel using `numTasks` tasks.
   *
   *  Parallelization is done by stripping the source image `src` into
   *  `numTasks` separate strips, where each strip is composed of some number of
   *  columns.
   */
  def parBlur(src: Img, dst: Img, numTasks: Int, radius: Int): Unit = {
    // TODO implement using the `task` construct and the `blur` method
    val taskSize =Math.max(1, src.width/numTasks)

    val taskList = (0 to numTasks).toStream.par.map(i =>
    {
      task{
        val start = i*taskSize;
        val end = Math.min(src.width, (i+1)*taskSize)
        blur(src, dst, start, end, radius)
      }
    })

    for{
      task <- taskList
    } task.join()
  }

}
