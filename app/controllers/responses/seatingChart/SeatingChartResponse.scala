package controllers.responses.seatingChart

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.SeatCount
import models.entities.seatingChart.SeatingChart
import models.usecases.seatingChart.SeatingChartOutput

final case class GetSeatingChartResponse(
  message: String,
  allSeatingChartCount: SeatCount,
  response: Seq[SeatingChart]
)


object GetSeatingChartResponse {

  def ok(seatOutput: SeatingChartOutput): GetSeatingChartResponse = {
    GetSeatingChartResponse(
      "SUCCESS",
      SeatCount(seatOutput.allSeatCount.value),
      seatOutput.allSeatingChart
    )
  }

  implicit val encoder: Encoder[GetSeatingChartResponse] = (g: GetSeatingChartResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatingChartCount", g.allSeatingChartCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[SeatingChart] = (a: SeatingChart) => {
    Json.obj(
      ("employee_id", a.employeeId.value.asJson),
      ("seat_id", a.seatId.value.asJson)
    )
  }
}
