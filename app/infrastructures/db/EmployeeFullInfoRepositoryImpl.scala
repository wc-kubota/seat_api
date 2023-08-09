package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.{MDirector, MDiv, MEmployee, MGroup, MHeadquarter, MOccupation, MSex, MTeam, MEmployeeRow}
import models.entities.department.{Div, Headquarter, Group, Team}
import models.entities.employee.{Director, Employee, EmployeeFullInfo, Occupation, Sex}
import models.repositories.employee.EmployeeFullInfoRepository
import models.usecases.employee.UpdateEmployeeFullInfoInput
import models.vo._
import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class EmployeeFullInfoRepositoryImpl @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider
)(implicit ex: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] with EmployeeFullInfoRepository {

  override def employeeCount(): Future[Count] = {
    val query = MEmployee
      .length
      .result
      .map(r => Count(r))
    db.run(query)
  }

  override def fetchEmployeeFullInfo(): Future[Seq[EmployeeFullInfo]] = {
    val query = (for {
        employee <- MEmployee
        sex <- MSex if (employee.sexId === sex.sexId)
        headquarter <- MHeadquarter if employee.headquartersId === headquarter.headquarterId
        div <- MDiv if employee.divId === div.divId
        group <- MGroup if employee.groupId === group.groupId
        team <- MTeam if employee.teamId === team.teamId
        director <- MDirector if employee.directorId === director.directorId
        occupation <- MOccupation if employee.occupationId === occupation.occupationId
      } yield (
        employee,
        sex,
        headquarter,
        div,
        group,
        team,
        director,
        occupation
        )).result
      .map(_.map( r =>
        EmployeeFullInfo(
          Employee(
            EmployeeId(r._1.employeeId),
            NameKanji(r._1.nameKanji),
            NameKana(r._1.nameKana)
          ),
          Sex(
            SexId(r._2.sexId),
            SexName(r._2.sexName)
          ),
          Headquarter(
            HeadquarterId(Option(r._3.headquarterId)),
            HeadquarterName(Option(r._3.headquarterName))
          ),
          Div(
            DivId(Option(r._4.divId)),
            DivName(Option(r._4.divName)),
          ),
          Group(
            GroupId(Option(r._5.groupId)),
            GroupName(Option(r._5.groupName))
          ),
          Team(
            TeamId(Option(r._6.teamId)),
            TeamName(Option(r._6.teamName))
          ),
          Director(
            DirectorId(Option(r._7.directorId)),
            DirectorName(Option(r._7.directorName))
          ),
          Occupation(
            OccupationId(r._8.occupationId),
            OccupationName(Option(r._8.occupationName))
          )
        ))
      )
    db.run(query)
  }

  override def updateEmployeeFullInfo(input: UpdateEmployeeFullInfoInput, employeeId: EmployeeId): Future[Boolean] = {
    val employeeFullInfoRow = toEmployeeFullInfoRow(input)
    val query = MEmployee
      .filter(_.employeeId === input.employeeFullInfo.employee.employeeId.value)
      .update(employeeFullInfoRow)

    db.run(query).map(_ == 1)
  }

  private def toEmployeeFullInfoRow(input: UpdateEmployeeFullInfoInput): MEmployeeRow = {
    MEmployeeRow(
      nameKanji = input.employeeFullInfo.employee.nameKanji.value,
      nameKana = input.employeeFullInfo.employee.nameKanji.value,
      sexId = input.employeeFullInfo.sex.sexId.value,
      headquartersId = Option(input.employeeFullInfo.headquarter.headquarterId.value),
      divId = Option(input.employeeFullInfo.div.divId.value),
      groupId = Option(input.employeeFullInfo.group.groupId.value),
      teamId = Option(input.employeeFullInfo.team.teamId.value),
      directorId = Option(input.employeeFullInfo.director.directorId.value),
      occupationId = Option(input.employeeFullInfo.occupation.occupationId.value)
    )
  }
}

