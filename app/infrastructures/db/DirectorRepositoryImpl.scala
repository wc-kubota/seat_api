package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MDirector
import models.entities.employee.Director
import models.repositories.employee.DirectorRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class DirectorRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DirectorRepository {

  override def employeeCount(): Future[Count] = {
    val query = MDirector
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchDirector(): Future[Seq[Director]] = {
    val query = MDirector
      .map(r => (
        r.directorId,
        r.directorName
      ))
      .result
      .map(_.map(r =>
        Director(
          DirectorId(r._1),
          DirectorName(r._2)
        )
      ))

    db.run(query)
  }
}
