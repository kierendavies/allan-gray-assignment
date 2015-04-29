package za.co.kierendavies.tweetfeed

import scala.util.matching.Regex

object UserFileParser extends FileParser {
  val userNameRegex = "[a-zA-Z0-9_]+".r
  val followsRegex = s"($userNameRegex) follows ($userNameRegex(?:, $userNameRegex)*)".r

  def parseLine(line: String): Unit = line match {
    case followsRegex(userName, following) =>
      following.split(", ").foreach {
        User.byName(userName).follow(_)
      }

    case "" => throw LineParsingException("empty line")

    case _ => throw LineParsingException(s"could not parse: $line")
  }
}
