package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 性別ID
 *
 * @param value
 */

case class SexId(value: Int) {
  require(value == 1 || value == 2, "Invalid sex Id. Only 1 or 2 allowed")
}

object SexId {
  def apply(value: Option[Int]): SexId = {
    value match {
      case Some(value) => SexId(value)
      case None =>
        throw new RuntimeException("SexIdの取得に失敗")
    }
  }
}

trait SexIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[SexId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[SexId] = Decoder[Int].map(SexId.apply)

}
