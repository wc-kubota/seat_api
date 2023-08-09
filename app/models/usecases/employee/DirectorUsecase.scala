package models.usecases.employee

import models.entities.employee.Director
import models.repositories.employee.DirectorRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class DirectorOutput(
   employeeCount: Count,
   director: Seq[Director]
)

/**
 * シートユースケース
 */
trait DirectorUsecase {
  def handle(): Future[DirectorOutput]
}

class DirectorUsecaseImpl @Inject() (
  directorRepository: DirectorRepository
)(implicit ec: ExecutionContext) extends DirectorUsecase {

  override def handle(): Future[DirectorOutput] = {
    for {
      director <- directorRepository.fetchDirector()
      employeeCount <- directorRepository.employeeCount()
    } yield {
      DirectorOutput(
        employeeCount,
        director
      )
    }
  }
}
