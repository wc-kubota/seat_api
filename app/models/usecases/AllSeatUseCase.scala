package models.usecases

import models.entities.AllSeat
import models.repositories.AllSeatRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class AllSeatOutput(
  allSeatCount: Count,
  allSeats: Seq[AllSeat]
)

/**
 * シートユースケース
 */
trait AllSeatUsecase {
  def handle(): Future[AllSeatOutput]
}

class AllSeatUsecaseImpl @Inject() (
  allSeatRepository: AllSeatRepository
)(implicit ec: ExecutionContext) extends AllSeatUsecase {

  override def handle(): Future[AllSeatOutput] = {
    for {
      allSeat <- allSeatRepository.fetchAllSeat()
      allCount <- allSeatRepository.allSeatCount()
    } yield {
      AllSeatOutput(
        allCount,
        allSeat
      )
    }
  }
}
