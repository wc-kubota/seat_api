package models.entities.employee

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param occupationId
 * @param String
 */

case class Occupation(
  occupationId: OccupationId,
  occupationName: OccupationName
 )

object Occupation
  extends OccupationIdJsonSupport
    with OccupationNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Occupation] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Occupation] = deriveConfiguredDecoder
}