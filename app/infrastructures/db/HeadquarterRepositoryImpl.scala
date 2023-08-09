package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MHeadquarter
import models.entities.department.Headquarter
import models.repositories.department.HeadquarterRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class HeadquarterRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with HeadquarterRepository {

  override def departmentCount(): Future[Count] = {
    val query = MHeadquarter
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchHeadquarter(): Future[Seq[Headquarter]] = {
    val query = MHeadquarter
      .map(r => (
        r.headquarterId,
        r.headquarterName
      ))
      .result
      .map(_.map(r =>
        Headquarter(
          HeadquarterId(r._1),
          HeadquarterName(r._2)
        )
      ))

    db.run(query)
  }
}
