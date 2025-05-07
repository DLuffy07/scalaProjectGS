package mylist

sealed abstract class MyList[+A] {
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String

  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def ++[B >: A](otherList: MyList[B]): MyList[B]

  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B

  override def toString: String = "[" + printElements + "]"
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException("Empty list has no head")
  def tail: MyList[Nothing] = throw new NoSuchElementException("Empty list has no tail")
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def ++[B >: Nothing](otherList: MyList[B]): MyList[B] = otherList

  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty
  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    throw new RuntimeException("Cannot zip two lists of unequal length")
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A] {
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = Cons(element, this)
  def printElements: String =
    if (tail.isEmpty) s"$head"
    else s"$head ${tail.printElements}"

  def map[B](transformer: A => B): MyList[B] =
    Cons(transformer(head), tail.map(transformer))

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(head)) Cons(head, tail.filter(predicate))
    else tail.filter(predicate)

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(head) ++ tail.flatMap(transformer)

  def ++[B >: A](otherList: MyList[B]): MyList[B] =
    Cons(head, tail ++ otherList)

  def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sorted: MyList[A]): MyList[A] = sorted match {
      case Empty => Cons(x, Empty)
      case Cons(h, t) =>
        if (compare(x, h) <= 0) Cons(x, sorted)
        else Cons(h, insert(x, t))
    }
    insert(head, tail.sort(compare))
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = list match {
    case Empty => throw new RuntimeException("Cannot zip two lists of unequal length")
    case Cons(h, t) => Cons(zip(head, h), tail.zipWith(t, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start, head)
    tail.fold(newStart)(operator)
  }
}
