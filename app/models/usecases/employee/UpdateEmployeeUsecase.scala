package models.usecases.employee

import cats.data.EitherT
import models.entities.employee.Employee
import models.repositories.employee.EmployeeRepository
import models.usecases.UsecaseError
import models.usecases.employee.UpdateEmployeeUsecase.Error.{NoEmployeeData, UpdateEmployeeDataFailed}
import models.vo.EmployeeId

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース引数用オブジェクト
 * @param employee
 */
final case class UpdateEmployeeInput(
                                    employee: Employee
                                    )


/**
 * ユースケース戻り値用オブジェクト
 * @param result
 */
final case class UpdateEmployeeOutput(result: Either[UsecaseError, Unit])

object UpdateEmployeeUsecase {
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
 * 従業員情報更新ユースケース
 */
trait UpdateEmployeeUsecase {
  def handle()(implicit employeeId: EmployeeId, input: UpdateEmployeeInput): Future[UpdateEmployeeOutput]
}

class UpdateEmployeeUsecaseImpl @Inject()(
  employeeRepository: EmployeeRepository
)(implicit ex: ExecutionContext) extends UpdateEmployeeUsecase {

  override def handle()(implicit employeeId: EmployeeId, input: UpdateEmployeeInput): Future[UpdateEmployeeOutput] = {
    val result = for {
      _ <- EitherT(updateEmployee(input))
    } yield  ()

    result.value.map(UpdateEmployeeOutput(_))
  }


  private def updateEmployee(input: UpdateEmployeeInput)(implicit employeeId: EmployeeId): Future[Either[UsecaseError, Unit]] = {
    for {
      result <- employeeRepository.updateEmployee(input, employeeId)
    } yield
      if(result)
        Right(())
      else
        Left(UpdateEmployeeDataFailed)
  }
}



