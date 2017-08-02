//열거 형태로 자기 자신을 return이 가능하고, 다음과 같이 class와 object에 자기 자신을 return하는 클래스 형태를 제공합니다.

sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()


// When 을 사용할 때 "else" 는 필요 없다. 왜냐면 모든 항목들이 열거 되었기 때문이다.

fun eval(expr: Expr): Double = when (expr) {
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
// the `else` clause is not required because we've covered all the cases
}

