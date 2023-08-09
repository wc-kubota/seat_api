package models.entities.department

import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import io.circe.{Decoder, Encoder}

/**
 *
 * @param allSeatCount
 */
case class DepartmentCount(
  departmentCount: Int
)

object DepartmentCount {
  implicit val config = Configuration.default.withSnakeCaseMemberNames
  implicit val decoder: Decoder[DepartmentCount] = deriveConfiguredDecoder
  implicit val encoder: Encoder[DepartmentCount] = deriveConfiguredEncoder
}
