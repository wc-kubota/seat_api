package models.usecases.employee

import models.entities.employee.Employee
import models.repositories.employee.EmployeeRepository
import models.vo.{Count, EmployeeId}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class EmployeeByEmployeeIdOutput(
  employeeCount: Count,
  employee: Seq[Employee]
)

/**
 * シートユースケース
 */
trait EmployeeByEmployeeIdUsecase {
  def handle(employeeId: EmployeeId): Future[EmployeeByEmployeeIdOutput]
}

class EmployeeByEmployeeIdUsecaseImpl @Inject() (
  employeeRepository: EmployeeRepository
)(implicit ec: ExecutionContext) extends EmployeeByEmployeeIdUsecase {

  override def handle(employeeId: EmployeeId): Future[EmployeeByEmployeeIdOutput] = {
    for {
      employeeCount <- employeeRepository.employeeCount()
      personal <- employeeRepository.fetchEmployeeByEmployeeId(employeeId)
    } yield {
      EmployeeByEmployeeIdOutput(
        employeeCount,
        personal
      )
    }
  }
}
