package models.vo

import io.circe.{Decoder, Encoder}

/**
 * かな名前
 *
 * @param value
 */

case class NameKana(value: String)

object NameKana {
  def apply(value: Option[String]): NameKana = {
    value match {
      case Some(value) => NameKana(value)
      case None =>
        throw new RuntimeException("名前かなの取得に失敗")
    }
  }
}

trait NameKanaJsonSupport {
  implicit val nameKanaEncoder: Encoder[NameKana] = Encoder[String].contramap(_.value)
  implicit val nameKanaDecoder: Decoder[NameKana] = Decoder[String].map(NameKana.apply)
}