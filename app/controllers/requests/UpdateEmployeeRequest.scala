package controllers.requests

import io.circe.{Decoder,Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import models.entities.employee.Employee
import models.usecases.employee.UpdateEmployeeInput

/**
 * 従業員情報更新時のリクエストパラメータ.
 * @param employee
 */
case class UpdateEmployeeRequest(
  employee: Employee
) {
  def toUpdateEmployeeInput(): UpdateEmployeeInput = {
    UpdateEmployeeInput(
      employee
    )
  }
}


object UpdateEmployeeRequest {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
  implicit val encoder: Encoder[UpdateEmployeeRequest] = deriveConfiguredEncoder
  implicit val decoder: Decoder[UpdateEmployeeRequest] = deriveConfiguredDecoder

}
