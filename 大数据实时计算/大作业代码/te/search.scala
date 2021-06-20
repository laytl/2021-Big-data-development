package te

import java.util.{Properties, Timer, TimerTask, UUID}

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema
import te.upload.produceToKafka

object search {
  /**
   * 输入的主题名称
   */
  val inputTopic = "Mrlay"
  /**
   * kafka地址
   */
  val bootstrapServers = "bigdata35.depts.bingosoft.net:29035,bigdata36.depts.bingosoft.net:29036,bigdata37.depts.bingosoft.net:29037"
  val d=new div()
  val filepath="E://桌面//data"//本地存储路径

  var change=false

  val timer = new Timer(true)
  var task = new TimerTask() {
    def run() {
      if(change){
        println("数据正在传入...")
        change=false
        d.upfile(filepath);
        println("完成传输，等待下一次传入...")
      }
    }
  };
  def main(args: Array[String]): Unit = {
    var s3Content=upload.readFile()
    produceToKafka(s3Content)
    timer.schedule(task,6000,6000)
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val kafkaProperties = new Properties()
    kafkaProperties.put("bootstrap.servers", bootstrapServers)
    kafkaProperties.put("group.id", UUID.randomUUID().toString)
    kafkaProperties.put("auto.offset.reset", "earliest")
    kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    val kafkaConsumer = new FlinkKafkaConsumer010[ObjectNode](inputTopic,
      new JSONKeyValueDeserializationSchema(true), kafkaProperties)
    kafkaConsumer.setCommitOffsetsOnCheckpoints(true)
    val inputKafkaStream = env.addSource(kafkaConsumer)

    inputKafkaStream.map { x =>
      d.save(filepath, x.get("value").get("sj").toString.split("/")(0).replace("\"", "").replace("\"", "")
        + "-" + x.get("value").get("sj").toString.split("/")(1), x.toString)
      change = true
    }
    env.execute()
  }

}
