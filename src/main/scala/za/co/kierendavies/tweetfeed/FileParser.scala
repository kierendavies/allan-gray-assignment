package za.co.kierendavies.tweetfeed

import scala.io.Source

import com.typesafe.scalalogging.StrictLogging

trait FileParser extends StrictLogging {
  def parseLine(line: String): Unit

  def parseFile(fileName: String): Unit = {
    logger.info("Parsing $fileName")
    Source.fromFile(fileName).getLines.foreach { line =>
      try {
        parseLine(line)
      } catch {
        case LineParsingException(msg) => logger.warn(s"Skipping line: $msg")
      }
    }
  }
}
