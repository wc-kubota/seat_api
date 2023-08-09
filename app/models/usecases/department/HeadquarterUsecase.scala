package models.usecases.department

import models.entities.department.Headquarter
import models.repositories.department.HeadquarterRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class HeadquarterOutput(
   departmentCount: Count,
   headquarter: Seq[Headquarter]
                                  )

/**
 * シートユースケース
 */
trait HeadquarterUsecase {
  def handle(): Future[HeadquarterOutput]
}

class HeadquarterUsecaseImpl @Inject() (
  headquarterRepository: HeadquarterRepository
)(implicit ec: ExecutionContext) extends HeadquarterUsecase {

  override def handle(): Future[HeadquarterOutput] = {
    for {
      headquarter <- headquarterRepository.fetchHeadquarter()
      departmentCount <- headquarterRepository.departmentCount()
    } yield {
      HeadquarterOutput(
        departmentCount,
        headquarter
      )
    }
  }
}
