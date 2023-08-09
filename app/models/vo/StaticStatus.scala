package models.vo

/**
 * 座席の静的ステータス(固定座席かどうか)
 * @param value
 */

case class StaticStatus(value: Int)

object StaticStatus {
  def apply(value: Option[Int]): StaticStatus = {
    value match {
      case Some(value) => StaticStatus(value)
      case None =>
        throw new RuntimeException("StaticStatusの作成に失敗")
    }
  }
}
