package controllers.endpoints.employee

import cats.data.EitherT
import controllers.requests.UpdateEmployeeRequest
import controllers.responses.ErrorResponse
import controllers.responses.employee.{GetEmployeeByEmployeeIdResponse, GetEmployeeResponse, UpdateEmployeeResponse}
import io.circe.syntax.EncoderOps
import models.usecases.employee.{EmployeeByEmployeeIdUsecase, EmployeeOutput, EmployeeUsecase, UpdateEmployeeUsecase}
import models.vo.{Count, EmployeeId}
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.Option
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class EmployeeController @Inject()(
 employeeUsecase: EmployeeUsecase,
 updateEmployeeUsecase: UpdateEmployeeUsecase,
 employeeByEmployeeIdUsecase: EmployeeByEmployeeIdUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全従業員情報を取得するエンドポイント
   * @return
   */
  def fetchEmployee() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        employeeUsecase.handle().map(r => (r.employeeCount) match {
          case Count(0) => Left("NOT_FOUND_DIV")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetEmployeeResponse.ok(r).asJson)
      case Left("NOT_FOUND_Employee") => Ok(GetEmployeeResponse.ok(EmployeeOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }

  /**
   * employeeIdの従業員情報を取得するエンドポイント
   *
   * @return
   */
  def fetchEmployeeByEmployeeId(employeeId: Int) = Action.async { implicit request =>
    (for {
      result <- EitherT {
        employeeByEmployeeIdUsecase.handle(EmployeeId.apply(Option(employeeId))).map(r => (r.employeeCount) match {
          case Count(0) => Left("NOT_FOUND_DIV")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetEmployeeByEmployeeIdResponse.ok(r).asJson)
      case Left("NOT_FOUND_Employee") => Ok(GetEmployeeResponse.ok(EmployeeOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }

  /**
   * 従業員情報の更新
   * @param employeeId
   * @return
   */

  def updateEmployee(employeeId: Int) = (Action(circe.json[UpdateEmployeeRequest])
    ).async { implicit request =>
    for {
      employee <- updateEmployeeUsecase.handle()(EmployeeId.apply(Option(employeeId)), request.body.toUpdateEmployeeInput())
    } yield employee.result match {
      case Right(_) =>
        Ok(UpdateEmployeeResponse.UpdatedEmployeeResponse().asJson)
      case Left(error) =>
        InternalServerError(UpdateEmployeeResponse.FailedUpdateEmployeeResponse("Failed", error.toString).asJson)
    }
  }
}