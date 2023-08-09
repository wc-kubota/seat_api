package controllers.responses.department

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.department.{DepartmentCount, Group}
import models.usecases.department.GroupOutput

final case class GetGroupResponse(
  message: String,
  departmentCount: DepartmentCount,
  response: Seq[Group]
)

object GetGroupResponse {

  def ok(groupOutput: GroupOutput): GetGroupResponse = {
    GetGroupResponse(
      "SUCCESS",
      DepartmentCount(groupOutput.departmentCount.value),
      groupOutput.group
    )
  }

  implicit val encoder: Encoder[GetGroupResponse] = (g: GetGroupResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.departmentCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Group] = (a: Group) => {
    Json.obj(
      ("group_id", a.groupId.value.asJson),
      ("group_name", a.groupName.value.asJson),
    )
  }
}
