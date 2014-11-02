# Volume

Clojure code to solve a simple algorithmic problem: finding the volume contained by a simple discrete 2D structure.

Given a vector of heights, the program returns the volume contained. For example, given [4 1 2 3] the program would return 3, due to the following:

```
#
#..#
#.##
####
4123
```

This program was written purely as an exercise in [Clojure](http://clojure.org) programming and [Light Table](http://lighttable.com/) usage.

There is little of interest to Clojure experts here, although some tests are written using [clojure.test.check](https://github.com/clojure/test.check), which is a Clojure property-based testing library inspired by Haskell's [QuickCheck](http://hackage.haskell.org/package/QuickCheck).

## Installation

Pre-requisites:

* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Leiningen](http://leiningen.org/)

Clone the repository, ```cd``` into it, and run ```lein repl```. You can then type commands such as ```(volume [2 1 2])``` which should give the result ```1```.

You can run the main function using ```lein run```. This will simply read space-separated structures from standard input and print the volume contained by each one.

To run the tests, run ```lein test```.

## License

Copyright © 2014 Andrew Medworth

Distributed under the BSD License: see LICENSE file for more details.
