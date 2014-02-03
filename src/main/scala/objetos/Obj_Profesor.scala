package objetos

import reactivemongo.api._
import reactivemongo.bson._

case class Profesor(id: String, nombre: String, carrera: String, estudiantes: List[String]){
  override def toString = {
    nombre + " " + carrera + ". Estudiantes: " + estudiantes.toString()
  }
}

object Profesor {

  implicit object ProfesorReader extends BSONDocumentReader[Profesor] {
    def read(doc: BSONDocument): Profesor = {
      Profesor(
        doc.getAs[String]("_id").get,
        doc.getAs[String]("nombre").get,
        doc.getAs[String]("carrera").get,
        doc.getAs[List[String]]("estudiantes").get
       )
    }
  }

  implicit object ProfesorWriter extends BSONDocumentWriter[Profesor] {
    def write(profesor: Profesor): BSONDocument = {
      BSONDocument(
        "_id" -> profesor.id,
        "nombre" -> profesor.nombre,
        "carrera" -> profesor.carrera,
        "estudiantes" -> profesor.estudiantes
        )
    }
  }
}