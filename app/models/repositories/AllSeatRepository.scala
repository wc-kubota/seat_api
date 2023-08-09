package models.repositories

import models.entities.AllSeat
import models.vo.Count

import scala.concurrent.Future

trait AllSeatRepository {
  def allSeatCount(): Future[Count]
  def fetchAllSeat(): Future[Seq[AllSeat]]

}
