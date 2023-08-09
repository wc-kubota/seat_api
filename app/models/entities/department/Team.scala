package models.entities.department

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param teamId
 * @param String
 */

case class Team(
  teamId: TeamId,
  teamName: TeamName
 )

object Team
  extends TeamIdJsonSupport
    with TeamNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Team] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Team] = deriveConfiguredDecoder
}

