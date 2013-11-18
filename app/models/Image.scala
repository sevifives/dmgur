package models
import play.api.Play.current

case class Image(id: Option[Int], name: String, description: String, ownerId: Int)

trait ImageComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Images extends Table[Image]("images") {
    def id = column[Int]("ID",O.PrimaryKey,O.AutoInc)
    def name = column[String]("NAME")
    def description = column[String]("Description")
    def ownerId = column[Int]("OWNER_ID")
    
    def * = id.? ~ name ~ description ~ ownerId <> (Image, Image.unapply _)
    
    def owner = foreignKey("OWNER_FK", ownerId, Users)(_.id)
  }
}