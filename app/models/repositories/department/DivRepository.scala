package models.repositories.department

import models.entities.department.Div
import models.vo.Count

import scala.concurrent.Future

trait DivRepository {
  def departmentCount(): Future[Count]
  def fetchDiv(): Future[Seq[Div]]
}
