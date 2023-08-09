package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MGroup
import models.entities.department.Group
import models.repositories.department.GroupRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class GroupRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with GroupRepository {

  override def departmentCount(): Future[Count] = {
    val query = MGroup
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchGroup(): Future[Seq[Group]] = {
    val query = MGroup
      .map(r => (
        r.groupId,
        r.groupName
      ))
      .result
      .map(_.map(r =>
        Group(
          GroupId(r._1),
          GroupName(r._2)
        )
      ))

    db.run(query)
  }
}
