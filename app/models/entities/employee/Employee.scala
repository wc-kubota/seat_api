package models.entities.employee

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto._
import io.circe.{Decoder, Encoder}
import models.vo._

/**
 * 従業員の情報
 *
 * @param employeeId
 * @param String
 */

case class Employee(
  employeeId: EmployeeId,
  nameKanji: NameKanji,
  nameKana: NameKana,
)


object Employee
 extends EmployeeIdJsonSupport
   with NameKanjiJsonSupport
   with NameKanaJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Employee] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Employee] = deriveConfiguredDecoder
}

