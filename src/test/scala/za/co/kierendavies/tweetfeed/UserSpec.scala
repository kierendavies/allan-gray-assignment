package za.co.kierendavies.tweetfeed

class UserSpec extends UnitSpec {
  describe("User") {
    describe("byName") {
      it("is constructed the first time") {
        User.byName("DoesNotExist") shouldBe a[User]
      }

      it("returns the same user for the same name") {
        val name = "Dudebro"
        val user = User.byName(name)
        User.byName(name) shouldBe user
      }
    }
  }
}
