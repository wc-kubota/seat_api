package models.usecases.seatingChart

import models.entities.seatingChart.SeatingChartWithUsers
import models.repositories.seatingChart.SeatingChartWithUsersRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class SeatingChartWithUsersOutput(
  allSeatCount: Count,
  allSeatingChartWithUsers: Seq[SeatingChartWithUsers]
)

/**
 * シートユースケース
 */
trait SeatingChartWithUsersUsecase {
  def handle(): Future[SeatingChartWithUsersOutput]
}

class SeatingChartWithUsersUsecaseImpl @Inject() (
  seatingChartWithUsersRepository: SeatingChartWithUsersRepository
)(implicit ec: ExecutionContext) extends SeatingChartWithUsersUsecase {

  override def handle(): Future[SeatingChartWithUsersOutput] = {
    for {
      seatingChartWithUsers <- seatingChartWithUsersRepository.fetchSeatingChartWithUsers()
      allCount <- seatingChartWithUsersRepository.seatCount()
    } yield {
      SeatingChartWithUsersOutput(
        allCount,
        seatingChartWithUsers
      )
    }
  }
}
