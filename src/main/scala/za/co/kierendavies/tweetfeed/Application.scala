package za.co.kierendavies.tweetfeed

import java.io.File

import scopt.OptionParser

object Application extends App {
  val parser = new OptionParser[Config]("tweetfeed") {
    opt[File]('u', "users") required() action { (f, c) => c.copy(userFile = f) }
    opt[File]('t', "tweets") required() action { (f, c) => c.copy(userFile = f) }
  }
  parser.parse(args, Config()) match {
    case Some(config) =>
      println(config.userFile.getPath)
      println(config.tweetFile.getPath)

    case None =>
      println("bad arguments")
  }
}
