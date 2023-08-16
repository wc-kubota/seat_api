package infrastructures.db

import com.google.inject.Inject
import infrastructures.seatApp.Tables.{MDirector, MDiv, MEmployee, MGroup, MHeadquarter, MOccupation, MSex, MTeam, MEmployeeRow}
import models.entities.department.{Div, Group, Headquarter, Team}
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
        sex <- MSex if (sex.sexId === employee.sexId)
        headquarter <- MHeadquarter if headquarter.headquarterId === employee.headquartersId
        div <- MDiv if div.divId === employee.divId
        group <- MGroup if group.groupId === employee.groupId
        team <- MTeam if team.teamId === employee.teamId
        director <- MDirector if director.directorId === employee.directorId
        occupation <- MOccupation if occupation.occupationId === employee.occupationId
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
        .map(_.map (
          { case (
          employee,
          sex,
          headquarter,
          div,
          group,
          team,
          director,
          occupation
          ) =>
          EmployeeFullInfo.apply(
              Employee.apply(
                EmployeeId.apply(employee.employeeId),
                NameKanji.apply(employee.nameKanji),
                NameKana.apply(employee.nameKana)
              ),
              Sex.apply(
                SexId.apply(sex.sexId),
                SexName.apply(sex.sexName)
              ),
              Headquarter.apply(
                HeadquarterId.apply(headquarter.headquarterId),
                HeadquarterName.apply(headquarter.headquarterName)
              ),
              Div.apply(
                DivId.apply(div.divId),
                DivName.apply(div.divName),
              ),
              Group.apply(
                GroupId.apply(group.groupId),
                GroupName.apply(group.groupName)
              ),
              Team.apply(
                TeamId.apply(team.teamId),
                TeamName.apply(team.teamName)
              ),
              Director.apply(
                DirectorId.apply(director.directorId),
                DirectorName.apply(director.directorName)
              ),
              Occupation.apply(
                OccupationId.apply(occupation.occupationId),
                OccupationName.apply(occupation.occupationName)
              )
            )
          }
        )
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
      nameKana = input.employeeFullInfo.employee.nameKana.value,
      sexId = input.employeeFullInfo.sex.sexId.value,
      headquartersId = Option(input.employeeFullInfo.headquarter.headquarterId.value),
      divId = Option(input.employeeFullInfo.div.divId.value),
      groupId = Option(input.employeeFullInfo.group.groupId.value),
      teamId = Option(input.employeeFullInfo.team.teamId.value),
      directorId = Option(input.employeeFullInfo.director.directorId.value),
      occupationId = Option(input.employeeFullInfo.occupation.occupationId.value),
      employeeId = Option(input.employeeFullInfo.employee.employeeId.value)
    )
  }
}

