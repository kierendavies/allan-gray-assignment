package za.co.kierendavies.tweetfeed

import scopt.OptionParser

/** Application runner class.
  *
  * When run, reads the user and tweet files from the command line, parses
  * them, then prints the simulated feeds to stdout.
  */
object Application extends App {
  val options = new OptionParser[Config]("tweetfeed") {
    opt[String]('u', "users") required() valueName("<file>") action {
      (f, c) => c.copy(userFileName = f) }
    opt[String]('t', "tweets") required() valueName("<file>") action {
      (f, c) => c.copy(tweetFileName = f) }
  }
  options.parse(args, Config()) match {
    case None => System.exit(1)

    case Some(config) =>
      UserFileParser.parseFile(config.userFileName)
      TweetFileParser.parseFile(config.tweetFileName)
      println(User.allFeeds.mkString)
  }
}
