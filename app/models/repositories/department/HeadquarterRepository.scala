package models.repositories.department

import models.entities.department.Headquarter
import models.vo.Count

import scala.concurrent.Future

trait HeadquarterRepository {
  def departmentCount(): Future[Count]
  def fetchHeadquarter(): Future[Seq[Headquarter]]

}
