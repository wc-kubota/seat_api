package controllers.responses

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

/**
 * エラー発生時のレスポンスパラメータ.
 *
 * @param message
 */
final case class ErrorResponse(message: String)

object ErrorResponse {
  implicit val encoder: Encoder[ErrorResponse] = deriveEncoder
}