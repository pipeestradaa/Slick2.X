import database.{DB, Persona, Personas}
import org.scalatest.FunSuite

import scala.slick.driver.H2Driver.simple._
/**
  * Created by andres on 8/08/16.
  */
class Scala2X extends FunSuite{
  val db = Database.forURL("jdbc:h2:mem:hello", driver = "org.h2.Driver")
  val personas = TableQuery[Personas]

  test("Select * from PERSONAS"){
    db.withSession{ implicit session =>

      personas.ddl.create
      personas += Persona(1, "Andres")
      personas += Persona(2, "Felipe")


      personas foreach { case (p: Persona) =>
        println("[ID]: " + p.id + "\t [NAME]: " + p.name)
      }
    }
    Thread.sleep(2000)
  }

  test("SELECT * FROM PERSONAS WHERE ID > 1"){
    db.withSession{ implicit session =>
      //iniciamos el objeto
      personas.ddl.create
      personas += Persona(1, "Andres")
      personas += Persona(2, "Felipe")

      //Construimos la consulta
      val query= personas.filter(_.id > 1)

      //realizamos la consulta con .list
      query.list foreach{
        case (p: Persona) =>
          println("[ID]: " + p.id + "\t [NAME]: " + p.name)
      }
    }
    Thread.sleep(2000)
  }

  test("UPDATE PERSONAS SET NAME='ANDRESINO' WHERE ID=1"){
    db.withSession { implicit session =>
      //iniciamos el objeto
      personas.ddl.create
      personas += Persona(1, "Andres")
      personas += Persona(2, "Felipe")
      //construimos la actualizacion con las columnas a modificar
      val updatePersona = personas.filter(_.id === 1).map(_.name)
      //pasamos los valores a actualiar y realizamos la consulta
      updatePersona.update("ANDRESINO")

      //imprimimos el resultado
      personas.filter(_.id === 1) foreach { case (p: Persona) =>
        println("[ID]: " + p.id + "\t [NAME]: " + p.name)
      }
    }
    Thread.sleep(2000)
  }

  test("DELETE FROM PERSONAS WHERE ID = 1"){
    db.withSession { implicit session =>
      //iniciamos el objeto
      personas.ddl.create
      personas += Persona(1, "Andres")
      personas += Persona(2, "Felipe")

      //Construimos la condicion del delete
      val deletePersona = personas.filter(_.id === 1)
      //Ejecutamos el delete
      deletePersona.delete

      //imprimimos el resultado
      personas foreach { case (p: Persona) =>
        println("[ID]: " + p.id + "\t [NAME]: " + p.name)
      }
    }
    Thread.sleep(3000)
  }
}
