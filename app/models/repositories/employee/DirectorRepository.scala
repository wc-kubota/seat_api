package models.repositories.employee

import models.entities.employee.Director
import models.vo.Count

import scala.concurrent.Future

trait DirectorRepository {
  def employeeCount(): Future[Count]
  def fetchDirector(): Future[Seq[Director]]

}
