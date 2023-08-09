package controllers.responses.employee

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}
import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
import io.circe.syntax._
import io.circe.{Decoder, Encoder, Json}
import models.entities.employee.{Employee, EmployeeCount}
import models.usecases.employee.EmployeeOutput

sealed trait UpdateEmployeeResponse

object UpdateEmployeeResponse {

  implicit val config = Configuration.default.withSnakeCaseMemberNames

  @ConfiguredJsonCodec
  case class FailedUpdateEmployeeResponse(
                                            message: String = "FAILED",
                                            reason: String = "FAILED_UPDATE"
                                          ) extends UpdateEmployeeResponse

  case class UpdatedEmployeeResponse(
                                       message: String = "SUCCESS",
                                     ) extends UpdateEmployeeResponse

  object UpdatedEmployeeResponse {
    implicit val config = Configuration.default.withSnakeCaseMemberNames
    implicit val decoder: Decoder[UpdatedEmployeeResponse] = deriveConfiguredDecoder
    implicit val encoder: Encoder[UpdatedEmployeeResponse] = deriveConfiguredEncoder
  }
}