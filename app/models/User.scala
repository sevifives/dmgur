package models

case class User(id: Option[Int], firstName: String, lastName: String)

trait UserComponent {
  this: Profile =>
    
  import profile.simple._
  
  object Users extends Table[User]("users") {
    def id = column[Int]("ID", O.PrimaryKey,O.AutoInc)
    def firstName = column[String]("FIRST_NAME")
    def lastName = column[String]("LAST_NAME")
    
    def * = id.? ~ firstName ~ lastName <> (User, User.unapply _)
  }
}