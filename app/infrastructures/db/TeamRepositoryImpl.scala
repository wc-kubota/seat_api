package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MTeam
import models.entities.department.Team
import models.repositories.department.TeamRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class TeamRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with TeamRepository {

  override def departmentCount(): Future[Count] = {
    val query = MTeam
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchTeam(): Future[Seq[Team]] = {
    val query = MTeam
      .map(r => (
        r.teamId,
        r.teamName
      ))
      .result
      .map(_.map(r =>
        Team(
          TeamId(r._1),
          TeamName(r._2)
        )
      ))

    db.run(query)
  }
}
