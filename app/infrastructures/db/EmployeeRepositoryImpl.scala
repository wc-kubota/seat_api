package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MEmployee
import models.entities.employee.Employee
import models.repositories.employee.EmployeeRepository
import models.usecases.employee.UpdateEmployeeInput
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class EmployeeRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with EmployeeRepository {

  override def employeeCount(): Future[Count] = {
    val query = MEmployee
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchEmployee(): Future[Seq[Employee]] = {
    val query = MEmployee
      .map(r => (
        r.employeeId,
        r.nameKanji,
        r.nameKana
      ))
      .result
      .map(_.map(r =>
        Employee(
          EmployeeId(r._1),
          NameKanji(r._2),
          NameKana(r._3)
        )
      ))

    db.run(query)
  }


  override def fetchEmployeeByEmployeeId(employeeId: EmployeeId): Future[Seq[Employee]] = {
    val query = MEmployee
      .filter(_.employeeId === employeeId.value)
      .map(r => (
        r.employeeId,
        r.nameKanji,
        r.nameKana
      ))
      .result
      .map(_.map(r =>
        Employee(
          EmployeeId(r._1),
          NameKanji(r._2),
          NameKana(r._3)
        )
      ))

    db.run(query)
  }

  override def updateEmployee(input: UpdateEmployeeInput, employeeId: EmployeeId): Future[Boolean] = {
    val query = MEmployee
      .filter(_.employeeId === input.employee.employeeId.value)
      .map(r => (
        r.nameKanji,
        r.nameKana
      ))

    db.run(query.update(
      input.employee.nameKanji.value,
      input.employee.nameKana.value
    )).map(_ == 1)
  }
}
