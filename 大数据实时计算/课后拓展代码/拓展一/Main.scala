package woc

import java.util.{Timer, TimerTask}

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._

object Main {
  val target="b"
  var out="";
  val timer = new Timer(true)
  var task = new TimerTask() {
    def run() {
      println("1分钟内出现了b的次数："+out.count(_ == 'b'))
      out=""
    }
  };

  def main(args: Array[String]) {
    timer.schedule(task,60000,60000)//定时器每隔1分钟触发一次
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //Linux or Mac:nc -l 9999
    //Windows:nc -l -p 9999
    val text = env.socketTextStream("localhost", 9999)

    val stream = text.flatMap {
        _.toLowerCase.split("\\W+") filter {
          _.contains(target)}
    }.map {
      x=>(out=out+x)
    }

    env.execute("Window Stream WordCount")
  }
}
