package controllers.responses.seatingChart

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.SeatCount
import models.entities.seatingChart.{Seat, SeatingChartWithUsers}
import models.usecases.seatingChart.SeatingChartWithUsersOutput
import models.entities.employee.Employee


final case class GetSeatingChartWithUsersResponse(
                                          message: String,
                                          allSeatingChartCount: SeatCount,
                                          response: Seq[SeatingChartWithUsers]
                                        )


object GetSeatingChartWithUsersResponse {

  def ok(seatingChartWithUsersOutput: SeatingChartWithUsersOutput): GetSeatingChartWithUsersResponse = {
    GetSeatingChartWithUsersResponse(
      "SUCCESS",
      SeatCount(seatingChartWithUsersOutput.allSeatCount.value),
      seatingChartWithUsersOutput.allSeatingChartWithUsers
    )
  }

  implicit val encoder: Encoder[GetSeatingChartWithUsersResponse] = (g: GetSeatingChartWithUsersResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatingChartCount", g.allSeatingChartCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[SeatingChartWithUsers] = (a: SeatingChartWithUsers) => {
    Json.obj(
      ("employeeInfo", a.employee.asJson),
      ("seatInfo", a.seat.asJson)
    )
  }

  private implicit val responseEmployeeEncoder: Encoder[Employee] = (a: Employee) => {
    Json.obj(
      ("employee_id", a.employeeId.value.asJson),
      ("employee_name_kanji", a.nameKanji.value.asJson),
      ("employee_name_kana", a.nameKana.value.asJson),
    )
  }

  private implicit val responseSeatEncoder: Encoder[Seat] = (a: Seat) => {
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