package za.co.kierendavies.tweetfeed

class Tweet(val user: User, val content: String) extends Ordered[Tweet] {
  val id: Int = Tweet.nextId

  override def toString = s"@$user: $content"
  override def compare(that: Tweet) = this.id compare that.id
}

object Tweet {
  private var lastId: Int = -1

  def nextId: Int = {
    lastId += 1
    lastId
  }
}
