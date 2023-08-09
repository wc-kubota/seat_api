package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 性別ID
 *
 * @param value
 */

case class SexName(value: String) {
  require(value == "男性" || value == "女性", "Invalid sex Name. Only 男性 or 女性 allowed")
}

object SexName {
  def apply(value: Option[String]): SexName = {
    value match {
      case Some(value) => SexName(value)
      case None =>
        throw new RuntimeException("SexNameの取得に失敗")
    }
  }
}

/**
 * Circe Json変換用設定
 */

trait SexNameJsonSupport {
  implicit val nameKanjiEncoder: Encoder[SexName] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[SexName] = Decoder[String].map(SexName.apply)
}