package models.usecases.employee

import cats.data.EitherT
import models.entities.employee.EmployeeFullInfo
import models.repositories.employee.{EmployeeFullInfoRepository, EmployeeRepository}
import models.usecases.UsecaseError
import models.usecases.employee.UpdateEmployeeUsecase.Error.UpdateEmployeeDataFailed
import models.vo.EmployeeId

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース引数用オブジェクト
 * @param employee
 */
final case class UpdateEmployeeFullInfoInput(
                                    employeeFullInfo: EmployeeFullInfo
                                    )


/**
 * ユースケース戻り値用オブジェクト
 * @param result
 */
final case class UpdateEmployeeFullInfoOutput(result: Either[UsecaseError, Unit])

object UpdateEmployeeFullInfoUsecase {
  sealed trait Error extends UsecaseError
  object Error {

    case object DBError extends Error {
      override def toString: String = "DB.ERROR"
    }

    case object NoEmployeeData extends Error {
      override def toString: String = "NO_EMPLOYEE_DATA"
    }

    case object UpdateEmployeeDataFailed extends Error {
      override def toString: String = "UPDATE_EMPLOYEE_DATA_FAILED"
    }
  }
}

/**
 * 従業員詳細情報更新ユースケース
 */
trait UpdateEmployeeFullInfoUsecase {
  def handle()(implicit employeeId: EmployeeId, input: UpdateEmployeeFullInfoInput): Future[UpdateEmployeeFullInfoOutput]
}

class UpdateEmployeeFullInfoUsecaseImpl @Inject()(
  employeeFullInfoRepository: EmployeeFullInfoRepository
)(implicit ex: ExecutionContext) extends UpdateEmployeeFullInfoUsecase {

  override def handle()(implicit employeeId: EmployeeId, input: UpdateEmployeeFullInfoInput): Future[UpdateEmployeeFullInfoOutput] = {
    val result = for {
      _ <- EitherT(updateEmployeeFullInfo(input))
    } yield  ()

    result.value.map(UpdateEmployeeFullInfoOutput(_))
  }


  private def updateEmployeeFullInfo(input: UpdateEmployeeFullInfoInput)(implicit employeeId: EmployeeId): Future[Either[UsecaseError, Unit]] = {
    for {
      result <- employeeFullInfoRepository.updateEmployeeFullInfo(input, employeeId)
    } yield
      if(result)
        Right(())
      else
        Left(UpdateEmployeeDataFailed)
  }
}
