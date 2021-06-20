package wo

import java.util.{Properties, Timer, TimerTask, UUID}

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.flink.streaming.util.serialization.JSONKeyValueDeserializationSchema

object search {
  /**
   * 输入的主题名称
   */
  val inputTopic = "mn_buy_ticket_1_xjun"
  /**
   * kafka地址
   */
  val bootstrapServers = "bigdata35.depts.bingosoft.net:29035,bigdata36.depts.bingosoft.net:29036,bigdata37.depts.bingosoft.net:29037"
  var cn=new count

  var change=false
  val timer = new Timer(true)
  var task = new TimerTask() {
    def run() {
      if(change){
        println("数据正在传入...")
        change=false
        cn.sum()
        println("完成传输，等待下一次传入...")
      }
    }
  };

  def main(args: Array[String]): Unit = {
      timer.schedule(task,1000,60000)
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val kafkaProperties = new Properties()
    kafkaProperties.put("bootstrap.servers", bootstrapServers)
    kafkaProperties.put("group.id", UUID.randomUUID().toString)
    kafkaProperties.put("auto.offset.reset", "earliest")
    kafkaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    kafkaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
//    kafkaProperties.put("deserializer.encoding","UTF-8")
    val kafkaConsumer = new FlinkKafkaConsumer010[ObjectNode](inputTopic,
    new JSONKeyValueDeserializationSchema(true), kafkaProperties)
    kafkaConsumer.setCommitOffsetsOnCheckpoints(true)
    val inputKafkaStream = env.addSource(kafkaConsumer)
    inputKafkaStream.map{x =>
      println(x)
      cn.cnt(x.get("value").get("destination").toString
        .replace("\"", "").replace("\"", ""))
      change=true
      println(cn.map)
    //cn.sum()
    }
    //
    println(change)
    env.execute()
  }
}
