package za.co.kierendavies.tweetfeed

class FeedSpec extends UnitSpec {
  val name = "Alice"
  val user = User.byName(name)
  val content1 = "This is a tweet"
  val content2 = "This is also a tweet"
  val tweet1 = new Tweet(user, content1)
  val tweet2 = new Tweet(user, content2)
  val feed = new Feed(user, List(tweet1, tweet2))

  describe("Feed") {
    it("renders a string") {
      feed.toString shouldBe s"$name\n\t@$name: $content1\n\t@$name: $content2\n"
    }
  }
}
