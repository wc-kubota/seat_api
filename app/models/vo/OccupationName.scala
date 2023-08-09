package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 役職
 *
 * @param value
 */

case class OccupationName(value: String) extends AnyVal

object OccupationName {
  def apply(value: Option[String]): OccupationName = {
    OccupationName(value)
  }
}

/**
 * Circe Json変換用設定
 */

trait OccupationNameJsonSupport {
  implicit val nameKanjiEncoder: Encoder[OccupationName] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[OccupationName] = Decoder[String].map(OccupationName.apply)
}