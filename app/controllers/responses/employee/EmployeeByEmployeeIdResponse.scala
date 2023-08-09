package controllers.responses.employee

import io.circe.syntax._
import io.circe.{Encoder, Json}
import models.entities.employee.{Employee, EmployeeCount}
import models.usecases.employee.{EmployeeByEmployeeIdOutput, EmployeeOutput}

final case class GetEmployeeByEmployeeIdResponse(
  message: String,
  employeeCount: EmployeeCount,
  response: Seq[Employee]
)

object GetEmployeeByEmployeeIdResponse {

  def ok(employeeByEmployeeIdOutput: EmployeeByEmployeeIdOutput): GetEmployeeByEmployeeIdResponse = {
    GetEmployeeByEmployeeIdResponse(
      "SUCCESS",
      EmployeeCount(employeeByEmployeeIdOutput.employeeCount.value),
      employeeByEmployeeIdOutput.employee
    )
  }

  implicit val encoder: Encoder[GetEmployeeByEmployeeIdResponse] = (g: GetEmployeeByEmployeeIdResponse) => {
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
