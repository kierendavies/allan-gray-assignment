package za.co.kierendavies.tweetfeed

/** An exception representing failure to parse a single line in a file. */
case class LineParsingException(msg: String) extends RuntimeException(msg)
