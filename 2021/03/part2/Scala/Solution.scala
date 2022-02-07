import scala.io.Source
import java.lang.System

object Solution {

    def main(args: Array[String]): Unit = {
        val fileName = if (args.length == 2) args(1) else "-"
        val inputStream = fileName match {
            case "-" => Source.fromInputStream(System.in)
            case  _  => Source.fromFile(fileName)
        }

        val inputLines = inputStream.getLines
                   .filter(!_.isEmpty).toList

        val prevalences = getPrevalences(inputLines)
        val oxygenGeneratorRate = inputLines
                                  .scanLeft(inputLines) {case (lines, ) =>

                                  }

        val oxygenGeneratorRatePatterns = prevalences.map({
                        case prevalence if prevalence > 0 => '1'
                        case prevalence if prevalence < 0 => '0'
                        case _ => '1'
                    })
                    .zipWithIndex
                    .map({ case (charDigit, pos) => "^" + ("."*prevalences.size).updated(pos, charDigit) + "$" })
                    .toList


        println(oxygenGeneratorRatePatterns)

        val oxygenGeneratorRate = inputLines
                    .filter { matchesPatterns(_, oxygenGeneratorRatePatterns) }

        println(oxygenGeneratorRate)
        
        val gammaRate   = Integer.parseInt(prevalences.map({ case digit => if (digit > 0) 1 else 0 }).mkString, 2)
        val epsilonRate = Integer.parseInt(prevalences.map({ case digit => if (digit > 0) 0 else 1 }).mkString, 2)

        println(prevalences)

        println((gammaRate, epsilonRate))
        println(gammaRate* epsilonRate)
    }

    def matchesPatterns(str: String, patterns: List[String]): Boolean = {
        patterns match {
            case Nil => true
            case pattern :: rest => (str matches pattern) & matchesPatterns(str, rest)
        }
    }

    def getPrevalences(binaryStrings: TraversableOnce[String]): Iterable[Int] = {
        return binaryStrings
                    .map(_.zipWithIndex)
                    .flatten
                    .map({
                         case ('0', pos) => (-1, pos)
                         case ('1', pos) => (1, pos)
                     })
                    .toList
                    .groupBy(_._2)
                    .map({case (pos, valueTuple) => pos -> valueTuple.map(_._1).sum})
                    .toList
                    .sortBy(_._1)
                    .map(_._2)
    }
    
}
