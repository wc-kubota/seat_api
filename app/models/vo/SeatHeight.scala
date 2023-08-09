package models.vo

/**
 * 座席のx軸ID
 * @param value
 */

case class SeatHeight(value: Int)

object SeatHeight {
  def apply(value: Option[Int]): SeatHeight = {
    value match {
      case Some(value) => SeatHeight(value)
      case None =>
        throw new RuntimeException("SeatHeightの作成に失敗")
    }
  }
}
