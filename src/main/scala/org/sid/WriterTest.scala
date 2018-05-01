package org.sid

import scalaz.Scalaz._
import scalaz.Writer

object WriterTest {

  def add(i: Int, j: Int): Writer[String, Int] = (i + j).set(s"$i plus $j")
  def append(s: String): Writer[String, Unit] = s.tell
  def testWriter = for {
    a <- add(1, 2)
    b <- append("test")
  } yield a * 2

  def runTest() = {
    println(add(1, 2).run._1)
    val result = testWriter.run
    println(result._1)
    println(result._2)
  }

}
