//package controllers.responses.employee
//
//import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}
//import io.circe.generic.extras.semiauto.{deriveConfiguredDecoder, deriveConfiguredEncoder}
//import io.circe.{Decoder, Encoder}
//
//
//sealed trait UpdateEmployeeFullInfoResponse
//
//object UpdateEmployeeFullInfoResponse {
//
//  implicit val config = Configuration.default.withSnakeCaseMemberNames
//
//  @ConfiguredJsonCodec
//  case class FailedUpdateEmployeeFullInfoResponse(
//                                           message: String = "FAILED",
//                                           reason: String = "FAILED_UPDATE"
//                                         ) extends UpdateEmployeeFullInfoResponse
//
//  case class UpdatedEmployeeResponse(
//                                      message: String = "SUCCESS",
//                                    ) extends UpdateEmployeeFullInfoResponse
//
//  object UpdatedEmployeeResponse {
//    implicit val config = Configuration.default.withSnakeCaseMemberNames
//    implicit val decoder: Decoder[UpdatedEmployeeResponse] = deriveConfiguredDecoder
//    implicit val encoder: Encoder[UpdatedEmployeeResponse] = deriveConfiguredEncoder
//  }
//}