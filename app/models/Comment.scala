package models
import play.api.Play.current

case class Comment(id: Option[Int], objId: Int, objType: Int, userId: Int, value: String)

trait CommentComponent {
  this: Profile with UserComponent =>
    
  import profile.simple._
  
  object Comments extends Table[Comment]("comments") {
    def id = column[Int]("ID", O.PrimaryKey,O.AutoInc)
    def objId = column[Int]("OBJ_ID")
    def objType = column[Int]("OBJ_TYPE")
    def userId = column[Int]("USER_ID")
    def value = column[String]("VALUE")
  
    def user = foreignKey("USER_FK", userId, Users)(_.id)

    def * = id.? ~ objId ~ objType ~ userId ~ value <> (Comment, Comment.unapply _)
  }
}