//package dao
//
//import com.google.inject.{Inject, Singleton}
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//
//@Singleton
//class FetchSeatDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecuteContext) extends HasDatabaseConfigProvider[JdbcProfile] {
//
//  /*
//   * 全座席の取得処理
//   */
//  def fetchAllseat(): Future[]
//}
