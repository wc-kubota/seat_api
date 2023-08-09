package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 従業員の情報
 *
 * @param value
 */

case class EmployeeId(value: Int)

object EmployeeId {
  def apply(value: Option[Int]): EmployeeId = {
    value match {
      case Some(value) => EmployeeId(value)
      case None =>
        throw new RuntimeException("EmployeeIdの取得に失敗")
    }
  }
}

/**
 * Circe Json変換用設定
 */

trait EmployeeIdJsonSupport {
  implicit val employeeIdEncoder: Encoder[EmployeeId] = Encoder[Int].contramap(_.value)
  implicit val employeeIdDecoder: Decoder[EmployeeId] = Decoder[Int].map(EmployeeId.apply)
}
