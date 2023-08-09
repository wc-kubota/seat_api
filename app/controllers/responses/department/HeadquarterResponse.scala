package controllers.responses.department

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.department.{DepartmentCount, Headquarter}
import models.entities.department.Headquarter
import models.usecases.department.HeadquarterOutput

final case class GetHeadquarterResponse(
  message: String,
  departmentCount: DepartmentCount,
  response: Seq[Headquarter]
)

object GetHeadquarterResponse {

  def ok(headquarterOutput: HeadquarterOutput): GetHeadquarterResponse = {
    GetHeadquarterResponse(
      "SUCCESS",
      DepartmentCount(headquarterOutput.departmentCount.value),
      headquarterOutput.headquarter
    )
  }

  implicit val encoder: Encoder[GetHeadquarterResponse] = (g: GetHeadquarterResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.departmentCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Headquarter] = (a: Headquarter) => {
    Json.obj(
      ("headquarter_id", a.headquarterId.value.asJson),
      ("headquarter_name", a.headquarterName.value.asJson),
    )
  }
}
