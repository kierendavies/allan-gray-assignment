package za.co.kierendavies.tweetfeed

object TweetFileParser extends FileParser {
  val userNameRegex = "[a-zA-Z0-9_]+".r
  val tweetRegex = s"($userNameRegex)> (.{1,140})".r
  val tooLongTweetRegex = s"$userNameRegex> .{141,}".r

  def parseLine(line: String): Unit = line match {
    case tweetRegex(userName, content) =>
      User.byName(userName).tweet(content)

    case tooLongTweetRegex() =>
      throw LineParsingException(s"tweet too long: $line")

    case "" => throw LineParsingException("empty line")

    case _ => throw LineParsingException(s"could not parse: $line")
  }
}
