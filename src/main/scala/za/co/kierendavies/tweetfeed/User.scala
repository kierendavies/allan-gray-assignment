package za.co.kierendavies.tweetfeed

import scala.collection.mutable

class User private (val name: String) {
  var following = new mutable.HashSet[User]
  var tweets = new mutable.TreeSet[Tweet]

  override def toString = name

  def follow(user: User): Unit = following += user
  def follow(name: String): Unit = follow(User.byName(name))

  def tweet(content: String): Unit = tweets += new Tweet(this, content)

  def feed: Feed = new Feed(
    this,
    if (following.isEmpty) Seq[Tweet]() else following.map(_.tweets).reduce(_ union _).toSeq)
}

object User {
  private var users = new mutable.HashMap[String, User]

  def byName(name: String): User = {
    this.synchronized {
      if (users contains name) {
        users(name)
      } else {
        val user = new User(name)
        users += (name -> user)
        user
      }
    }
  }

  def allFeeds: Seq[Feed] = users.values.toSeq.sortBy(_.name).map(_.feed)
}
