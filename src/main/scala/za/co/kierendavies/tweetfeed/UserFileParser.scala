package za.co.kierendavies.tweetfeed

object UserFileParser extends FileParser {

  /** The regular expression used to identify user names.
    *
    * A variation on the regex used by Twitter.  This variation allows names
    * of unbounded length, instead of only up to 20 characters.
    * See [[https://github.com/twitter/twitter-text the twitter-text source]].
    */
  val userNameRegex = "[a-zA-Z0-9_]+".r

  /** The regular expression for an entry describing who a user follows.
    *
    * The first capturing group contains the name of the user, and the second
    * contains all the names of the users follows, delimited by ", ".
    */
  val followsRegex = s"($userNameRegex) follows ($userNameRegex(?:, $userNameRegex)*)".r

  /** Parse a line of a user file.
    *
    * When the line describes who a user follows, the user is set to follow
    * those others.  Otherwise a [[LineParsingException]] will be thrown.
    *
    * @param line The line to parse.
    */
  def parseLine(line: String): Unit = line match {
    case followsRegex(userName, following) =>
      following.split(", ").foreach {
        User.byName(userName).follow(_)
      }

    case "" => throw LineParsingException("empty line")

    case _ => throw LineParsingException(s"could not parse: $line")
  }
}
