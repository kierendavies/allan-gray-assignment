package za.co.kierendavies.tweetfeed

import java.io.File

case class Config(
  userFile: File = new File("."),
  tweetFile: File = new File("."))
