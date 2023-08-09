package controllers.responses

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.{AllSeat, SeatCount}
import models.usecases.AllSeatOutput

final case class GetAllSeatResponse(
  message: String,
  allSeatCount: SeatCount,
  response: Seq[AllSeat]
)

object GetAllSeatResponse {

  def ok(allSeatOutput: AllSeatOutput): GetAllSeatResponse = {
    GetAllSeatResponse(
      "SUCCESS",
      SeatCount(allSeatOutput.allSeatCount.value),
      allSeatOutput.allSeats
    )
  }

  implicit val encoder: Encoder[GetAllSeatResponse] = (g: GetAllSeatResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.allSeatCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[AllSeat] = (a: AllSeat) => {
    Json.obj(
      ("seat_id", a.seatId.value.asJson),
      ("seat_x", a.seatX.value.asJson),
      ("seat_Y", a.seatY.value.asJson),
      ("seat_width", a.seatWidth.value.asJson),
      ("seat_height", a.seatHeight.value.asJson),
      ("seat_employee", a.seatEmployee.value.asJson),
      ("static_status", a.seatStaticStatus.value.asJson)
    )
  }
}
