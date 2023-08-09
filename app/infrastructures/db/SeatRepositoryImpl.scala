package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MSeat
import models.entities.seatingChart.Seat
import models.repositories.seatingChart.SeatRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class SeatRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with SeatRepository {

  override def seatCount(): Future[Count] = {
    val query = MSeat
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchAllSeat(): Future[Seq[Seat]] = {
    val query = MSeat
      .map(r => (
        r.seatId,
        r.seatX,
        r.seatY,
        r.seatHeight,
        r.seatWidth,
        r.isFixed
      ))
      .result
      .map(_.map(r =>
        Seat(
          SeatId(r._1),
          SeatX(r._2),
          SeatY(r._3),
          SeatWidth(r._4),
          SeatHeight(r._5),
          IsFixed(r._6)
        )
      ))

    db.run(query)
  }
}
