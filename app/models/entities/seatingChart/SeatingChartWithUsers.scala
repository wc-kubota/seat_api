package models.entities.seatingChart

import models.entities.employee.Employee
import models.vo._

/**
 * 座席の情報
 *
 * @param employeeId
 * @param seatId
 */

case class SeatingChartWithUsers(
                    employee: Employee,
                    seat: Seat
                   )

