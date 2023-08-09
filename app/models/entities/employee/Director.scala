package models.entities.employee

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param directorId
 * @param String
 */

case class Director(
  directorId: DirectorId,
  directorName: DirectorName
 )

object Director
  extends DirectorIdJsonSupport
    with DirectorNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Director] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Director] = deriveConfiguredDecoder
}