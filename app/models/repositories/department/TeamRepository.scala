package models.repositories.department

import models.entities.department.Team
import models.vo.Count

import scala.concurrent.Future

trait TeamRepository {
  def departmentCount(): Future[Count]
  def fetchTeam(): Future[Seq[Team]]

}
