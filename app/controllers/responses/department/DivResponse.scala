package controllers.responses.department

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.department.{DepartmentCount, Div}
import models.usecases.department.DivOutput

final case class GetDivResponse(
  message: String,
  departmentCount: DepartmentCount,
  response: Seq[Div]
)

object GetDivResponse {

  def ok(divOutput: DivOutput): GetDivResponse = {
    GetDivResponse(
      "SUCCESS",
      DepartmentCount(divOutput.departmentCount.value),
      divOutput.div
    )
  }

  implicit val encoder: Encoder[GetDivResponse] = (g: GetDivResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.departmentCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Div] = (a: Div) => {
    Json.obj(
      ("div_id", a.divId.value.asJson),
      ("div_name", a.divName.value.asJson),
    )
  }
}
