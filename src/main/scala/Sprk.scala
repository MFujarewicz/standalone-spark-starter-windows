import com.globalmentor.apache.hadoop.fs.BareLocalFileSystem
import org.apache.hadoop.fs.FileSystem
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level


object Sprk extends App{

  Logger.getLogger("org").setLevel(Level.OFF)

  val spark = SparkSession.builder.appName("Foo Bar").master("local").getOrCreate

  spark.sparkContext.hadoopConfiguration.setClass("fs.file.impl", classOf[BareLocalFileSystem], classOf[FileSystem])

  spark.sql("SELECT 1").show()

}
