//package controllers.requests
//
//import io.circe.generic.extras.Configuration
//import io.circe.generic.extras.semiauto._
//import io.circe.{Decoder, Encoder}
//import models.entities.employee.{Employee, EmployeeFullInfo}
//import models.usecases.employee.UpdateEmployeeFullInfoInput
//import models.vo._
//
///**
// * 従業員情報更新時のリクエストパラメータ.
// * @param employee
// */
//case class UpdateEmployeeFullInfoRequest(
//  employeeFullInfo: EmployeeFullInfo
//) {
//  def toUpdateEmployeeFullInfoInput(): UpdateEmployeeFullInfoInput = {
//    UpdateEmployeeFullInfoInput(
//      employeeFullInfo
//    )
//  }
//}
//
//
//object UpdateEmployeeFullInfoRequest {
//  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
//  implicit val encoder: Encoder[UpdateEmployeeFullInfoRequest] = deriveConfiguredEncoder
//  implicit val decoder: Decoder[UpdateEmployeeFullInfoRequest] = deriveConfiguredDecoder
//
//
//
//}
