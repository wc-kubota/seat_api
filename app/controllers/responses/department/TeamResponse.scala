package controllers.responses.department

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.department.{DepartmentCount, Team}
import models.usecases.department.TeamOutput

final case class GetTeamResponse(
  message: String,
  departmentCount: DepartmentCount,
  response: Seq[Team]
)

object GetTeamResponse {

  def ok(teamOutput: TeamOutput): GetTeamResponse = {
    GetTeamResponse(
      "SUCCESS",
      DepartmentCount(teamOutput.departmentCount.value),
      teamOutput.team
    )
  }

  implicit val encoder: Encoder[GetTeamResponse] = (g: GetTeamResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.departmentCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Team] = (a: Team) => {
    Json.obj(
      ("team_id", a.teamId.value.asJson),
      ("team_name", a.teamName.value.asJson),
    )
  }
}
