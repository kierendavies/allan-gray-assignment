package za.co.kierendavies.tweetfeed

class UserSpec extends UnitSpec {
  describe("User") {
    val name = "Dudebro"
    val user = User.byName(name)
    val user2 = User.byName("OtherGuy")
    val user3 = User.byName("ItsaMeMario")

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
        user.following should have size 0
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
        val name4 = "ItsaMeLuigi"
        user.follow(name4)
        user.following should have size 3
        user.following should contain (User.byName(name4))
      }
    }

    describe("tweet") {
      val content = "This is a tweet"
      it("initially has no tweets") {
        user.tweets should have size 0
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
  }
}
