package controllers.responses.employee

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.employee.{EmployeeCount, Director}
import models.usecases.employee.DirectorOutput

final case class GetDirectorResponse(
  message: String,
  employeeCount: EmployeeCount,
  response: Seq[Director]
)

object GetDirectorResponse {

  def ok(directorOutput: DirectorOutput): GetDirectorResponse = {
    GetDirectorResponse(
      "SUCCESS",
      EmployeeCount(directorOutput.employeeCount.value),
      directorOutput.director
    )
  }

  implicit val encoder: Encoder[GetDirectorResponse] = (g: GetDirectorResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.employeeCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Director] = (a: Director) => {
    Json.obj(
      ("director_id", a.directorId.value.asJson),
      ("director_name", a.directorName.value.asJson),
    )
  }
}
