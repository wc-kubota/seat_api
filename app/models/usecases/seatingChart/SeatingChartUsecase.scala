package models.usecases.seatingChart

import models.entities.seatingChart.SeatingChart
import models.repositories.seatingChart.SeatingChartRepository
import models.vo.Count

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


/**
 * ユースケース戻り値用オブジェクト
 */
final case class SeatingChartOutput(
  allSeatCount: Count,
  allSeatingChart: Seq[SeatingChart]
)

/**
 * シートユースケース
 */
trait SeatingChartUsecase {
  def handle(): Future[SeatingChartOutput]
}

class SeatingChartUsecaseImpl @Inject() (
  seatingChartRepository: SeatingChartRepository
)(implicit ec: ExecutionContext) extends SeatingChartUsecase {

  override def handle(): Future[SeatingChartOutput] = {
    for {
      seatingChart <- seatingChartRepository.fetchSeatingChart()
      allCount <- seatingChartRepository.seatCount()
    } yield {
      SeatingChartOutput(
        allCount,
        seatingChart
      )
    }
  }
}
