package models.vo

/**
 * 座席のx軸ID
 * @param value
 */

case class SeatWidth(value: Int)

object SeatWidth {
  def apply(value: Option[Int]): SeatWidth = {
    value match {
      case Some(value) => SeatWidth(value)
      case None =>
        throw new RuntimeException("SeatWidthの作成に失敗")
    }
  }
}
