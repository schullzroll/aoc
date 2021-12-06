import scala.io.Source
import java.lang.System

object Solution {
  def main(args: Array[String]): Unit = {
        val fileName = if (args.length == 2) args(1) else "-"
        val inputStream = fileName match {
            case "-" => Source.fromInputStream(System.in)
            case  _  => Source.fromFile(fileName)
        }

        val positionDeltas = inputStream.getLines
                            .filter(!_.isEmpty)
                            .map(_.split(" ")).filter(_.length == 2)
                            .map(arr => (arr(0), arr(1).toInt))
                            .foldLeft((0, 0, 0)) {case ((accX: Int, accY: Int, accAim: Int), (command: String, delta: Int)) => command match {
                              case "forward" => (accX+delta, accY+(accAim*delta), accAim)
                              case "down"    => (accX, accY, accAim+delta)
                              case "up"      => (accX, accY, accAim-delta)
                              case _ => (0, 0, 0)
                              }
                            }
        println(positionDeltas)
        println(positionDeltas._1 * positionDeltas._2)
  }
}
