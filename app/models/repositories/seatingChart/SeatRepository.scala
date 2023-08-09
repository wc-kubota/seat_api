package models.repositories.seatingChart

import models.entities.seatingChart.Seat
import models.vo.Count

import scala.concurrent.Future

trait SeatRepository {
  def seatCount(): Future[Count]
  def fetchAllSeat(): Future[Seq[Seat]]

}
