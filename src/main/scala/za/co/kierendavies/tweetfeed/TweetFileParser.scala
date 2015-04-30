package za.co.kierendavies.tweetfeed

/** Provides methods for parsing a file describing tweets by users. */
object TweetFileParser extends FileParser {

  /** The regular expression used to identify user names.
    *
    * A variation on the regex used by Twitter.  This variation allows names
    * of unbounded length, instead of only up to 20 characters.
    * See [[https://github.com/twitter/twitter-text the twitter-text source]].
    */
  val userNameRegex = "[a-zA-Z0-9_]+".r

  /** The regular expression for a tweet posted by a user.
    *
    * The first capturing group contains the user's name, and the second
    * contains the tweet.  Tweets may be up to 140 characters long.
    */
  val tweetRegex = s"($userNameRegex)> (.{1,140})".r

  /** The regular expression for something which looks like a tweet, but is too long. */
  val tooLongTweetRegex = s"$userNameRegex> .{141,}".r

  /** Parse a line of a tweet file.
    *
    * When the line matches a tweet entry, the tweet is posted for the user.
    * Otherwise a [[LineParsingException]] will be thrown.
    *
    * @param line The line to parse.
    */
  def parseLine(line: String): Unit = line match {
    case tweetRegex(userName, content) =>
      User.byName(userName).tweet(content)

    case tooLongTweetRegex() =>
      throw LineParsingException(s"tweet too long: $line")

    case "" => throw LineParsingException("empty line")

    case _ => throw LineParsingException(s"could not parse: $line")
  }
}
