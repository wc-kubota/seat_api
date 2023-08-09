package models.entities.department

import io.circe.generic.extras.Configuration
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param headqurterId
 * @param String
 */

case class Headquarter(
  headquarterId: HeadquarterId,
  headquarterName: HeadquarterName
 )

object Headquarter
  extends HeadquarterIdJsonSupport
    with HeadquarterNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Headquarter] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Headquarter] = deriveConfiguredDecoder
}