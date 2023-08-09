package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 本部
 *
 * @param value
 */

case class HeadquarterName(value: String) extends AnyVal

object HeadquarterName {
  def apply(value: Option[String]): HeadquarterName = {
    HeadquarterName(value)
  }
}

/**
 * Circe Json変換用設定
 */

trait HeadquarterNameJsonSupport {
  implicit val nameKanjiEncoder: Encoder[HeadquarterName] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[HeadquarterName] = Decoder[String].map(HeadquarterName.apply)
}