package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.MDiv
import models.entities.department.Div
import models.repositories.department.DivRepository
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class DivRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with DivRepository {

  override def departmentCount(): Future[Count] = {
    val query = MDiv
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchDiv(): Future[Seq[Div]] = {
    val query = MDiv
      .map(r => (
        r.divId,
        r.divName
      ))
      .result
      .map(_.map(r =>
        Div(
          DivId(r._1),
          DivName(r._2)
        )
      ))

    db.run(query)
  }
}
