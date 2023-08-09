package infrastructures.seatApp
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(MDirector.schema, MDiv.schema, MEmployee.schema, MGroup.schema, MHeadquarter.schema, MOccupation.schema, MRemote.schema, MSeat.schema, MSex.schema, MTeam.schema, RemoteAssignment.schema, SeatAssignment.schema, Seats.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table MDirector
   *  @param directorId Database column director_id SqlType(INT), PrimaryKey
   *  @param directorName Database column director_name SqlType(VARCHAR), Length(255,true) */
  case class MDirectorRow(directorId: Int, directorName: String)
  /** GetResult implicit for fetching MDirectorRow objects using plain SQL queries */
  implicit def GetResultMDirectorRow(implicit e0: GR[Int], e1: GR[String]): GR[MDirectorRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MDirectorRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_director. Objects of this class serve as prototypes for rows in queries. */
  class MDirector(_tableTag: Tag) extends profile.api.Table[MDirectorRow](_tableTag, Some("seat_app"), "m_director") {
    def * = (directorId, directorName) <> (MDirectorRow.tupled, MDirectorRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(directorId), Rep.Some(directorName))).shaped.<>({r=>import r._; _1.map(_=> MDirectorRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column director_id SqlType(INT), PrimaryKey */
    val directorId: Rep[Int] = column[Int]("director_id", O.PrimaryKey)
    /** Database column director_name SqlType(VARCHAR), Length(255,true) */
    val directorName: Rep[String] = column[String]("director_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MDirector */
  lazy val MDirector = new TableQuery(tag => new MDirector(tag))

  /** Entity class storing rows of table MDiv
   *  @param divId Database column div_id SqlType(INT), PrimaryKey
   *  @param divName Database column div_name SqlType(VARCHAR), Length(255,true) */
  case class MDivRow(divId: Int, divName: String)
  /** GetResult implicit for fetching MDivRow objects using plain SQL queries */
  implicit def GetResultMDivRow(implicit e0: GR[Int], e1: GR[String]): GR[MDivRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MDivRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_div. Objects of this class serve as prototypes for rows in queries. */
  class MDiv(_tableTag: Tag) extends profile.api.Table[MDivRow](_tableTag, Some("seat_app"), "m_div") {
    def * = (divId, divName) <> (MDivRow.tupled, MDivRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(divId), Rep.Some(divName))).shaped.<>({r=>import r._; _1.map(_=> MDivRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column div_id SqlType(INT), PrimaryKey */
    val divId: Rep[Int] = column[Int]("div_id", O.PrimaryKey)
    /** Database column div_name SqlType(VARCHAR), Length(255,true) */
    val divName: Rep[String] = column[String]("div_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MDiv */
  lazy val MDiv = new TableQuery(tag => new MDiv(tag))

  /** Entity class storing rows of table MEmployee
   *  @param nameKanji Database column name_kanji SqlType(VARCHAR), Length(255,true)
   *  @param nameKana Database column name_kana SqlType(VARCHAR), Length(255,true)
   *  @param sexId Database column sex_id SqlType(INT)
   *  @param headquartersId Database column headquarters_id SqlType(INT), Default(None)
   *  @param divId Database column div_id SqlType(INT), Default(None)
   *  @param groupId Database column group_id SqlType(INT), Default(None)
   *  @param teamId Database column team_id SqlType(INT), Default(None)
   *  @param directorId Database column director_id SqlType(INT), Default(None)
   *  @param occupationId Database column occupation_id SqlType(INT), Default(None)
   *  @param employeeId Database column employee_id SqlType(INT), AutoInc, PrimaryKey */
  case class MEmployeeRow(nameKanji: String, nameKana: String, sexId: Int, headquartersId: Option[Int] = None, divId: Option[Int] = None, groupId: Option[Int] = None, teamId: Option[Int] = None, directorId: Option[Int] = None, occupationId: Option[Int] = None, employeeId: Option[Int] = None)
  /** GetResult implicit for fetching MEmployeeRow objects using plain SQL queries */
  implicit def GetResultMEmployeeRow(implicit e0: GR[String], e1: GR[Int], e2: GR[Option[Int]]): GR[MEmployeeRow] = GR{
    prs => import prs._
    val r = (<<?[Int], <<[String], <<[String], <<[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int])
    import r._
    MEmployeeRow.tupled((_2, _3, _4, _5, _6, _7, _8, _9, _10, _1)) // putting AutoInc last
  }
  /** Table description of table m_employee. Objects of this class serve as prototypes for rows in queries. */
  class MEmployee(_tableTag: Tag) extends profile.api.Table[MEmployeeRow](_tableTag, Some("seat_app"), "m_employee") {
    def * = (nameKanji, nameKana, sexId, headquartersId, divId, groupId, teamId, directorId, occupationId, Rep.Some(employeeId)) <> (MEmployeeRow.tupled, MEmployeeRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(nameKanji), Rep.Some(nameKana), Rep.Some(sexId), headquartersId, divId, groupId, teamId, directorId, occupationId, Rep.Some(employeeId))).shaped.<>({r=>import r._; _1.map(_=> MEmployeeRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7, _8, _9, _10)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column name_kanji SqlType(VARCHAR), Length(255,true) */
    val nameKanji: Rep[String] = column[String]("name_kanji", O.Length(255,varying=true))
    /** Database column name_kana SqlType(VARCHAR), Length(255,true) */
    val nameKana: Rep[String] = column[String]("name_kana", O.Length(255,varying=true))
    /** Database column sex_id SqlType(INT) */
    val sexId: Rep[Int] = column[Int]("sex_id")
    /** Database column headquarters_id SqlType(INT), Default(None) */
    val headquartersId: Rep[Option[Int]] = column[Option[Int]]("headquarters_id", O.Default(None))
    /** Database column div_id SqlType(INT), Default(None) */
    val divId: Rep[Option[Int]] = column[Option[Int]]("div_id", O.Default(None))
    /** Database column group_id SqlType(INT), Default(None) */
    val groupId: Rep[Option[Int]] = column[Option[Int]]("group_id", O.Default(None))
    /** Database column team_id SqlType(INT), Default(None) */
    val teamId: Rep[Option[Int]] = column[Option[Int]]("team_id", O.Default(None))
    /** Database column director_id SqlType(INT), Default(None) */
    val directorId: Rep[Option[Int]] = column[Option[Int]]("director_id", O.Default(None))
    /** Database column occupation_id SqlType(INT), Default(None) */
    val occupationId: Rep[Option[Int]] = column[Option[Int]]("occupation_id", O.Default(None))
    /** Database column employee_id SqlType(INT), AutoInc, PrimaryKey */
    val employeeId: Rep[Int] = column[Int]("employee_id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table MEmployee */
  lazy val MEmployee = new TableQuery(tag => new MEmployee(tag))

  /** Entity class storing rows of table MGroup
   *  @param groupId Database column group_id SqlType(INT), PrimaryKey
   *  @param groupName Database column group_name SqlType(VARCHAR), Length(255,true) */
  case class MGroupRow(groupId: Int, groupName: String)
  /** GetResult implicit for fetching MGroupRow objects using plain SQL queries */
  implicit def GetResultMGroupRow(implicit e0: GR[Int], e1: GR[String]): GR[MGroupRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MGroupRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_group. Objects of this class serve as prototypes for rows in queries. */
  class MGroup(_tableTag: Tag) extends profile.api.Table[MGroupRow](_tableTag, Some("seat_app"), "m_group") {
    def * = (groupId, groupName) <> (MGroupRow.tupled, MGroupRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(groupId), Rep.Some(groupName))).shaped.<>({r=>import r._; _1.map(_=> MGroupRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column group_id SqlType(INT), PrimaryKey */
    val groupId: Rep[Int] = column[Int]("group_id", O.PrimaryKey)
    /** Database column group_name SqlType(VARCHAR), Length(255,true) */
    val groupName: Rep[String] = column[String]("group_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MGroup */
  lazy val MGroup = new TableQuery(tag => new MGroup(tag))

  /** Entity class storing rows of table MHeadquarter
   *  @param headquarterId Database column headquarter_id SqlType(INT), PrimaryKey
   *  @param headquarterName Database column headquarter_name SqlType(VARCHAR), Length(255,true) */
  case class MHeadquarterRow(headquarterId: Int, headquarterName: String)
  /** GetResult implicit for fetching MHeadquarterRow objects using plain SQL queries */
  implicit def GetResultMHeadquarterRow(implicit e0: GR[Int], e1: GR[String]): GR[MHeadquarterRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MHeadquarterRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_headquarter. Objects of this class serve as prototypes for rows in queries. */
  class MHeadquarter(_tableTag: Tag) extends profile.api.Table[MHeadquarterRow](_tableTag, Some("seat_app"), "m_headquarter") {
    def * = (headquarterId, headquarterName) <> (MHeadquarterRow.tupled, MHeadquarterRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(headquarterId), Rep.Some(headquarterName))).shaped.<>({r=>import r._; _1.map(_=> MHeadquarterRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column headquarter_id SqlType(INT), PrimaryKey */
    val headquarterId: Rep[Int] = column[Int]("headquarter_id", O.PrimaryKey)
    /** Database column headquarter_name SqlType(VARCHAR), Length(255,true) */
    val headquarterName: Rep[String] = column[String]("headquarter_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MHeadquarter */
  lazy val MHeadquarter = new TableQuery(tag => new MHeadquarter(tag))

  /** Entity class storing rows of table MOccupation
   *  @param occupationName Database column occupation_name SqlType(VARCHAR), Length(255,true)
   *  @param occupationId Database column occupation_id SqlType(INT), AutoInc, PrimaryKey */
  case class MOccupationRow(occupationName: String, occupationId: Option[Int] = None)
  /** GetResult implicit for fetching MOccupationRow objects using plain SQL queries */
  implicit def GetResultMOccupationRow(implicit e0: GR[String], e1: GR[Option[Int]]): GR[MOccupationRow] = GR{
    prs => import prs._
    val r = (<<?[Int], <<[String])
    import r._
    MOccupationRow.tupled((_2, _1)) // putting AutoInc last
  }
  /** Table description of table m_occupation. Objects of this class serve as prototypes for rows in queries. */
  class MOccupation(_tableTag: Tag) extends profile.api.Table[MOccupationRow](_tableTag, Some("seat_app"), "m_occupation") {
    def * = (occupationName, Rep.Some(occupationId)) <> (MOccupationRow.tupled, MOccupationRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(occupationName), Rep.Some(occupationId))).shaped.<>({r=>import r._; _1.map(_=> MOccupationRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column occupation_name SqlType(VARCHAR), Length(255,true) */
    val occupationName: Rep[String] = column[String]("occupation_name", O.Length(255,varying=true))
    /** Database column occupation_id SqlType(INT), AutoInc, PrimaryKey */
    val occupationId: Rep[Int] = column[Int]("occupation_id", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table MOccupation */
  lazy val MOccupation = new TableQuery(tag => new MOccupation(tag))

  /** Entity class storing rows of table MRemote
   *  @param remoteStatusId Database column remote_status_id SqlType(BIT), PrimaryKey
   *  @param isRemoted Database column is_remoted SqlType(VARCHAR), Length(255,true) */
  case class MRemoteRow(remoteStatusId: Boolean, isRemoted: String)
  /** GetResult implicit for fetching MRemoteRow objects using plain SQL queries */
  implicit def GetResultMRemoteRow(implicit e0: GR[Boolean], e1: GR[String]): GR[MRemoteRow] = GR{
    prs => import prs._
    val r = (<<[Boolean], <<[String])
    import r._
    MRemoteRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_remote. Objects of this class serve as prototypes for rows in queries. */
  class MRemote(_tableTag: Tag) extends profile.api.Table[MRemoteRow](_tableTag, Some("seat_app"), "m_remote") {
    def * = (remoteStatusId, isRemoted) <> (MRemoteRow.tupled, MRemoteRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(remoteStatusId), Rep.Some(isRemoted))).shaped.<>({r=>import r._; _1.map(_=> MRemoteRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column remote_status_id SqlType(BIT), PrimaryKey */
    val remoteStatusId: Rep[Boolean] = column[Boolean]("remote_status_id", O.PrimaryKey)
    /** Database column is_remoted SqlType(VARCHAR), Length(255,true) */
    val isRemoted: Rep[String] = column[String]("is_remoted", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MRemote */
  lazy val MRemote = new TableQuery(tag => new MRemote(tag))

  /** Entity class storing rows of table MSeat
   *  @param seatId Database column seat_id SqlType(INT), PrimaryKey
   *  @param seatX Database column seat_x SqlType(INT)
   *  @param seatY Database column seat_y SqlType(INT)
   *  @param seatHeight Database column seat_height SqlType(INT)
   *  @param seatWidth Database column seat_width SqlType(INT)
   *  @param isFixed Database column is_fixed SqlType(BIT) */
  case class MSeatRow(seatId: Int, seatX: Int, seatY: Int, seatHeight: Int, seatWidth: Int, isFixed: Boolean)
  /** GetResult implicit for fetching MSeatRow objects using plain SQL queries */
  implicit def GetResultMSeatRow(implicit e0: GR[Int], e1: GR[Boolean]): GR[MSeatRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[Int], <<[Int], <<[Int], <<[Int], <<[Boolean])
    import r._
    MSeatRow.tupled((_1, _2, _3, _4, _5, _6)) // putting AutoInc last
  }
  /** Table description of table m_seat. Objects of this class serve as prototypes for rows in queries. */
  class MSeat(_tableTag: Tag) extends profile.api.Table[MSeatRow](_tableTag, Some("seat_app"), "m_seat") {
    def * = (seatId, seatX, seatY, seatHeight, seatWidth, isFixed) <> (MSeatRow.tupled, MSeatRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(seatId), Rep.Some(seatX), Rep.Some(seatY), Rep.Some(seatHeight), Rep.Some(seatWidth), Rep.Some(isFixed))).shaped.<>({r=>import r._; _1.map(_=> MSeatRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column seat_id SqlType(INT), PrimaryKey */
    val seatId: Rep[Int] = column[Int]("seat_id", O.PrimaryKey)
    /** Database column seat_x SqlType(INT) */
    val seatX: Rep[Int] = column[Int]("seat_x")
    /** Database column seat_y SqlType(INT) */
    val seatY: Rep[Int] = column[Int]("seat_y")
    /** Database column seat_height SqlType(INT) */
    val seatHeight: Rep[Int] = column[Int]("seat_height")
    /** Database column seat_width SqlType(INT) */
    val seatWidth: Rep[Int] = column[Int]("seat_width")
    /** Database column is_fixed SqlType(BIT) */
    val isFixed: Rep[Boolean] = column[Boolean]("is_fixed")
  }
  /** Collection-like TableQuery object for table MSeat */
  lazy val MSeat = new TableQuery(tag => new MSeat(tag))

  /** Entity class storing rows of table MSex
   *  @param sexId Database column sex_id SqlType(INT), PrimaryKey
   *  @param sexName Database column sex_name SqlType(VARCHAR), Length(255,true) */
  case class MSexRow(sexId: Int, sexName: String)
  /** GetResult implicit for fetching MSexRow objects using plain SQL queries */
  implicit def GetResultMSexRow(implicit e0: GR[Int], e1: GR[String]): GR[MSexRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MSexRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_sex. Objects of this class serve as prototypes for rows in queries. */
  class MSex(_tableTag: Tag) extends profile.api.Table[MSexRow](_tableTag, Some("seat_app"), "m_sex") {
    def * = (sexId, sexName) <> (MSexRow.tupled, MSexRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(sexId), Rep.Some(sexName))).shaped.<>({r=>import r._; _1.map(_=> MSexRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column sex_id SqlType(INT), PrimaryKey */
    val sexId: Rep[Int] = column[Int]("sex_id", O.PrimaryKey)
    /** Database column sex_name SqlType(VARCHAR), Length(255,true) */
    val sexName: Rep[String] = column[String]("sex_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MSex */
  lazy val MSex = new TableQuery(tag => new MSex(tag))

  /** Entity class storing rows of table MTeam
   *  @param teamId Database column team_id SqlType(INT), PrimaryKey
   *  @param teamName Database column team_name SqlType(VARCHAR), Length(255,true) */
  case class MTeamRow(teamId: Int, teamName: String)
  /** GetResult implicit for fetching MTeamRow objects using plain SQL queries */
  implicit def GetResultMTeamRow(implicit e0: GR[Int], e1: GR[String]): GR[MTeamRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    MTeamRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table m_team. Objects of this class serve as prototypes for rows in queries. */
  class MTeam(_tableTag: Tag) extends profile.api.Table[MTeamRow](_tableTag, Some("seat_app"), "m_team") {
    def * = (teamId, teamName) <> (MTeamRow.tupled, MTeamRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(teamId), Rep.Some(teamName))).shaped.<>({r=>import r._; _1.map(_=> MTeamRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column team_id SqlType(INT), PrimaryKey */
    val teamId: Rep[Int] = column[Int]("team_id", O.PrimaryKey)
    /** Database column team_name SqlType(VARCHAR), Length(255,true) */
    val teamName: Rep[String] = column[String]("team_name", O.Length(255,varying=true))
  }
  /** Collection-like TableQuery object for table MTeam */
  lazy val MTeam = new TableQuery(tag => new MTeam(tag))

  /** Entity class storing rows of table RemoteAssignment
   *  @param seatId Database column seat_id SqlType(INT), PrimaryKey
   *  @param isRemoted Database column is_remoted SqlType(VARCHAR), Length(45,true) */
  case class RemoteAssignmentRow(seatId: Int, isRemoted: String)
  /** GetResult implicit for fetching RemoteAssignmentRow objects using plain SQL queries */
  implicit def GetResultRemoteAssignmentRow(implicit e0: GR[Int], e1: GR[String]): GR[RemoteAssignmentRow] = GR{
    prs => import prs._
    val r = (<<[Int], <<[String])
    import r._
    RemoteAssignmentRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table remote_assignment. Objects of this class serve as prototypes for rows in queries. */
  class RemoteAssignment(_tableTag: Tag) extends profile.api.Table[RemoteAssignmentRow](_tableTag, Some("seat_app"), "remote_assignment") {
    def * = (seatId, isRemoted) <> (RemoteAssignmentRow.tupled, RemoteAssignmentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(seatId), Rep.Some(isRemoted))).shaped.<>({r=>import r._; _1.map(_=> RemoteAssignmentRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column seat_id SqlType(INT), PrimaryKey */
    val seatId: Rep[Int] = column[Int]("seat_id", O.PrimaryKey)
    /** Database column is_remoted SqlType(VARCHAR), Length(45,true) */
    val isRemoted: Rep[String] = column[String]("is_remoted", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table RemoteAssignment */
  lazy val RemoteAssignment = new TableQuery(tag => new RemoteAssignment(tag))

  /** Entity class storing rows of table SeatAssignment
   *  @param employeeId Database column employee_id SqlType(INT), Default(None)
   *  @param seatId Database column seat_id SqlType(INT) */
  case class SeatAssignmentRow(employeeId: Option[Int] = None, seatId: Int)
  /** GetResult implicit for fetching SeatAssignmentRow objects using plain SQL queries */
  implicit def GetResultSeatAssignmentRow(implicit e0: GR[Option[Int]], e1: GR[Int]): GR[SeatAssignmentRow] = GR{
    prs => import prs._
    val r = (<<?[Int], <<[Int])
    import r._
    SeatAssignmentRow.tupled((_1, _2)) // putting AutoInc last
  }
  /** Table description of table seat_assignment. Objects of this class serve as prototypes for rows in queries. */
  class SeatAssignment(_tableTag: Tag) extends profile.api.Table[SeatAssignmentRow](_tableTag, Some("seat_app"), "seat_assignment") {
    def * = (employeeId, seatId) <> (SeatAssignmentRow.tupled, SeatAssignmentRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((employeeId, Rep.Some(seatId))).shaped.<>({r=>import r._; _2.map(_=> SeatAssignmentRow.tupled((_1, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column employee_id SqlType(INT), Default(None) */
    val employeeId: Rep[Option[Int]] = column[Option[Int]]("employee_id", O.Default(None))
    /** Database column seat_id SqlType(INT) */
    val seatId: Rep[Int] = column[Int]("seat_id")
  }
  /** Collection-like TableQuery object for table SeatAssignment */
  lazy val SeatAssignment = new TableQuery(tag => new SeatAssignment(tag))

  /** Entity class storing rows of table Seats
   *  @param seatX Database column SEAT_X SqlType(INT), Default(None)
   *  @param seatY Database column SEAT_Y SqlType(INT), Default(None)
   *  @param seatWidth Database column SEAT_WIDTH SqlType(INT), Default(None)
   *  @param seatHeight Database column SEAT_HEIGHT SqlType(INT), Default(None)
   *  @param seatEmployee Database column SEAT_EMPLOYEE SqlType(INT), Default(None)
   *  @param staticStatus Database column STATIC_STATUS SqlType(INT), Default(None)
   *  @param seatId Database column SEAT_ID SqlType(INT), AutoInc, PrimaryKey */
  case class SeatsRow(seatX: Option[Int] = None, seatY: Option[Int] = None, seatWidth: Option[Int] = None, seatHeight: Option[Int] = None, seatEmployee: Option[Int] = None, staticStatus: Option[Int] = None, seatId: Option[Int] = None)
  /** GetResult implicit for fetching SeatsRow objects using plain SQL queries */
  implicit def GetResultSeatsRow(implicit e0: GR[Option[Int]]): GR[SeatsRow] = GR{
    prs => import prs._
    val r = (<<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int])
    import r._
    SeatsRow.tupled((_2, _3, _4, _5, _6, _7, _1)) // putting AutoInc last
  }
  /** Table description of table seats. Objects of this class serve as prototypes for rows in queries. */
  class Seats(_tableTag: Tag) extends profile.api.Table[SeatsRow](_tableTag, Some("seat_app"), "seats") {
    def * = (seatX, seatY, seatWidth, seatHeight, seatEmployee, staticStatus, Rep.Some(seatId)) <> (SeatsRow.tupled, SeatsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((seatX, seatY, seatWidth, seatHeight, seatEmployee, staticStatus, Rep.Some(seatId))).shaped.<>({r=>import r._; _7.map(_=> SeatsRow.tupled((_1, _2, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column SEAT_X SqlType(INT), Default(None) */
    val seatX: Rep[Option[Int]] = column[Option[Int]]("SEAT_X", O.Default(None))
    /** Database column SEAT_Y SqlType(INT), Default(None) */
    val seatY: Rep[Option[Int]] = column[Option[Int]]("SEAT_Y", O.Default(None))
    /** Database column SEAT_WIDTH SqlType(INT), Default(None) */
    val seatWidth: Rep[Option[Int]] = column[Option[Int]]("SEAT_WIDTH", O.Default(None))
    /** Database column SEAT_HEIGHT SqlType(INT), Default(None) */
    val seatHeight: Rep[Option[Int]] = column[Option[Int]]("SEAT_HEIGHT", O.Default(None))
    /** Database column SEAT_EMPLOYEE SqlType(INT), Default(None) */
    val seatEmployee: Rep[Option[Int]] = column[Option[Int]]("SEAT_EMPLOYEE", O.Default(None))
    /** Database column STATIC_STATUS SqlType(INT), Default(None) */
    val staticStatus: Rep[Option[Int]] = column[Option[Int]]("STATIC_STATUS", O.Default(None))
    /** Database column SEAT_ID SqlType(INT), AutoInc, PrimaryKey */
    val seatId: Rep[Int] = column[Int]("SEAT_ID", O.AutoInc, O.PrimaryKey)
  }
  /** Collection-like TableQuery object for table Seats */
  lazy val Seats = new TableQuery(tag => new Seats(tag))
}
