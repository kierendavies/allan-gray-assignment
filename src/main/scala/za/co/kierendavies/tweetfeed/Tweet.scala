package za.co.kierendavies.tweetfeed

/** A tweet that has been posted by a user.
  *
  * @param user The user who posted this.
  * @param content The full text of the tweet.
  */
class Tweet(val user: User, val content: String) extends Ordered[Tweet] {

  /** A chronological identifier.
    *
    * Tweets need to be presented in order of posting.  Since no timestamp
    * data is provided in the record of tweets, this serves as a form of
    * timestamp.  The ordering of tweets is defined as ascending on this field.
    */
  val id: Int = Tweet.nextId

  /** Convert to a string.
    *
    * @return The string representation.
    */
  override def toString = s"@$user: $content"

  /** Compare to another tweet.
    *
    * Comparisons are made by [[id]].
    *
    * @param that The tweet to which to compare.
    * @return The result of comparison as specified by [[Ordered]].
    */
  override def compare(that: Tweet) = this.id compare that.id
}

/** Creates unique identifiers for tweets. */
object Tweet {
  /** The last identifier which was assigned.
    *
    * Initially -1 so that the first id assigned will be 0.
    */
  private var lastId: Int = -1

  /** Get a unique identifier.
    *
    * Successive calls will always yield results in increasing order.
    *
    * @return A unique identifier.
    */
  def nextId: Int = {
    lastId += 1
    lastId
  }
}
