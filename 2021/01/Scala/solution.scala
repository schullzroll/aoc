import scala.io.Source
import java.lang.System

object Solution {
    def main(args: Array[String]): Unit = {
        val fileName = if (args.length == 2) args(1) else "-"
        val inputStream = fileName match {
            case "-" => Source.fromInputStream(System.in)
            case  _  => Source.fromFile(fileName)
        }

        val depths = inputStream.getLines.map(_.toInt)
        var increasingDepthsCount = 0
        var lastDepth = depths.next
        for (depth <- depths) {
            if (depth > lastDepth)
                increasingDepthsCount += 1

            lastDepth = depth
        }

        println(increasingDepthsCount)
    }
}