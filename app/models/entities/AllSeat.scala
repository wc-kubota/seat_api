package models.entities

import models.vo.{SeatId,SeatX,SeatY,SeatWidth,SeatHeight,SeatEmployee,StaticStatus}

/**
 * 座席の情報
 *
 * @param seatId
 * @param seatX
 * @param seatY
 * @param seatWidth
 * @param seatHeight
 * @param seatEmployee
 * @param seatStaticStatus
 */

case class AllSeat (
                    seatId: SeatId,
                    seatX: SeatX,
                    seatY: SeatY,
                    seatWidth: SeatWidth,
                    seatHeight: SeatHeight,
                    seatEmployee: SeatEmployee,
                    seatStaticStatus: StaticStatus
                   )

