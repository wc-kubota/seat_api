package models.usecases.employee

import models.entities.employee.Employee
import models.repositories.employee.EmployeeRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class EmployeeOutput(
   employeeCount: Count,
   employee: Seq[Employee]
)

/**
 * シートユースケース
 */
trait EmployeeUsecase {
  def handle(): Future[EmployeeOutput]
}

class EmployeeUsecaseImpl @Inject() (
  employeeRepository: EmployeeRepository
)(implicit ec: ExecutionContext) extends EmployeeUsecase {

  override def handle(): Future[EmployeeOutput] = {
    for {
      employee <- employeeRepository.fetchEmployee()
      employeeCount <- employeeRepository.employeeCount()
    } yield {
      EmployeeOutput(
        employeeCount,
        employee
      )
    }
  }
}
