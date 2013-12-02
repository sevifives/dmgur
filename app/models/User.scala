package models

import play.api.db._
import play.api.Play.current
import scala.language.postfixOps
import scala.slick.direct.AnnotationMapper.column

case class User(id: Option[Int], email: String, name: String, password: String)

trait UserComponent {
  this: Profile =>
    
  import profile.simple._
  
  object Users extends Table[User]("users") {
    def id = column[Option[Int]]("id", O.PrimaryKey,O.AutoInc)
    def email = column[String]("email")
    def name = column[String]("name")
    def password = column[String]("password")
    
    def * = id ~ email ~ name ~ password <> (User, User.unapply _)
    
    private def autoInc(implicit session: Session) = name ~ email ~ password returning id into {
      case (_, id) => id
    }
    
    def insert(user: User)(implicit session: Session): User = {
      val id = autoInc.insert(user.name, user.email, user.password)
      user.copy(id = id)
    }
  }
}