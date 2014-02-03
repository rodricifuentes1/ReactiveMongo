package db

import db.Conexion._
import objetos.Estudiante
import objetos.Profesor
import play.api.libs.iteratee.Iteratee
import reactivemongo.bson.BSONDocument
import reactivemongo.bson.BSONDocumentIdentity
import reactivemongo.bson.BSONStringHandler
import reactivemongo.bson.Producer.nameValue2Producer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure

object DataBase {

  /**
   * Metodo que vacia la coleccion estudiantes
   */
  def vaciarEstudiantes = {
    col_estudiantes.drop
  }

  /**
   * Metodo que vacia la coleccion profesores
   */
  def vaciarProfesores = {
    col_profesores.drop
  }

  def vaciarTodo = {
    vaciarEstudiantes
    vaciarProfesores
  }
  
  /**
   * Metodo que devuelve un cursor de estudiantes
   */
  def darEstudiantes = {
    val query = BSONDocument()
    col_estudiantes.find(query).cursor[Estudiante]
  }

  /**
   * Metodo que inserta un estudiante
   */
  def insertarEstudiante(est: Estudiante) = {
    col_estudiantes.insert(est)
  }

  /**
   * Metodo que eliminar un estudiante dado su ID
   */
  def eliminarEstudiante(id: String) = {
    val query = BSONDocument("_id" -> id)
    col_estudiantes.remove(query)
  }

  def encontrarYRecorrerEstudiantes = {

    //Se obtiene el cursor
    val cursor = darEstudiantes

    //Primera forma de recorrer
    cursor.enumerate().apply(Iteratee.foreach { doc =>
      println(doc.toString)
    })

    //Segunda forma de recorrer
    val futureList = cursor.toList()
    futureList.map(list => list.foreach(est => println(est.toString)))
  }

  def insertarProfesor(pro: Profesor) = {
    col_profesores.insert(pro)
  }

  /**
   * Metodo que eliminar un profesor dado su ID
   */
  def eliminarProfesor(id: String) = {
    val query = BSONDocument("_id" -> id)
    col_profesores.remove(query)
  }

  /**
   * Metodo que devuelve un cursor de estudiantes
   */
  def darProfesores = {
    val query = BSONDocument()
    col_profesores.find(query).cursor[Profesor]
  }

  def encontrarYRecorrerProfesores = {

    //Se obtiene el cursor
    val cursorP = darProfesores

    //Primera forma de recorrer
    cursorP.enumerate().apply(Iteratee.foreach { prof =>
      println(prof.toString)
    })

    //Segunda forma de recorrer
    val futureList = cursorP.toList()
    futureList.map(list => list.foreach(prof => println(prof.toString)))
  }

  def obtenerEstudiantesDeProfesor(idProfesor: String) = {
    val query = BSONDocument("_id" -> idProfesor)
    val listaFuture = col_profesores.find(query).one[Profesor]
  }

  def main(args: Array[String]) {
  }
}