the spark project done by us is anagram in  scala language
------------------------------------------------------------------------------------------------------------------------------------------------------------
versions used:
------------------------------------------------------------------------------------------------------------------------------------------------------------
java -version
      openjdk version "11.0.16" 2022-07-19
      OpenJDK Runtime Environment (build 11.0.16+8-post-Ubuntu-0ubuntu122.04)
      OpenJDK 64-Bit Server VM (build 11.0.16+8-post-Ubuntu-0ubuntu122.04, mixed mode, sharing)

used spark version 3.4.0 hadoop 3
------------------------------------------------------------------------------------------------------------------------------------------------------------
-> The code for the project :

object Tweetlenght{
	def main(args:Array[String]){
		val rd = sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")

		val sum: Int = rd.map(line => line.split("\\s+").length).reduce(_ + _)

		println(sum)

		val count: Long = rd.count() 

		val average: Double = sum.toDouble / count.toDouble

		val result: Double = average

		println(result)
	    sc.stop()
	}
}

object coOccured{
	def main(args:Array[String]){
		val rd = sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")
          // Load the input file as an RDD

	    val linesRDD = sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")

	    // Create pairs of co-occurred words
	    val coOccurredPairsRDD = linesRDD.flatMap(line => {
		val words = line.split("\\s+")
		words.combinations(2).toList
	      }).map(pair => (pair.mkString(" "), 1))

	    // Reduce to get the count of each co-occurred word pair
	    val coOccurrenceCountsRDD = coOccurrencePairsRDD.reduceByKey(_ + _)

	    // Sort by count in descending order
	    val sortedCountsRDD = coOccurrenceCountsRDD.sortBy(-_._2)

	    // Take the top 100 co-occurred word pairs
	    val top100Pairs = sortedCountsRDD.take(100)
	    for(x<-top100Pairs){
	    println(x)
	    }
	    sc.stop()
	}
}
  



The above code is saved in a scala file and named as Tweetlenght.scala and coOccured.scala . In the code 1 we use Tweetlenght as object and in code 2 we use coOccured
------------------------------------------------------------------------------------------------------------------------------------------------------------
-> Steps to execute the above code in spark-shell

1) save the above code in the .scala file named as Tweetlenght.scala
2) execute command spark-shell on the terminal which runs the sparksession
3) load the file using :load command in the spark which is (:load Tweetlenght.scala),(:load coOccured.scala)
4) it will compile the object Tweetlenght, coOccured.
5) run the file by executing = Tweetlenght.main(Array()) and coOccured.main(Array())
6) it will run the code and display output
------------------------------------------------------------------------------------------------------------------------------------------------------------
-> The series of commands after saving the Tweetlenght code are
1)spark-shell
2):load Tweetlenght.scala
3) Tweetlenght.main(Array())

------------------------------------------------------------------------------------------------------------------------------------------------------------
-> The series of commands after saving the Tweetlenght code are
1)spark-shell
2):load coOccured.scala
3) coOccured.main(Array())


------------------------------------------------------------------------------------------------------------------------------------------------------------

step by step explaination of coOCcured code:

1. The code defines an object called `coOccured` that contains a `main` method. This is the entry point of the Scala program.

2. Inside the `main` method, the code first loads an input file called "AvengersEndgameDescTweet.txt" using `sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")`. The `sc` variable is assumed to be an instance of `SparkContext`, which is used for creating and manipulating RDDs (Resilient Distributed Datasets) in Apache Spark.

3. The code then creates an RDD called `linesRDD` by again loading the same input file. This RDD represents each line in the file as an element.

4. Next, the code applies transformations on `linesRDD` to generate pairs of co-occurring words. It uses the `flatMap` transformation to split each line into words, and then uses the `combinations(2)` method to generate all possible combinations of two words. Each pair of words is then mapped to a key-value pair, where the key is the pair of words joined by a space (" ") and the value is 1.

5. The resulting RDD, `coOccurredPairsRDD`, represents all the co-occurring word pairs along with a count of 1 for each pair.

6. The code further applies transformations to the `coOccurredPairsRDD`. It uses the `reduceByKey` transformation to sum up the counts of each co-occurring word pair.

7. The resulting RDD, `coOccurrenceCountsRDD`, contains each co-occurring word pair along with its count.

8. The code then uses the `sortBy` transformation to sort the co-occurring word pairs by count in descending order, resulting in the RDD `sortedCountsRDD`.

9. The code uses the `take(100)` action to retrieve the top 100 co-occurring word pairs from the `sortedCountsRDD`.

10. Finally, the code loops over the top 100 pairs and prints each pair using `println`. After that, it stops the SparkContext (`sc.stop()`), indicating the end of the program.

Overall, this code calculates the co-occurrence of words in a text file using Apache Spark's RDD operations and prints the top 100 co-occurring word pairs along with their counts.

------------------------------------------------------------------------------------------------------------------------------------------------------------

step by step to find the tweet lenght in terms of words:

1. The code defines an object called `Tweetlenght` that contains a `main` method. This serves as the entry point of the Scala program.

2. Inside the `main` method, the code loads an input file called "AvengersEndgameDescTweet.txt" using `sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")`. The `sc` variable is assumed to be an instance of `SparkContext`.

3. The code then applies transformations and actions on the RDD `rd` to calculate the sum of the lengths of all tweets in the input file. It uses the `map` transformation to split each line into words and calculate the length of each line by calling `line.split("\\s+").length`. The resulting RDD contains the lengths of individual tweets. Then, the `reduce` action is applied to sum up all the lengths into a single value, which is assigned to the variable `sum`.

4. The code prints the value of `sum` using `println(sum)`. This will output the total sum of tweet lengths.

5. Next, the code uses the `count` action on RDD `rd` to calculate the total number of tweets in the input file. The result is assigned to the variable `count`.

6. The code calculates the average tweet length by dividing `sum.toDouble` (the total sum of tweet lengths) by `count.toDouble` (the total number of tweets). The result is assigned to the variable `average`.

7. The value of `average` is then assigned to the variable `result`.

8. Finally, the code prints the value of `result` using `println(result)`. This will output the average tweet length.

9. The SparkContext is stopped using `sc.stop()`, indicating the end of the program.

Overall, this code calculates the sum of tweet lengths and the average tweet length using Apache Spark's RDD operations and prints the results.