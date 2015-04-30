package za.co.kierendavies.tweetfeed

import scala.collection.mutable

/** A user, who may follow other users and post tweets.
  *
  * @param name The name of the user.
  */
class User private (val name: String) {

  /** The users whom this user follows.
    *
    * [[scala.collection.mutable.HashSet]] was chosen for its O(1) insertion,
    * as ordering is not important.
    */
  var following = new mutable.HashSet[User]

  /** The tweets posted by this user.
    *
    * [[scala.collection.mutable.TreeSet]] was chosen for being ordered, and
    * still having O(log n) insertion.  Keeping the tweets ordered allows O(n)
    * unioning when constructing a user's feed.
    */
  var tweets = new mutable.TreeSet[Tweet]

  /** Convert to a string.
    *
    * Represented as just the user's name.
    *
    * @return The string represention.
    */
  override def toString = name

  /** Follow another user.
    *
    * @param user The user to follow.
    */
  def follow(user: User): Unit = following += user

  /** Follow another user.
    *
    * @param name The name of the user to follow.
    */
  def follow(name: String): Unit = follow(User.byName(name))

  /** Post a tweet by this user.
    *
    * @param content The full text of the tweet.
    */
  def tweet(content: String): Unit = tweets += new Tweet(this, content)

  /** Construct the feed of tweets visible to this user.
    *
    * @return The user's feed.
    */
  def feed: Feed = new Feed(
    this,
    if (following.isEmpty) Seq[Tweet]() else following.map(_.tweets).reduce(_ union _).union(tweets).toSeq)
}

/** Keeps a store of all users, and allows them to be retrieved by name. */
object User {

  /** The store of all users.
    *
    * [[scala.collection.mutable.HashMap]] was chosen for its O(1) insertion
    * and query by key.
    */
  private var users = new mutable.HashMap[String, User]

  /** Get a user by name.
    *
    * If no user by the given name exists, it is created.
    *
    * @param name The name of the user.
    * @return The user.
    */
  def byName(name: String): User = {
    if (users contains name) {
      users(name)
    } else {
      val user = new User(name)
      users += (name -> user)
      user
    }
  }

  /** Get the feeds of all users, sorted by user name.
    *
    * @return A [[scala.collection.Seq]] of feeds.
    */
  def allFeeds: Seq[Feed] = users.values.toSeq.sortBy(_.name).map(_.feed)
}
