package models.entities

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}

/**
 *
 * @param allSeatCount
 */
case class SeatCount(
  allSeatCount: Int
)

object SeatCount {
  implicit val config = Configuration.default.withSnakeCaseMemberNames
  implicit val decoder: Decoder[SeatCount] = deriveConfiguredDecoder
  implicit val encoder: Encoder[SeatCount] = deriveConfiguredEncoder
}
