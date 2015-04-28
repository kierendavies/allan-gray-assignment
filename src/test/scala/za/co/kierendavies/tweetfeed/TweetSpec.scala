package za.co.kierendavies.tweetfeed

class TweetSpec extends UnitSpec {
  val user = new User("Dudebro")
  val content = "This is a tweet"
  val tweet = new Tweet(user, content)
  val tweet2 = new Tweet(user, content)

  describe("Tweet") {
    it("is constructed with a user and content") {
      tweet.user shouldBe user
      tweet.content shouldBe content
    }

    it("is assigned a non-negative id") {
      tweet.id should be >= 0
      tweet2.id should be >= 0
    }

    it("compares equal by identity") {
      tweet should not be tweet2
    }

    it("orders by age") {
      val t1 = new Tweet(user, "")
      val t2 = new Tweet(user, "")
      t1 should be < t2
    }

    it("converts to string") {
      tweet.toString shouldBe "@Dudebro: This is a tweet"
    }
  }
}
