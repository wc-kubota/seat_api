package controllers.endpoints.department

import cats.data.EitherT
import controllers.responses.ErrorResponse
import controllers.responses.department.GetTeamResponse
import io.circe.syntax.EncoderOps
import models.usecases.department.{TeamOutput, TeamUsecase}
import models.vo.Count
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._


class TeamController @Inject()(
 teamUsecase: TeamUsecase,
 cc:ControllerComponents
)( ec: ExecutionContext) extends AbstractController(cc) with Circe {

  /**
   * 全座席取得するエンドポイント
   * @return
   */
  def fetchTeam() = Action.async { implicit request =>
    (for {
      result <- EitherT {
        teamUsecase.handle().map(r => (r.departmentCount) match {
          case Count(0) => Left("NOT_FOUND_DIv")
          case _ => Right(r)
        })
      }
    } yield result).value.map {
      case Right(r) => Ok(GetTeamResponse.ok(r).asJson)
      case Left("NOT_FOUND_Team") => Ok(GetTeamResponse.ok(TeamOutput(Count(0), Seq())).asJson)
      case _ => InternalServerError(ErrorResponse("INTERNAL_SERVER_ERROR").asJson)
    }
  }
}