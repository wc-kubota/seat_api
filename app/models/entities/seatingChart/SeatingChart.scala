package models.entities.seatingChart

import models.vo._

/**
 * 座席の情報
 *
 * @param employeeId
 * @param seatId
 */

case class SeatingChart(
                    employeeId: EmployeeId,
                    seatId: SeatId
                   )

