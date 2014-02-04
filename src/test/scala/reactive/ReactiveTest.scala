package reactive

import org.junit.Test
import objetos.Estudiante
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import db.DataBase
import objetos.Profesor
import org.junit.Before
import org.scalatest.FunSuite

class ReactiveTest extends FunSuite {

  val timeout = 20 seconds

  @Before def vaciar {
    Await.ready(DataBase.vaciarTodo, timeout)
  }

  @Test def crear {
    val est = Estudiante("1", "Estudiante", "Prueba", 20)
    assert(Await.result(DataBase.insertarEstudiante(est), timeout).ok == true)

    val prof = Profesor("1", "Profesor", "Isis", List("1"))
    assert(Await.result(DataBase.insertarProfesor(prof), timeout).ok == true)
  }

}