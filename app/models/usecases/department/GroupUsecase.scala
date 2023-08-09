package models.usecases.department

import models.entities.department.Group
import models.repositories.department.GroupRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class GroupOutput(
   departmentCount: Count,
   group: Seq[Group]
                                  )

/**
 * シートユースケース
 */
trait GroupUsecase {
  def handle(): Future[GroupOutput]
}

class GroupUsecaseImpl @Inject() (
  groupRepository: GroupRepository
)(implicit ec: ExecutionContext) extends GroupUsecase {

  override def handle(): Future[GroupOutput] = {
    for {
      group <- groupRepository.fetchGroup()
      departmentCount <- groupRepository.departmentCount()
    } yield {
      GroupOutput(
        departmentCount,
        group
      )
    }
  }
}
