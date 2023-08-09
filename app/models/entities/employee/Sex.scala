package models.entities.employee

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param sexId
 * @param String
 */

case class Sex(
  sexId: SexId,
  sexName: SexName
 )

object Sex
  extends SexIdJsonSupport
    with SexNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Sex] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Sex] = deriveConfiguredDecoder
}
