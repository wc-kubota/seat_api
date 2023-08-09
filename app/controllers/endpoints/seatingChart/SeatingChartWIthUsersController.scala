package controllers.endpoints.seatingChart

import cats.data.EitherT
import controllers.responses.seatingChart.GetSeatingChartWithUsersResponse
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

//response
import controllers.responses.ErrorResponse


import io.circe.syntax.EncoderOps

//usecases
import models.usecases.seatingChart.{SeatingChartWithUsersOutput, SeatingChartWithUsersUsecase}

//entity
import models.entities.seatingChart.{Seat, SeatingChartWithUsers}
import models.entities.employee.Employee


//vo
import models.vo.Count



class SeatingChartWithUsersController @Inject()(
 seatingChartWithUsersUseCase: SeatingChartWithUsersUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得し、着席している従業員情報を返すエンドポイント
   * @return
   */
  def getSeatingChartWithUsers() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        seatingChartWithUsersUseCase.handle().map(r => (r.allSeatCount) match {
          case Count(0) => Left("NOT_FOUND_SEAT")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(
        GetSeatingChartWithUsersResponse.ok(r).asJson)

      case Left("NOT_FOUND_SEAT") => Ok(GetSeatingChartWithUsersResponse.ok(SeatingChartWithUsersOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}