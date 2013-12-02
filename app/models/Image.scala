package models
import play.api.Play.current
import scala.slick.session.Session
import scala.slick.driver.H2Driver
import scala.slick.driver.SQLiteDriver

case class Image(name: String, fileName: String, description: String, ownerId: Int, id: Option[Int] = None)

trait ImageComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Images extends Table[Image]("images") {
    def id = column[Option[Int]]("id",O.PrimaryKey,O.AutoInc)
    def name = column[String]("name")
    def fileName = column[String]("file_name")
    def description = column[String]("description")
    def ownerId = column[Int]("owner_id")
    
    def * = name ~ fileName ~ description ~ ownerId ~ id <> (Image, Image.unapply _)
    
    def owner = foreignKey("owner_fk", ownerId, Users)(_.id.get)
    
    val autoInc = name ~ fileName ~ description ~ ownerId returning id into { case (name, fileName, description, ownerId, id) => Image(name, fileName, description, ownerId, id) }

    def insert(picture: Image)(implicit session: Session): Image = {
      autoInc.insert(image.name, image.fileName, image.description, image.ownerId)
    }
  }
}