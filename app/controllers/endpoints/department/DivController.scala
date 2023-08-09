package controllers.endpoints.department

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.department.GetDivResponse
import io.circe.syntax.EncoderOps
import models.usecases.department.{DivOutput, DivUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class DivController @Inject()(
 divUsecase: DivUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def fetchDiv() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        divUsecase.handle().map(r => (r.departmentCount) match {
          case Count(0) => Left("NOT_FOUND_DIv")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetDivResponse.ok(r).asJson)
      case Left("NOT_FOUND_Div") => Ok(GetDivResponse.ok(DivOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}