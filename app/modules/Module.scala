package modules

import com.google.inject.AbstractModule
//db
import infrastructures.db.{
  AllSeatRepositoryImpl,
  SeatRepositoryImpl,
  HeadquarterRepositoryImpl,
  DivRepositoryImpl,
  GroupRepositoryImpl,
  TeamRepositoryImpl,
  DirectorRepositoryImpl,
  EmployeeRepositoryImpl,
  EmployeeFullInfoRepositoryImpl,
  SeatingChartRepositoryImpl,
  SeatingChartWithUsersRepositoryImpl
}

//usecases
import models.usecases.{AllSeatUsecase, AllSeatUsecaseImpl}
import models.usecases.seatingChart.{
  SeatUsecase,         SeatUsecaseImpl,
  SeatingChartUsecase, SeatingChartUsecaseImpl,
  SeatingChartWithUsersUsecase, SeatingChartWithUsersUsecaseImpl
}

import models.usecases.department.{
  HeadquarterUsecase, HeadquarterUsecaseImpl,
  DivUsecase,         DivUsecaseImpl,
  GroupUsecase,       GroupUsecaseImpl,
  TeamUsecase,        TeamUsecaseImpl
}
import models.usecases.employee.{
  DirectorUsecase, DirectorUsecaseImpl,
  EmployeeUsecase, EmployeeUsecaseImpl,
  EmployeeByEmployeeIdUsecase, EmployeeByEmployeeIdUsecaseImpl,
  UpdateEmployeeUsecase, UpdateEmployeeUsecaseImpl,
  EmployeeFullInfoUsecase, EmployeeFullInfoUsecaseImpl,
}

//repositories
import models.repositories.seatingChart.{SeatRepository, SeatingChartRepository, SeatingChartWithUsersRepository}
import models.repositories.AllSeatRepository
import models.repositories.department.{HeadquarterRepository, DivRepository, GroupRepository, TeamRepository}
import models.repositories.employee.{DirectorRepository, EmployeeRepository, EmployeeFullInfoRepository}

/**
 * 抽象化クラスと実装クラスのバインド設定.
 */
class Module extends AbstractModule {
  override def configure(): Unit = {
    //Usecase
    bind(classOf[AllSeatUsecase]).to(classOf[AllSeatUsecaseImpl])
    bind(classOf[SeatUsecase]).to(classOf[SeatUsecaseImpl])
    bind(classOf[HeadquarterUsecase]).to(classOf[HeadquarterUsecaseImpl])
    bind(classOf[DivUsecase]).to(classOf[DivUsecaseImpl])
    bind(classOf[GroupUsecase]).to(classOf[GroupUsecaseImpl])
    bind(classOf[TeamUsecase]).to(classOf[TeamUsecaseImpl])
    bind(classOf[DirectorUsecase]).to(classOf[DirectorUsecaseImpl])
    bind(classOf[EmployeeUsecase]).to(classOf[EmployeeUsecaseImpl])
    bind(classOf[EmployeeByEmployeeIdUsecase]).to(classOf[EmployeeByEmployeeIdUsecaseImpl])
    bind(classOf[EmployeeFullInfoUsecase]).to(classOf[EmployeeFullInfoUsecaseImpl])
    bind(classOf[UpdateEmployeeUsecase]).to(classOf[UpdateEmployeeUsecaseImpl])
    bind(classOf[SeatingChartUsecase]).to(classOf[SeatingChartUsecaseImpl])
    bind(classOf[SeatingChartWithUsersUsecase]).to(classOf[SeatingChartWithUsersUsecaseImpl])




    //DB
    bind(classOf[AllSeatRepository]).to(classOf[AllSeatRepositoryImpl])
    bind(classOf[SeatRepository]).to(classOf[SeatRepositoryImpl])
    bind(classOf[HeadquarterRepository]).to(classOf[HeadquarterRepositoryImpl])
    bind(classOf[DivRepository]).to(classOf[DivRepositoryImpl])
    bind(classOf[GroupRepository]).to(classOf[GroupRepositoryImpl])
    bind(classOf[TeamRepository]).to(classOf[TeamRepositoryImpl])
    bind(classOf[DirectorRepository]).to(classOf[DirectorRepositoryImpl])
    bind(classOf[EmployeeRepository]).to(classOf[EmployeeRepositoryImpl])
    bind(classOf[EmployeeFullInfoRepository]).to(classOf[EmployeeFullInfoRepositoryImpl])
    bind(classOf[SeatingChartRepository]).to(classOf[SeatingChartRepositoryImpl])
    bind(classOf[SeatingChartWithUsersRepository]).to(classOf[SeatingChartWithUsersRepositoryImpl])

  }
}
