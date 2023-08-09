package models.usecases.department

import models.entities.department.Div
import models.repositories.department.DivRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class DivOutput(
   departmentCount: Count,
   div: Seq[Div]
)

/**
 * シートユースケース
 */
trait DivUsecase {
  def handle(): Future[DivOutput]
}

class DivUsecaseImpl @Inject() (
  divRepository: DivRepository
)(implicit ec: ExecutionContext) extends DivUsecase {

  override def handle(): Future[DivOutput] = {
    for {
      div <- divRepository.fetchDiv()
      departmentCount <- divRepository.departmentCount()
    } yield {
      DivOutput(
        departmentCount,
        div
      )
    }
  }
}
