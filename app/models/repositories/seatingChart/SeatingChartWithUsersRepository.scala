package models.repositories.seatingChart

import models.entities.seatingChart.SeatingChartWithUsers
import models.vo.Count

import scala.concurrent.Future

trait SeatingChartWithUsersRepository {
  def seatCount(): Future[Count]
  def fetchSeatingChartWithUsers(): Future[Seq[SeatingChartWithUsers]]

}
