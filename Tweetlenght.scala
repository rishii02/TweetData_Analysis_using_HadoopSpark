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

