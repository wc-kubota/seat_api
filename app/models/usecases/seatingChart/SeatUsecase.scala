package models.usecases.seatingChart

import models.entities.seatingChart.Seat
import models.repositories.seatingChart.SeatRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class SeatOutput(
  allSeatCount: Count,
  allSeats: Seq[Seat]
)

/**
 * シートユースケース
 */
trait SeatUsecase {
  def handle(): Future[SeatOutput]
}

class SeatUsecaseImpl @Inject() (
  seatRepository: SeatRepository
)(implicit ec: ExecutionContext) extends SeatUsecase {

  override def handle(): Future[SeatOutput] = {
    for {
      seat <- seatRepository.fetchAllSeat()
      allCount <- seatRepository.seatCount()
    } yield {
      SeatOutput(
        allCount,
        seat
      )
    }
  }
}
