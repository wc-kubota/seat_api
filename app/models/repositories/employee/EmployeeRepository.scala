package models.repositories.employee

import models.entities.employee.Employee
import models.usecases.employee.UpdateEmployeeInput
import models.vo.{Count, EmployeeId}

import scala.concurrent.Future

trait EmployeeRepository {
  def employeeCount(): Future[Count]
  def fetchEmployee(): Future[Seq[Employee]]
  def fetchEmployeeByEmployeeId(employeeId: EmployeeId): Future[Seq[Employee]]
  def updateEmployee(input: UpdateEmployeeInput, employeeId: EmployeeId): Future[Boolean]
}

