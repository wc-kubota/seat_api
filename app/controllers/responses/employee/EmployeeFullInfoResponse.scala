package controllers.responses.employee

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.employee.{EmployeeFullInfo, EmployeeCount}
import models.usecases.employee.EmployeeFullInfoOutput

final case class GetEmployeeFullInfoResponse(
  message: String,
  employeeCount: EmployeeCount,
  response: Seq[EmployeeFullInfo]
)

object GetEmployeeFullInfoResponse {

  def ok(employeeFullInfoOutput: EmployeeFullInfoOutput): GetEmployeeFullInfoResponse = {
    GetEmployeeFullInfoResponse(
      "SUCCESS",
      EmployeeCount(employeeFullInfoOutput.employeeCount.value),
      employeeFullInfoOutput.employeeFullInfo
    )
  }

  implicit val encoder: Encoder[GetEmployeeFullInfoResponse] = (g: GetEmployeeFullInfoResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.employeeCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[EmployeeFullInfo] = (a: EmployeeFullInfo) => {
    Json.obj(
      ("employee_id", a.employee.employeeId.value.asJson),
      ("employee_name_kanji", a.employee.nameKanji.value.asJson),
      ("employee_name_kana", a.employee.nameKana.value.asJson),
      ("sex_id",a.sex.sexId.value.asJson),
      ("sex_name",a.sex.sexName.value.asJson),
      ("headquarter_id", a.headquarter.headquarterId.value.asJson),
      ("headquarter_name", a.headquarter.headquarterName.value.asJson),
      ("div_id", a.div.divId.value.asJson),
      ("div_name", a.div.divName.value.asJson),
      ("group_id", a.group.groupId.value.asJson),
      ("group_name", a.group.groupName.value.asJson),
      ("team_id", a.team.teamId.value.asJson),
      ("team_name", a.team.teamName.value.asJson),
      ("director_id", a.director.directorId.value.asJson),
      ("director_name", a.director.directorName.value.asJson),
      ("occupation_id", a.occupation.occupationId.value.asJson),
      ("occupation_name", a.occupation.occupationName.value.asJson)
    )
  }
}
