package za.co.kierendavies.tweetfeed

/** A simulated feed of visible tweets.
  *
  * @param user The owner of this feed.
  * @param tweets All currently visible tweets, in order of creation.
  */
class Feed(val user: User, val tweets: Seq[Tweet]) {

  /** Convert to a string.
    *
    * Represented as the user's name, then each visible tweet on a new line,
    * indented by a tab, and prefixed with the handle of its creator.
    *
    * @return The string representation.
    */
  override def toString = {
    if (tweets.isEmpty) s"$user\n"
    else s"$user\n\t${tweets.mkString("\n\t")}\n"
  }
}
