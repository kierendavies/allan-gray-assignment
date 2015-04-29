package za.co.kierendavies.tweetfeed

class Feed(val user: User, val tweets: Seq[Tweet]) {
  override def toString = {
    if (tweets.isEmpty) user.toString
    else s"$user\n\t${tweets.mkString("\n\t")}"
  }
}
