import java.sql.DriverManager
import java.util.Properties

import com.bingocloud.{ClientConfiguration, Protocol}
import com.bingocloud.auth.BasicAWSCredentials
import com.bingocloud.services.s3.AmazonS3Client
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.nlpcn.commons.lang.util.IOUtil

object upload {
  //mysql参数
  val url = "jdbc:mysql://bigdata130.depts.bingosoft.net:24306/user27_db"
  val properties = new Properties()
  properties.setProperty("driverClassName", "com.mysql.jdbc.Driver")
  properties.setProperty("user", "user27")
  properties.setProperty("password", "pass@bingo27")

  val connection = DriverManager.getConnection(url, properties)

  val statement = connection.createStatement

  //要读取的文件

  //kafka参数
  val topic = "ta"
  val bootstrapServers = "bigdata35.depts.bingosoft.net:29035,bigdata36.depts.bingosoft.net:29036,bigdata37.depts.bingosoft.net:29037"

  def main(args: Array[String]): Unit = {
    val s3Content = readFile()
    var st=s3Content.split(";")
    for(j <-0 to st.length-1){
      produceToKafka(st(j))
    }

  }

  /**
   * 从mysql中读取文件内容
   *
   * @return mysql的表内容
   */
  def readFile(): String = {
    var strname=""
    var resultS = statement.executeQuery("select * from libin");
    while(resultS.next()){
      var col=resultS.getMetaData.getColumnCount
      var str="{"
      for(i<-1 to col){
        str=str+"\""+resultS.getMetaData.getColumnName(i)+"\":"+"\""+resultS.getString(i)+"\","
      }
      str=str+"};"
      strname=strname+str
    }
    return strname
  }

  /**
   * 把数据写入到kafka中
   *
   * @param s3Content 要写入的内容
   */
  def produceToKafka(s3Content: String): Unit = {
    val props = new Properties
    props.put("bootstrap.servers", bootstrapServers)
    props.put("acks", "all")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val dataArr = s3Content.split("\n")
    for (s <- dataArr) {
      if (!s.trim.isEmpty) {
        val record = new ProducerRecord[String, String](topic, null, s)
        println("开始生产数据：" + s)
        producer.send(record)
      }
    }
    producer.flush()
    producer.close()
  }
}