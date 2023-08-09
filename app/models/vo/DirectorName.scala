package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 本部
 *
 * @param value
 */

case class DirectorName(value: String) extends AnyVal

object DirectorName {
  def apply(value: Option[String]): DirectorName = {
    DirectorName(value)
  }
}

/**
 * Circe Json変換用設定
*/

trait DirectorNameJsonSupport {
  implicit val nameKanjiEncoder: Encoder[DirectorName] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[DirectorName] = Decoder[String].map(DirectorName.apply)
}