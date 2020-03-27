package name.sargon.knightpath

import name.sargon.knightpath.NamedSquare.*

fun main() {
    val allowed: Bitboard = Bitboard()
        .set(A1.value)
        .set(B1.value)
        .set(B2.value)
        .set(B3.value)
        .set(B4.value)
        .set(C1.value)
        .set(C2.value)
        .set(C3.value)
        .set(D1.value)
        .set(D2.value)

    val squares = Squares(allowed)

    println(squares)
}
