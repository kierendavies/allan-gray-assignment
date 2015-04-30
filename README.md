# Tweet Feed

Coding assignment for Allan Gray application screening.  See the [task description](ASSIGNMENT.md).

I took this as an opportunity to learn Scala, which I have been wanting to do for a while.  I made an effort to use the language as idiomatically as possible.

## Prerequisites

The application was only tested with the versions of the dependencies listed below.  Other versions may work.

* [Java Development Kit](http://www.oracle.com/technetwork/java/index.html) 1.8.0
* [Scala](http://www.scala-lang.org/) 2.11.6
* [sbt](http://www.scala-sbt.org/) 0.13.8

Verify unit tests with `sbt test`.

## Running

Run the application with `sbt "run -u <userfile> -t <tweetfile>"`.  For example, `sbt "run -u sample/user.txt -t sample/tweet.txt"`

## Missing functionality

### Test coverage

`Application`, `FileParser`, `TweetFileParser` and `UserFileParser` do not have any test coverage.  This is because they require various degrees of mocking, and I could not come to grips with any of the Scala or Java mocking frameworks in the time available.

### Concurrency

The components written are reusable, but are not thread-safe.  Making them thread-safe requires synchronization on `Tweet.nextId` and `User.byName`, and making use of concurrent data structures for `User.following` and `User.tweets`.  Concurrent data structures may have significant performance implications.
