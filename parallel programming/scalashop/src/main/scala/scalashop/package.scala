
import common._

package object scalashop {

  /** The value of every pixel is represented as a 32 bit integer. */
  type RGBA = Int

  /** Returns the red component. */
  def red(c: RGBA): Int = (0xff000000 & c) >>> 24

  /** Returns the green component. */
  def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16

  /** Returns the blue component. */
  def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8

  /** Returns the alpha component. */
  def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0

  /** Used to create an RGBA value from separate components. */
  def rgba(r: Int, g: Int, b: Int, a: Int): RGBA = {
    (r << 24) | (g << 16) | (b << 8) | (a << 0)
  }

  /** Restricts the integer into the specified range. */
  def clamp(v: Int, min: Int, max: Int): Int = {
    if (v < min) min
    else if (v > max) max
    else v
  }

  /** Image is a two-dimensional matrix of pixel values. */
  class Img(val width: Int, val height: Int, private val data: Array[RGBA]) {
    def this(w: Int, h: Int) = this(w, h, new Array(w * h))
    def apply(x: Int, y: Int): RGBA = data(y * width + x)
    def update(x: Int, y: Int, c: RGBA): Unit = data(y * width + x) = c
  }

  /** Computes the blurred RGBA value of a single pixel of the input image. */
  def boxBlurKernel(src: Img, x: Int, y: Int, radius: Int): RGBA = {
    // TODO implement using while loops

    val listOfColorTuple = for{
      i <- (x-radius to x+radius).toStream
      j <- (y-radius to y+radius).toStream
      if (i >= 0)
      if (i < src.width)
      if (j >=0)
      if (j < src.height)
    } yield(red(src(i, j)), green(src(i,j)), blue(src(i, j)), alpha(src(i, j)))

    val sumOfColorTuple = listOfColorTuple.foldRight[(Int, Int, Int, Int)] ((0,0,0,0)) { (x, res) =>
      val (a,b,c,d) = x
      (a + res._1, b + res._2, c + res._3, d + res._4)
    }

    val redValue = sumOfColorTuple._1 /  listOfColorTuple.length
    val greenValue = sumOfColorTuple._2 / listOfColorTuple.length
    val blueValue = sumOfColorTuple._3 / listOfColorTuple.length
    val alphaValue = sumOfColorTuple._4  / listOfColorTuple.length

    rgba(redValue, greenValue, blueValue, alphaValue)
  }



}
