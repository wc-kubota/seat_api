package models.entities.seatingChart

import models.vo._

/**
 * 座席の情報
 *
 * @param seatId
 * @param seatX
 * @param seatY
 * @param seatWidths
 * @param seatHeight
 * @param seatEmployee
 * @param isFixed
 */

case class Seat(
                    seatId: SeatId,
                    seatX: SeatX,
                    seatY: SeatY,
                    seatWidth: SeatWidth,
                    seatHeight: SeatHeight,
                    isFixed: IsFixed
                   )

