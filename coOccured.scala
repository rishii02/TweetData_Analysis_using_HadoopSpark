object coOccured{
	def main(args:Array[String]){
		val rd = sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")
          // Load the input file as an RDD
	    val linesRDD = sc.textFile("/home/kapil/Downloads/AvengersEndgameDescTweet.txt")

	    // Create pairs of co-occurred words
	    val coOccurrencePairsRDD = linesRDD.flatMap(line => {
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

