package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 組織の事業本部
 * ex) 経営管理本部
 *
 * @param value
 */

case class DivId(value: Int) extends AnyVal

object DivId {
  def apply(value: Option[Int]): DivId = {
    DivId(value)
  }
}

trait DivIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[DivId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[DivId] = Decoder[Int].map(DivId.apply)

}