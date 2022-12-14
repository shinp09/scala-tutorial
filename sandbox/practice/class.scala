class Point3D(val x: Int, val y: Int, val z: Int)

object Point3D {
  def Point3D(): Unit = {
    val p = new Point3D(10, 20, 30);
    println(p.x) // 10
    println(p.y) // 20
    println(p.z) // 30
  }
}

abstract class Shape {
  def area: Double
}

/*
   * RectangleとCircleの定義
   */
// Shape クラスを継承し、既に存在している area メソッドをオーバーラーイドする
class Rectangle(val w: Double, val h: Double) extends Shape {
  override def area: Double = w * h
}

class Circle(val w: Double, val h: Double) extends Shape {
  override def area: Double = w * h
}

var shape: Shape = new Rectangle(10.0, 20.0)
println(shape.area)
shape = new Circle(2.0)
println(shape.area)
