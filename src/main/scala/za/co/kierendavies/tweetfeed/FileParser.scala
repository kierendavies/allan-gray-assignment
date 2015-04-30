package za.co.kierendavies.tweetfeed

import scala.io.Source

import com.typesafe.scalalogging.StrictLogging

/** Provides line-by-line file parsing.
  *
  * Classes that mix in [[FileParser]] must implement [[parseLine]], which may
  * throw [[LineParsingException]].  The [[parseFile]] method will then, given
  * a file name, iterate over the lines of the file and call [[parseLine]] on
  * each, intercepting [[LineParsingException]] and logging a warning instead.
  */
trait FileParser extends StrictLogging {

  /** Parse one line of the file.
    *
    * Expected to throw [[LineParsingException]] when parsing fails.
    *
    * @param line The line to parse.
    */
  def parseLine(line: String): Unit

  /** Parse a file line by line.
    *
    * @param fileName The name of the file.
    */
  def parseFile(fileName: String): Unit = {
    logger.info(s"Parsing $fileName")
    Source.fromFile(fileName).getLines.foreach { line =>
      try {
        parseLine(line)
      } catch {
        case LineParsingException(msg) => logger.warn(s"Skipping line: $msg")
      }
    }
  }
}
