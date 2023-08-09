package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.{Seats, SeatsRow}
import models.entities.AllSeat
import models.repositories.AllSeatRepository
import models.vo.{Count, SeatEmployee, SeatHeight, SeatId, SeatWidth, SeatX, SeatY, StaticStatus}
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class AllSeatRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with AllSeatRepository {

  override def allSeatCount(): Future[Count] = {
    val query = Seats
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchAllSeat(): Future[Seq[AllSeat]] = {
    val query = Seats
      .map(r => (
        r.seatId,
        r.seatX,
        r.seatY,
        r.seatWidth,
        r.seatHeight,
        r.seatEmployee,
        r.staticStatus
      ))
      .result
      .map(_.map(r =>
        AllSeat(
          SeatId(r._1),
          SeatX(r._2),
          SeatY(r._3),
          SeatWidth(r._4),
          SeatHeight(r._5),
          SeatEmployee(r._6),
          StaticStatus(r._7)
        )
      ))

    db.run(query)
  }
}
