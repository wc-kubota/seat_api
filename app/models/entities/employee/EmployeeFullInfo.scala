package models.entities.employee

import models.entities.department.{Div, Group, Headquarter, Team}
import io.circe.{Decoder, Encoder}
import models.vo._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._


/**
 * 従業員の情報
 *
 * @param Employee
 * @param SexId
 * @param Headquarter
 * @param Div
 * @param Group
 * @param Team
 * @param Director
 * @param Occupation
 */

case class EmployeeFullInfo(
  employee: Employee,
  sex: Sex,
  headquarter: Headquarter,
  div: Div,
  group: Group,
  team: Team,
  director: Director,
  occupation: Occupation
)


object EmployeeFullInfo
  extends EmployeeIdJsonSupport
{
  implicit val config = Configuration.default.withSnakeCaseMemberNames
  implicit val decoder: Decoder[EmployeeFullInfo] = deriveConfiguredDecoder
  implicit val encoder: Encoder[EmployeeFullInfo] = deriveConfiguredEncoder
}
