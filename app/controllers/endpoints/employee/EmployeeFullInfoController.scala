package controllers.endpoints.employee

import cats.data.EitherT
import controllers.requests.UpdateEmployeeFullInfoRequest
import controllers.responses.ErrorResponse
import controllers.responses.employee.{GetEmployeeFullInfoResponse, UpdateEmployeeFullInfoResponse}
import io.circe.syntax.EncoderOps
import models.usecases.employee.{EmployeeFullInfoOutput, EmployeeFullInfoUsecase, UpdateEmployeeFullInfoUsecase}
import models.vo.{Count, EmployeeId}
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, Action, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class EmployeeFullInfoController @Inject()(
                                            employeeFullInfoUsecase: EmployeeFullInfoUsecase,
                                            updateEmployeeFullInfoUsecase: UpdateEmployeeFullInfoUsecase,
                                            cc:ControllerComponents
                                          )( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全従業員の詳しい情報を取得するエンドポイント
   * @return
   */
  def fetchEmployeeFullInfo() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        employeeFullInfoUsecase.handle().map(r => (r.employeeCount) match {
          case Count(0) => Left("NOT_FOUND_DIv")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetEmployeeFullInfoResponse.ok(r).asJson)
      case Left("NOT_FOUND_Employee") => Ok(GetEmployeeFullInfoResponse.ok(EmployeeFullInfoOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }

  /**
   * 従業員の詳しい情報を更新
   * @param employeeId
   * @return
   */

  def updateEmployeeFullInfo(employeeId: Int) = (Action(circe.json[UpdateEmployeeFullInfoRequest])
    ).async { implicit request =>
    for {
      employeeFullInfo <- updateEmployeeFullInfoUsecase.handle()(EmployeeId.apply(Option(employeeId)), request.body.toUpdateEmployeeFullInfoInput())
    } yield employeeFullInfo.result match {
      case Right(_) =>
        Ok(UpdateEmployeeFullInfoResponse.UpdatedEmployeeResponse().asJson)
      case Left(error) =>
        InternalServerError(UpdateEmployeeFullInfoResponse.FailedUpdateEmployeeFullInfoResponse("FAILED", error.toString).asJson)
    }
  }
}