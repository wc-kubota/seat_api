package models.usecases.employee

import models.entities.employee.EmployeeFullInfo
import models.repositories.employee.EmployeeFullInfoRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class EmployeeFullInfoOutput(
   employeeCount: Count,
   employeeFullInfo: Seq[EmployeeFullInfo]
)

/**
 * シートユースケース
 */
trait EmployeeFullInfoUsecase {
  def handle(): Future[EmployeeFullInfoOutput]
}

class EmployeeFullInfoUsecaseImpl @Inject() (
  employeeFullInfoRepository: EmployeeFullInfoRepository
)(implicit ec: ExecutionContext) extends EmployeeFullInfoUsecase {

  override def handle(): Future[EmployeeFullInfoOutput] = {
    for {
      employeeFullInfo <- employeeFullInfoRepository.fetchEmployeeFullInfo()
      employeeCount <- employeeFullInfoRepository.employeeCount()
    } yield {
      EmployeeFullInfoOutput(
        employeeCount,
        employeeFullInfo
      )
    }
  }
}
