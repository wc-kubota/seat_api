package models.entities.employee

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import io.circe.{Decoder, Encoder}

/**
 *
 * @param employeeCount
 */
case class EmployeeCount(
  employeeCount: Int
)

object EmployeeCount {
  implicit val config = Configuration.default.withSnakeCaseMemberNames
  implicit val decoder: Decoder[EmployeeCount] = deriveConfiguredDecoder
  implicit val encoder: Encoder[EmployeeCount] = deriveConfiguredEncoder
}
