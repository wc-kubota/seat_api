package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 組織の事業本部
 * ex) 経営管理本部
 *
 * @param value
 */

case class DirectorId(value: Int) extends AnyVal

object DirectorId {
  def apply(value: Option[Int]): DirectorId = {
    DirectorId(value)
  }
}

trait DirectorIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[DirectorId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[DirectorId] = Decoder[Int].map(DirectorId.apply)

}