package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 本部
 *
 * @param value
 */

case class DivName(value: String) extends AnyVal

object DivName {
  def apply(value: Option[String]): DivName = {
    DivName(value)
  }
}

/**
 * Circe Json変換用設定
 */

trait DivNameJsonSupport {
  implicit val nameKanjiEncoder: Encoder[DivName] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[DivName] = Decoder[String].map(DivName.apply)
}