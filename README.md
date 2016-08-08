#NOStream#

Java library that avoid use of Stream interface.

* Current classes: <br />
   * Collection, List, ArrayList, LinkedList extends the counterpart Java structures . <br />
   * ListOperation : Interface with default methods that are a my implementation of the fuctions. <br />
* Fuctions are: <br />
   * all
   * any
   * count
   * distinct
   * filter
   * filterIndexed
   * filterNotNull
   * first
   * firstOrNull
   * forEach
   * forEachIndexed
   * forEachReverse
   * groupBy
   * intersection
   * last
   * lastOrNull
   * map
   * mapIndexed
   * maxBy
   * minBy
   * orderBy
   * orderDecrescentBy
   * reduce
   * reduceReverse
   * reverse
   * take
   * takeLast
   * union
   * zipIndexed
   * zipWith
* How to use: <br\>
&emsp;    Import jar in your project then replace java.util.[Collection name] with NOStream.structures.[Collection name]