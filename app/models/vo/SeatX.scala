package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 座席のx軸ID
 * @param value
 */

case class SeatX(value: Int)

object SeatX {
  def apply(value: Option[Int]): SeatX = {
    value match {
      case Some(value) => SeatX(value)
      case None =>
        throw new RuntimeException("SeatXの作成に失敗")
    }
  }
}
