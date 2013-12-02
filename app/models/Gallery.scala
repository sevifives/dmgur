package models

case class Gallery(id: Option[Int], name: String, description: String, ownerId: Int)

trait GalleryComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Galleries extends Table[Gallery]("galleries") {
    def id = column[Int]("id",O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def ownerId = column[Int]("owner_id")
    
    def * = id.? ~ name ~ description ~ ownerId <> (Gallery, Gallery.unapply _)
    
    def owner = foreignKey("owner_fkK", ownerId, Users)(_.id)
  }
}