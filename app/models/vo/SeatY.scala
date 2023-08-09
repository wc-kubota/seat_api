package models.vo

/**
 * 座席のY軸ID
 * @param value
 */

case class SeatY(value: Int)

object SeatY {
  def apply(value: Option[Int]): SeatY = {
    value match {
      case Some(value) => SeatY(value)
      case None =>
        throw new RuntimeException("SeatYの作成に失敗")
    }
  }
}
