package models
import play.api.Play.current

case class Comment(id: Option[Int], objId: Int, objType: Int, userId: Int, value: String)

trait CommentComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Comments extends Table[Comment]("comments") {
    def id = column[Int]("id", O.PrimaryKey,O.AutoInc)
    def objId = column[Int]("obj_id")
    def objType = column[Int]("obj_type")
    def userId = column[Int]("user_id")
    def value = column[String]("value")
  
    def user = foreignKey("user_fk", userId, Users)(_.id)

    def * = id.? ~ objId ~ objType ~ userId ~ value <> (Comment, Comment.unapply _)
  }
}