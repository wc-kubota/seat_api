package controllers.endpoints.seatingChart

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.seatingChart.GetSeatingChartResponse
import io.circe.syntax.EncoderOps
import models.usecases.seatingChart.{SeatingChartOutput, SeatingChartUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class SeatingChartController @Inject()(
 seatingChartUseCase: SeatingChartUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def getSeatingChart() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        seatingChartUseCase.handle().map(r => (r.allSeatCount) match {
          case Count(0) => Left("NOT_FOUND_SEAT")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetSeatingChartResponse.ok(r).asJson)
      case Left("NOT_FOUND_SEAT") => Ok(GetSeatingChartResponse.ok(SeatingChartOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}