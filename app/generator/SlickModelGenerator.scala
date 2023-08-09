package generator
import slick.jdbc.JdbcProfile
import slick.jdbc.meta.MTable
import slick.model.Model

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.Duration

import slick.codegen.SourceCodeGenerator
import slick.jdbc.meta.MTable
import slick.jdbc.MySQLProfile
import slick.jdbc.JdbcProfile
import java.net.URI
import scala.concurrent.Await
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration
import slick.model.Model
import javax.swing.table.TableModel
import slick.model.Table

object SlickModelGenerator {

  def run(slickDriver: String, jdbcDriver: String, url: String, user: String, password: String,
          tableNames: Option[Seq[String]], outputDir: String = "app", pkg: String = "models", topTraitName: String = "Tables", scalaFileName: String = "Tables.scala") = {
    val driver: JdbcProfile = slick.jdbc.MySQLProfile
    val db = slick.jdbc.MySQLProfile.api.Database.forURL(url, driver = jdbcDriver, user = user, password = password)
    try {
      import scala.concurrent.ExecutionContext.Implicits.global
      //      val mTablesAction = MTable.getTables.map{_.map{mTable => mTable.copy(name = mTable.name.copy(catalog=None))}}

      val modelAction = driver.createModel(Some(driver.defaultTables), ignoreInvalidDefaults = false)(ExecutionContext.global).withPinnedSession
      val allModel = Await.result(db.run(modelAction), Duration.Inf)
      val modelFiltered = tableNames.fold(allModel) { tableNames =>
        Model(tables = allModel.tables.filter { aTable =>
          tableNames.contains(aTable.name.table)
        })
      }

      new SourceCodeGeneratorEx(modelFiltered).writeToFile(slickDriver, outputDir, pkg, topTraitName, scalaFileName)
    } finally db.close
  }

  def main(args: Array[String]): Unit = {
    //各スキーマの設定でfunction作ってここにいれる
        exportCommonSchema
  }

  // commonスキーマ出力
  def exportCommonSchema = {
    val slickDriver = "slick.jdbc.MySQLProfile"
    val jdbcDriver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3307/seat_app?useSSL=false"
    val user = "kouki.kubota"
    val password = "password"
    val outputDir = "app"
    val pkg = "infrastructures.seatApp"
    val topTraitName = "Tables"
    val scalaFileName = "Tables.scala"

    // 対象テーブル
    val tableNames: Option[Seq[String]] = Some(
      Seq(
        "seats",
        "m_div",
        "m_employee",
        "m_group",
        "m_headquarter",
        "m_occupation",
        "m_remote",
        "m_seat",
        "m_sex",
        "m_team",
        "m_director",
        "remote_assignment",
        "seat_assignment"
      )
    )

    run(slickDriver, jdbcDriver, url, user, password, tableNames,
      outputDir, pkg, topTraitName, scalaFileName)
  }
}
