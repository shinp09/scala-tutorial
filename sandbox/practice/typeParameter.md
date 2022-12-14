# 型パラメーター ( type parameter )

クラスやメソッドなどで使用する型をパラメーター化して再利用性を高める機能
汎用的にしてインスタンス時にさまざまな型を適用したい時に効果を発揮
Scala では型パラメーターは A から順番に名前を付けることが慣習となっている

例) 一つの要素を保持し、要素の格納 / 取得操作ができるクラス

```scala
class Cell[A](var value: A) {
  def put(newValue: A): Unit = {
    value = newValue
  }

  def get(): A = value
}
```

初期値に`Int`型 の 1 を投入し、後から`String`型 を入れるとコンパイルエラーになる

```shell
scala> val cell = new Cell[Int](1)

// ERROR
scala> cell.put("something")
<console>:10: error: type mismatch;
 found   : String("something")
 required: Int
              cell.put("something")
                       ^
```

### メソッドから複数の値を返すケース

```scala
class Human[A, B](val a: A, val b: B) {
  override def toString(): String = "(" + a + "," + b + ")"
}

def addInformation(m: String, n: Int): Human[String, Int] = new Human[String, Int](m, n)

addInformation("Bob", 21)
// val res7: Human[String, Int] = (Tarou,21)
```

### 変位指定

#### 共変 ( covariant )

`[+T]`   
サブタイプを親クラスの代わりとして使用できる

```scala
abstract class Human {
  def name: String
}

case class Bob(name: String) extends Human

case class John(name: String) extends Human

object CovarianceTest extends App {
  def printNames(humans: List[Human]): Unit = {
    humans.foreach { human =>
      println(human.name)
    }
  }

  val bobs: List[Bob] = List(Bob("Bob1"), Bob("Bob2"))
  val johns: List[John] = List(John("John1"), John("John2"))

  //  List[Human] = List[Bob] であるため実行される
  printNames(bobs)
  // Bob1
  // Bob2

  //  List[Human] = List[John] であるため実行される
  printNames(johns)
  // John1
  // John2
}
```

### 反変

`[-T]`

まとめ中...

## 型パラメータの境界 ( bounds )

### 上限境界

`<:`  
型パラメーターがどのような型を継承しているかを指定する

```scala
abstract class Test {
  def test: String
}

// 上限限界として Test が指定されているため a / b は test を呼びだせる
class TestPair[A <: Test, B <: Test](val a: A, val b: B) extends Test {
  override def show: String = "(" + a.test + "," + b.test + ")"
}
```

### 下限境界

`>:`  
型パラメーターがどのようなスーパータイプであるかを指定する  
-> どのサブクラスまで許可されるかを示す

まとめ中...

```scala
trait Node[+B] {
  // 関数は引数の型パラメーターに対して反変であり、戻り値の型パラメーターに対しては共変であるためコンパイルエラーになる
  def prepend(elem: B): Node[B]
}

↓

trait Node[+B] {
  //  関数の引数の変位指定を逆転させるためにB型のスーパータイプであることを明示する
  def prepend[U >: B](elem: U): Node[B]
}
```
