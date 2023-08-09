package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 本部
 *
 * @param value
 */

case class TeamName(value: String) extends AnyVal

object TeamName {
  def apply(value: Option[String]): TeamName = {
    TeamName(value)
  }
}

trait TeamNameJsonSupport {
  implicit val TeamNameEncoder: Encoder[TeamName] = Encoder[String].contramap(_.value)
  implicit val TeamNameDecoder: Decoder[TeamName] = Decoder[String].map(TeamName.apply)
}