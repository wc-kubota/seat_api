package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 組織の事業本部
 * ex) 経営管理本部
 *
 * @param value
 */

case class TeamId(value: Int) extends AnyVal

object TeamId {
  def apply(value: Option[Int]): TeamId = {
    TeamId(value)
  }
}

trait TeamIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[TeamId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[TeamId] = Decoder[Int].map(TeamId.apply)
}