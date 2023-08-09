package models.entities.department

import io.circe.generic.extras.Configuration
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import models.vo._

/**
 * 座席の情報
 *
 * @param groupId
 * @param String
 */

case class Group(
  groupId: GroupId,
  groupName: GroupName
 )

object Group
  extends GroupIdJsonSupport
    with GroupNameJsonSupport
{
 implicit val config = Configuration.default.withSnakeCaseMemberNames
 implicit val encoder: Encoder[Group] = deriveConfiguredEncoder
 implicit val decoder: Decoder[Group] = deriveConfiguredDecoder
}