package controllers.responses.seatingChart

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.SeatCount
import models.entities.seatingChart.Seat
import models.usecases.seatingChart.SeatOutput

final case class GetSeatResponse(
  message: String,
  allSeatCount: SeatCount,
  response: Seq[Seat]
)

object GetSeatResponse {

  def ok(seatOutput: SeatOutput): GetSeatResponse = {
    GetSeatResponse(
      "SUCCESS",
      SeatCount(seatOutput.allSeatCount.value),
      seatOutput.allSeats
    )
  }

  implicit val encoder: Encoder[GetSeatResponse] = (g: GetSeatResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.allSeatCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Seat] = (a: Seat) => {
    Json.obj(
      ("seat_id", a.seatId.value.asJson),
      ("seat_x", a.seatX.value.asJson),
      ("seat_Y", a.seatY.value.asJson),
      ("seat_height", a.seatHeight.value.asJson),
      ("seat_width", a.seatWidth.value.asJson),
      ("is_fixed", a.isFixed.value.asJson)
    )
  }
}
