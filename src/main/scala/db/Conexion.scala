package db

import scala.concurrent.ExecutionContext.Implicits.global
import reactivemongo.api._

object Conexion {
  
  //Driver para la conexion
  //val driver = new MongoDriver

  //Conexion con mongoDB
  val conexion = driver.connection(List("localhost"))

  //Conexion con la base de datos AMDB
  val baseDedatos = conexion("reactive")

  //Colección estudiantes que se encuentra en la base de datos AMDB
  val col_estudiantes = baseDedatos("estudiantes")

  //Colección profesores que se encuentra en la base de datos AMDB
  val col_profesores = baseDedatos("profesores")

}