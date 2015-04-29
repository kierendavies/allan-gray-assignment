package za.co.kierendavies.tweetfeed

import java.io.File

import scopt.OptionParser

object Application extends App {
  val parser = new OptionParser[Config]("tweetfeed") {
    opt[File]('u', "users") required() valueName("<file>") action {
      (f, c) => c.copy(userFile = f) }
    opt[File]('t', "tweets") required() valueName("<file>") action {
      (f, c) => c.copy(userFile = f) }
  }
  parser.parse(args, Config()) match {
    case None => System.exit(1)

    case Some(config) =>
      println(config.userFile.getPath)
      println(config.tweetFile.getPath)
  }
}
