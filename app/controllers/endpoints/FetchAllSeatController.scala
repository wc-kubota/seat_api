package controllers.endpoints

import cats.data.EitherT
import javax.inject.Inject
import controllers.responses.{GetAllSeatResponse, ErrorResponse}
import io.circe.syntax.EncoderOps
import models.usecases.{AllSeatUsecase, AllSeatOutput}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext


class FetchAllSeatController @Inject()(
 allSeatUseCase: AllSeatUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def fetchAllSeat() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        allSeatUseCase.handle().map(r => (r.allSeatCount) match {
          case Count(0) => Left("NOT_FOUND_SEAT")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetAllSeatResponse.ok(r).asJson)
      case Left("NOT_FOUND_SEAT") => Ok(GetAllSeatResponse.ok(AllSeatOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}