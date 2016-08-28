#NOStream#

Java library that avoid use of Stream interface.
* Pros: <br />
   * Faster than stream counterpart (Test in project can dimostrate it)
   * More simple syntactically
* How it works: <br />
   * Collection, List, ArrayList, LinkedList extends the counterpart Java structures and implement my list trasformation methods (Operation interface) . <br />
   * Import jar in your project then replace java.util.[Collection name] with structures.[Collection name] <br/>
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
   * orderDecreasingBy
   * reduce
   * reduceReverse
   * reverse
   * take
   * takeLast
   * union
   * zipIndexed
   * zipWith
* How to use: <br/>
&emsp;    Import jar in your project then replace java.util.[Collection name] with structures.[Collection name]
* How to download jar: <br/>
&emsp;  <a href="http://goo.gl/I2s5pb">NOStream Download </a>
