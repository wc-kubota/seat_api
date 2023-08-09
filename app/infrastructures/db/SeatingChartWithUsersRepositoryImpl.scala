package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.{MEmployee, MSeat, SeatAssignment}
import models.entities.employee.Employee
import models.entities.seatingChart.{SeatingChartWithUsers, Seat}
import models.repositories.seatingChart.SeatingChartWithUsersRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class SeatingChartWithUsersRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with SeatingChartWithUsersRepository {

  override def seatCount(): Future[Count] = {
    val query = SeatAssignment
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchSeatingChartWithUsers(): Future[Seq[SeatingChartWithUsers]] = {
    val query = (
      for {
      ((seatingChart, seatAssignment), employee) <- MSeat join SeatAssignment on (_.seatId === _.seatId) join MEmployee on (_._2.employeeId === _.employeeId)
      } yield (seatingChart, employee)
    ).result
      .map(_.map{
        case (seatingChart, employee) =>
          SeatingChartWithUsers(
            Employee(
              EmployeeId(employee.employeeId),
              NameKanji(employee.nameKanji),
              NameKana(employee.nameKana)
            ),
            Seat(
              SeatId(seatingChart.seatId),
              SeatX(seatingChart.seatX),
              SeatY(seatingChart.seatY),
              SeatWidth(seatingChart.seatWidth),
              SeatHeight(seatingChart.seatHeight),
              IsFixed(seatingChart.isFixed)
            )
          )
      })

    db.run(query)
  }
}
