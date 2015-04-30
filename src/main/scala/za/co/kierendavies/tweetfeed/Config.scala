package za.co.kierendavies.tweetfeed

/** Configuration used by [[Application]] to hold command-line arguments.
  *
  * @param userFileName The name of the file describing users and who they follow.
  * @param tweetFileName The name of the file containing tweets posted by users.
  */
case class Config(
    userFileName: String = "",
    tweetFileName: String = "")
