package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 組織の事業本部
 * ex) 経営管理本部
 *
 * @param value
 */

case class HeadquarterId(value: Int) extends AnyVal

object HeadquarterId {
  def apply(value: Option[Int]): HeadquarterId = {
    value match {
      case Some(value) => HeadquarterId(value)
      case None => throw new RuntimeException("HeadquarterIdの取得に失敗")
    }
  }
}

trait HeadquarterIdJsonSupport {
  implicit val headquarterIdEncoder: Encoder[HeadquarterId] = Encoder[Int].contramap(_.value)
  implicit val headquarterIdDecoder: Decoder[HeadquarterId] = Decoder[Int].map(HeadquarterId.apply)
}