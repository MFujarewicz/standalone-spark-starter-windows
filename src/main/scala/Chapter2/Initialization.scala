package Chapter2

import com.globalmentor.apache.hadoop.fs.BareLocalFileSystem
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.functions._


object Initialization extends App {

  Logger.getLogger("org").setLevel(Level.OFF)
  val spark = SparkSession.builder.appName("hello mordo").master("local").getOrCreate
  spark.sparkContext.hadoopConfiguration.setClass("fs.file.impl", classOf[BareLocalFileSystem], classOf[FileSystem])

  import spark.implicits._

  val myRange = spark.range(1000).toDF("number")


  val evenRange = myRange.where("number % 2 = 0")
  evenRange.show()

  myRange.filter(col("number") % 2 === 0).show()

  println(myRange.count())
  println(evenRange.count())
}
