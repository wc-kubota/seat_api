package controllers.responses.employee

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.employee.{Employee, EmployeeCount}
import models.usecases.employee.EmployeeOutput

final case class GetEmployeeResponse(
  message: String,
  employeeCount: EmployeeCount,
  response: Seq[Employee]
)

object GetEmployeeResponse {

  def ok(employeeOutput: EmployeeOutput): GetEmployeeResponse = {
    GetEmployeeResponse(
      "SUCCESS",
      EmployeeCount(employeeOutput.employeeCount.value),
      employeeOutput.employee
    )
  }

  implicit val encoder: Encoder[GetEmployeeResponse] = (g: GetEmployeeResponse) => {
    Json.obj(
      ("message", g.message.asJson),
      ("allSeatCount", g.employeeCount.asJson),
      ("response", g.response.asJson)
    )
  }

  private implicit val responseEncoder: Encoder[Employee] = (a: Employee) => {
    Json.obj(
      ("employee_id", a.employeeId.value.asJson),
      ("employee_name_kanji", a.nameKanji.value.asJson),
      ("employee_name_kana", a.nameKana.value.asJson),
    )
  }
}
