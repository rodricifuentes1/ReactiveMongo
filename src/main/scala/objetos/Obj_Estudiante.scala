package objetos

import reactivemongo.api._
import reactivemongo.bson._

/**
 * Definicion de la clase estudiante
 */
case class Estudiante(id: String, nombre: String, apellidos: String, edad: Int) {
  override def toString = {
    nombre + " " + apellidos + " " + edad + " años"
  }
}

/**
 * Definicion del objeto Estudiante
 * Este objeto debe extender los traits BSONDocumentReader(leer de la DB) BSONDocumentWriter(guardar en la DB)
 * En la base de datos SOLO se guarda STRING, si la clase requiere tener algun dato como Double o Int, se debe obtener como String y parsearlo
 */
object Estudiante {

  implicit object EstudianteReader extends BSONDocumentReader[Estudiante] {
    def read(doc: BSONDocument): Estudiante = {
      Estudiante(
        doc.getAs[String]("_id").get,
        doc.getAs[String]("nombre").get,
        doc.getAs[String]("apellidos").get,
        doc.getAs[String]("edad").get.toInt)
    }
  }

  implicit object EstudianteWriter extends BSONDocumentWriter[Estudiante] {
    def write(estudiante: Estudiante): BSONDocument = {
      BSONDocument(
        "_id" -> estudiante.id,
        "nombre" -> estudiante.nombre,
        "apellidos" -> estudiante.apellidos,
        "edad" -> estudiante.edad.toString)
    }
  }

}
