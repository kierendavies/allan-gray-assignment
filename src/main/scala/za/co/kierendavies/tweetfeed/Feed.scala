package za.co.kierendavies.tweetfeed

class Feed(val user: User, val tweets: Seq[Tweet]) {
  override def toString = s"$user\n\t${tweets.mkString("\n\t")}\n"
}
