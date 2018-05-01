package org.sid

import scalaz.State

object StateTest {
  type Stack = List[Int]

  def pop: State[Stack, Int] = State{ case x :: xs => (xs, x) }
  def push(a: Int): State[Stack, Unit] = State{ s => (a :: s) -> () }

  def stackManip: State[Stack, Int] = for {
    _ <- push(3)
    a <- pop
    b <- pop
  } yield b

  def runTest() = {
    val result = stackManip(List(5, 8, 2, 1))
    // val result = stackManip.run(List(5, 8, 2, 1))
    println(result._1)
    println(result._2)
  }
}
