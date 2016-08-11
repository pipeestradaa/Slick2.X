package database

import scala.slick.driver.H2Driver.simple._
import scala.slick.lifted.{MappedProjection, ProvenShape}

/**
  * Created by andres on 8/08/16.
  */
case class Persona(id: Int, name: String)

class Personas(tag: Tag) extends Table[Persona](tag, "PERSONAS") {
  def id: Column[Int] = column[Int]("PER_ID",O.PrimaryKey)
  def name: Column[String] = column[String]("PER_NAME")
  def * = (id, name) <> (Persona.tupled, Persona.unapply)
}
