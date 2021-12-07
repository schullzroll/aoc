import scala.io.Source
import java.lang.System

object Solution {
  def main(args: Array[String]): Unit = {
        val fileName = if (args.length == 2) args(1) else "-"
        val inputStream = fileName match {
            case "-" => Source.fromInputStream(System.in)
            case  _  => Source.fromFile(fileName)
        }

        val rates = inputStream.getLines
                   .filter(!_.isEmpty)
                   .map(_.zipWithIndex)
                   .flatten
                   .map({
                        case ('0', pos) => (-1, pos)
                        case ('1', pos) => (1, pos)
                    })
                   .toList
                   .groupBy(_._2)
                   .map({case (pos, valueTuple) => pos -> valueTuple.map(_._1).sum})

        println(rates)
        
        val prevalences = rates.toList.sortBy(_._1).map(_._2)

        val gammaRate   = Integer.parseInt(prevalences.map({ case digit => if (digit > 0) 1 else 0 }).mkString, 2)
        val epsilonRate = Integer.parseInt(prevalences.map({ case digit => if (digit > 0) 0 else 1 }).mkString, 2)

        println(prevalences)

        println((gammaRate, epsilonRate))
        println(gammaRate* epsilonRate)
  }

}
