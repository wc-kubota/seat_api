package models.usecases.department

import models.entities.department.Team
import models.repositories.department.TeamRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class TeamOutput(
   departmentCount: Count,
   team: Seq[Team]
                                  )

/**
 * シートユースケース
 */
trait TeamUsecase {
  def handle(): Future[TeamOutput]
}

class TeamUsecaseImpl @Inject() (
  teamRepository: TeamRepository
)(implicit ec: ExecutionContext) extends TeamUsecase {

  override def handle(): Future[TeamOutput] = {
    for {
      team <- teamRepository.fetchTeam()
      departmentCount <- teamRepository.departmentCount()
    } yield {
      TeamOutput(
        departmentCount,
        team
      )
    }
  }
}
