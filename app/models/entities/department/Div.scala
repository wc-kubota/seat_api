package models.entities.department

import io.circe.generic.extras.Configuration
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param divId
 * @param String
 */

case class Div(
  divId: DivId,
  divName: DivName
 )

object Div
  extends DivIdJsonSupport
    with DivNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Div] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Div] = deriveConfiguredDecoder
}