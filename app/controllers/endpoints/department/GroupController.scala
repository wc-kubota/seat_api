package controllers.endpoints.department

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.department.GetGroupResponse
import io.circe.syntax.EncoderOps
import models.usecases.department.{GroupOutput, GroupUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class GroupController @Inject()(
 groupUsecase: GroupUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def fetchGroup() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        groupUsecase.handle().map(r => (r.departmentCount) match {
          case Count(0) => Left("NOT_FOUND_DIv")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetGroupResponse.ok(r).asJson)
      case Left("NOT_FOUND_Group") => Ok(GetGroupResponse.ok(GroupOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}