package models.vo

/**
 * 座席の静的ステータス(固定座席かどうか)
 * @param value
 */

case class IsFixed(value: Boolean)

object IsFixed {
  def apply(value: Option[Boolean]): IsFixed = {
    value match {
      case Some(value) => IsFixed(value)
      case None =>
        throw new RuntimeException("StaticStatusの作成に失敗")
    }
  }
}
