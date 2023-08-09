package controllers.endpoints.seatingChart

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.seatingChart.GetSeatResponse
import io.circe.syntax.EncoderOps
import models.usecases.seatingChart.{SeatOutput, SeatUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class SeatController @Inject()(
 seatUseCase: SeatUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def getSeatByFloorId() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        seatUseCase.handle().map(r => (r.allSeatCount) match {
          case Count(0) => Left("NOT_FOUND_SEAT")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetSeatResponse.ok(r).asJson)
      case Left("NOT_FOUND_SEAT") => Ok(GetSeatResponse.ok(SeatOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}