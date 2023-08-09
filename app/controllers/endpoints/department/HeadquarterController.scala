package controllers.endpoints.department

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.department.GetHeadquarterResponse
import io.circe.syntax.EncoderOps
import models.usecases.department.{HeadquarterOutput,HeadquarterUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class HeadquarterController @Inject()(
 headquarterUsecase: HeadquarterUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def fetchHeadquarter() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        headquarterUsecase.handle().map(r => (r.departmentCount) match {
          case Count(0) => Left("NOT_FOUND_HEADQUARTER")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetHeadquarterResponse.ok(r).asJson)
      case Left("NOT_FOUND_HEADQUARTER") => Ok(GetHeadquarterResponse.ok(HeadquarterOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}