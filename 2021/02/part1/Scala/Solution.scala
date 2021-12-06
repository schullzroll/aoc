import scala.io.Source
import java.lang.System
import scala.collection.immutable.Vector

object Solution {
  def main(args: Array[String]): Unit = {
        val fileName = if (args.length == 2) args(1) else "-"
        val inputStream = fileName match {
            case "-" => Source.fromInputStream(System.in)
            case  _  => Source.fromFile(fileName)
        }

        val positionDeltas = inputStream.getLines
                            .map(_.split(" "))
                            .map(arr => op2delta(arr(0), arr(1).toInt))
                            .foldLeft((0, 0)) {case ((accA, accB), (a, b)) => (accA+a, accB+b)}
        println(positionDeltas)
        println(positionDeltas._1 * positionDeltas._2)
  }

  def op2delta(op: String, delta: Int): Tuple2[Int, Int] = {
    op match {
        case "forward" => (delta, 0)
        case "down"    => (0, delta)
        case "up"      => (0, -delta)
        case _ => (0, 0)
    }
  }
}
