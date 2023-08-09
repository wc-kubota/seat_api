package models.vo

import io.circe.{Decoder, Encoder}

/**
 * 座席横幅
 * @param value
 */

case class SeatId(value: Int)

object SeatId {
  def apply(value: Option[Int]): SeatId = {
    value match {
      case Some(value) => SeatId(value)
      case None =>
        throw new RuntimeException("座席IDの作成に失敗")
    }
  }
}
