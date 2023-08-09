package models.repositories.seatingChart

import models.entities.seatingChart.SeatingChart
import models.vo.Count

import scala.concurrent.Future

trait SeatingChartRepository {
  def seatCount(): Future[Count]
  def fetchSeatingChart(): Future[Seq[SeatingChart]]

}
