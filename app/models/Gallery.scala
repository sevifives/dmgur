package models

case class Gallery(id: Option[Int], name: String, description: String, ownerId: Int)

trait GalleryComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Galleries extends Table[Gallery]("galleries") {
    def id = column[Int]("ID",O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def description = column[String]("DESCRIPTION")
    def ownerId = column[Int]("OWNER_ID")
    
    def * = id.? ~ name ~ description ~ ownerId <> (Gallery, Gallery.unapply _)
    
    def owner = foreignKey("OWNER_FK", ownerId, Users)(_.id)
  }
}