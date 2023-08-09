package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.SeatAssignment
import models.entities.seatingChart.SeatingChart
import models.repositories.seatingChart.SeatingChartRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class SeatingChartRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with SeatingChartRepository {

  override def seatCount(): Future[Count] = {
    val query = SeatAssignment
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchSeatingChart(): Future[Seq[SeatingChart]] = {
    val query = SeatAssignment
      .map(r => (
        r.employeeId,
        r.seatId,
      ))
      .result
      .map(_.map(r =>
        SeatingChart(
          EmployeeId(r._1),
          SeatId(r._2)
        )
      ))

    db.run(query)
  }
}
