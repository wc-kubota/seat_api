package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 漢字名前
 *
 * @param value
 */

case class NameKanji(value: String)

object NameKanji {
  def apply(value: Option[String]): NameKanji = {
    value match {
      case Some(value) => NameKanji(value)
      case None =>
        throw new RuntimeException("名前漢字の取得に失敗")
    }
  }
}


/**
 * Circe Json変換用設定
 */

trait NameKanjiJsonSupport {
  implicit val nameKanjiEncoder: Encoder[NameKanji] = Encoder[String].contramap(_.value)
  implicit val nameKanjiDecoder: Decoder[NameKanji] = Decoder[String].map(NameKanji.apply)
}

