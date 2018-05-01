package org.sid

import scalaz.Reader

object Main extends App {

  def countChar(): Reader[String, Int] = Reader{ s => s.length }
  def path(prefix: String): Reader[String, String] = Reader{ s => prefix + " " + s }
  def add(addend: Int): Reader[Int, Int] = Reader{ i => i + addend }

  def plusInt(i: Int) = for {
    a <- countChar()
  } yield a + i

  def plusString(s: String) = for {
    a <- countChar()
  } yield a + s

  val testReader = for {
    a <- countChar()
    b <- path("lalala")
  } yield a + b.length

//  compile fail:
//  found: scalaz.Kleisli[Id, Int, Int]
//  required: scalaz.Kleisli[Id, String, ?]
//  val testReader2 = for {
//    a <- countChar()
//    b <- add(3)
//  } yield a + b
//  countChar().flatMap{ i => add(i).flatMap(_ + 1) }

  val testReader3 = for {
    a <- countChar()
    b <- path("lalala")
  } yield b


  println(plusInt(10).run("test"))
  println(plusString("xxx").run("test"))
  println(testReader.run("ha"))
  println(testReader3.run("ha"))
}