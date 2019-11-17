package ru.spbau.jvm.scala

import slick.jdbc.SQLiteProfile.api._


object PhonebookSchema {
  class User(tag: Tag) extends Table[(Int, String, String, Int)](tag, "User") {
    def id = column[Int]("id")
    def name = column[String]("name")
    def surname = column[String]("surname")
    def number_id = column[Int]("number_id")

    override def * = (id, name, surname, number_id)
  }

  class PhoneNumber(tag: Tag) extends Table[(Int, String)](tag, "Number") {
    def id = column[Int]("id")
    def number = column[String]("number")
    override def * = (id, number)
  }

  class Call(tag: Tag) extends Table[(Int, String, Int, Int, java.time.LocalDateTime)](tag, "Call") {
    def user_id = column[Int]("user_id")
    def callee = column[String]("callee")
    def time = column[Int]("time_s")
    def cost = column[Int]("cost")
    def datetime = column[java.time.LocalDateTime]("datetime")
    override def * = (user_id, callee, time, cost, datetime)
  }

  lazy val users = TableQuery[User]
  lazy val numbers = TableQuery[PhoneNumber]
  lazy val calls = TableQuery[Call]

  final val tablesAndFiles = Seq(
    (users.schema.create, "User"),
    (numbers.schema.create, "Number"),
    (calls.schema.create, "Call")
  )
}

object PhonebookQueries {
  import PhonebookSchema._

  def selectAllUsers = users.map(p => (p.id, p.name, p.number_id))

}