package models.repositories.department

import models.entities.department.Group
import models.vo.Count

import scala.concurrent.Future

trait GroupRepository {
  def departmentCount(): Future[Count]
  def fetchGroup(): Future[Seq[Group]]

}
