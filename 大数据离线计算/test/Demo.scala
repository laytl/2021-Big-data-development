package test

import java.sql.{DriverManager, ResultSet}
import java.util.Properties

object Demo {
  val url = "jdbc:hive2://bigdata118.depts.bingosoft.net:22118/user27_db"
  val properties = new Properties()
  properties.setProperty("driverClassName", "org.apache.hive.jdbc.HiveDriver")
  properties.setProperty("user", "user27")
  properties.setProperty("password", "pass@bingo27")

  val connection = DriverManager.getConnection(url, properties)

  val statement = connection.createStatement

}