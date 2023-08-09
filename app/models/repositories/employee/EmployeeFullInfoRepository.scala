package models.repositories.employee

import models.entities.employee.EmployeeFullInfo
import models.usecases.employee.{UpdateEmployeeFullInfoInput, UpdateEmployeeInput}
import models.vo.{Count, EmployeeId}

import scala.concurrent.Future

trait EmployeeFullInfoRepository {
  def employeeCount(): Future[Count]
  def fetchEmployeeFullInfo(): Future[Seq[EmployeeFullInfo]]
  def updateEmployeeFullInfo(input: UpdateEmployeeFullInfoInput, employeeId: EmployeeId): Future[Boolean]
}

