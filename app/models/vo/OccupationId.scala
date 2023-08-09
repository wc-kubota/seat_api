package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 役職
 * ex) マネージャー
 *
 * @param value
 */

case class OccupationId(value: Int) extends AnyVal

object OccupationId {
  def apply(value: Option[Int]): OccupationId = {
    OccupationId(value)
  }
}

trait OccupationIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[OccupationId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[OccupationId] = Decoder[Int].map(OccupationId.apply)

}