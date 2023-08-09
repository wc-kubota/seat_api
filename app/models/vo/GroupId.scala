package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 組織の事業本部
 * ex) 経営管理本部
 *
 * @param value
 */

case class GroupId(value: Int) extends AnyVal

object GroupId {
  def apply(value: Option[Int]): GroupId = {
    GroupId(value)
  }
}

trait GroupIdJsonSupport {
  implicit val groupIdEncoder: Encoder[GroupId] = Encoder[Int].contramap(_.value)
  implicit val groupIdDecoder: Decoder[GroupId] = Decoder[Int].map(GroupId.apply)
}