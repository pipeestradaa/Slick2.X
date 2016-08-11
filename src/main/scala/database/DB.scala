package database
import scala.concurrent.Await
import scala.slick.driver.H2Driver.simple._
/**
  * Created by andres on 8/08/16.
  */
class DB {
  val personas = TableQuery[Personas]

  def getDB() = Database.forURL("jdbc:h2:mem:hello", driver = "org.h2.Driver")




}
