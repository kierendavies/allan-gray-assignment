package za.co.kierendavies.tweetfeed

class UserSpec extends UnitSpec {
  describe("User") {
    val name = "Alice"
    val user = User.byName(name)
    val user2 = User.byName("Bob")
    val user3 = User.byName("Carol")
    val name4 = "Dan"
    val content = "This is a tweet"
    val content2 = "This is also a tweet"

    describe("byName") {
      it("is constructed the first time") {
        user shouldBe a [User]
      }
      it("returns the same user for the same name") {
        User.byName(name) shouldBe user
      }
    }

    describe("follow") {
      it("initially does not follow anyone") {
        user.following shouldBe empty
      }
      it("adds a user to be followed") {
        user.follow(user2)
        user.following should have size 1
        user.following should contain (user2)
      }
      it("is idempotent") {
        user.follow(user2)
        user.follow(user2)
        user.following should have size 1
      }
      it("adds more users to be followed") {
        user.follow(user3)
        user.following should have size 2
        user.following should contain (user3)
      }
      it("can take a name") {
        user.follow(name4)
        user.following should have size 3
        user.following should contain (User.byName(name4))
      }
    }

    describe("tweet") {
      it("initially has no tweets") {
        user.tweets shouldBe empty
      }
      it("posts a tweet") {
        user.tweet(content)
        user.tweets should have size 1
        user.tweets.head.content shouldBe content
      }
      it("posts more tweets") {
        user.tweet(content)
        user.tweets should have size 2
      }
    }

    describe("feed") {
      it("has the right user") {
        user.feed.user shouldBe user
      }
      it("does not fail if the user has no followers") {
        user2.feed.tweets shouldBe empty
      }
      it("is empty if no followed users or the user himself have tweeted") {
        user2.follow(user3)
        user2.feed.tweets shouldBe empty
      }
      it("shows the user's own tweets") {
        user.feed.tweets should not be empty
        user.feed.tweets.map(_.user) should contain (user)
      }
      it("shows tweets from followed users") {
        user2.tweet(content)
        user3.tweet(content)
        user.feed.tweets.map(_.user) should contain allOf (user2, user3)
      }
      it("has its tweets in order of posting") {
        user.feed.tweets.map(_.id) shouldBe sorted
      }
    }

    describe("allFeeds") {
      it("is sorted by user name") {
        User.allFeeds.map(_.user.name) shouldBe sorted
      }
    }
  }
}
