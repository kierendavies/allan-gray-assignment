package za.co.kierendavies.tweetfeed

import scala.collection.mutable

class User private (val name: String) {
  override def toString = name
}

object User {
  private var users = new mutable.HashMap[String, User]

  def byName(name: String): User = {
    if (users contains name) {
      users(name)
    } else {
      val user = new User(name)
      users += (name -> user)
      user
    }
  }
}
