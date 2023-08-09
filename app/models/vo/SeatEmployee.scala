package models.vo

/**
 * 座席に座っている人
 * @param value
 */

case class SeatEmployee(value: Int)

object SeatEmployee {
  def apply(value: Option[Int]): SeatEmployee = {
    value match {
      case Some(value) => SeatEmployee(value)
      case None =>
        throw new RuntimeException("SeatEmployeeの作成に失敗")
    }
  }
}
