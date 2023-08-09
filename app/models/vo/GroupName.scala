package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 本部
 *
 * @param value
 */

case class GroupName(value: String) extends AnyVal

object GroupName {
  def apply(value: Option[String]): GroupName = {
    GroupName(value)
  }
}

trait GroupNameJsonSupport {
  implicit val GroupNameEncoder: Encoder[GroupName] = Encoder[String].contramap(_.value)
  implicit val GroupNameDecoder: Decoder[GroupName] = Decoder[String].map(GroupName.apply)
}