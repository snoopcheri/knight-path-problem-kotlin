package name.sargon.knightpath

typealias Square = Int

fun toSquare(x: Int, y: Int): Square {
    return x + (y * 8)
}

fun fromSquare(square: Square): Pair<Int, Int> {
    val x: Int = square % 8
    val y: Int = square / 8

    return Pair(x, y)
}

@Suppress("unused")
enum class NamedSquare(val value:Int) {

    A1(toSquare(0, 0)),
    A2(toSquare(0, 1)),
    A3(toSquare(0, 2)),
    A4(toSquare(0, 3)),
    A5(toSquare(0, 4)),
    A6(toSquare(0, 5)),
    A7(toSquare(0, 6)),
    A8(toSquare(0, 7)),

    B1(toSquare(1, 0)),
    B2(toSquare(1, 1)),
    B3(toSquare(1, 2)),
    B4(toSquare(1, 3)),
    B5(toSquare(1, 4)),
    B6(toSquare(1, 5)),
    B7(toSquare(1, 6)),
    B8(toSquare(1, 7)),

    C1(toSquare(2, 0)),
    C2(toSquare(2, 1)),
    C3(toSquare(2, 2)),
    C4(toSquare(2, 3)),
    C5(toSquare(2, 4)),
    C6(toSquare(2, 5)),
    C7(toSquare(2, 6)),
    C8(toSquare(2, 7)),

    D1(toSquare(3, 0)),
    D2(toSquare(3, 1)),
    D3(toSquare(3, 2)),
    D4(toSquare(3, 3)),
    D5(toSquare(3, 4)),
    D6(toSquare(3, 5)),
    D7(toSquare(3, 6)),
    D8(toSquare(3, 7)),

    E1(toSquare(4, 0)),
    E2(toSquare(4, 1)),
    E3(toSquare(4, 2)),
    E4(toSquare(4, 3)),
    E5(toSquare(4, 4)),
    E6(toSquare(4, 5)),
    E7(toSquare(4, 6)),
    E8(toSquare(4, 7)),

    F1(toSquare(5, 0)),
    F2(toSquare(5, 1)),
    F3(toSquare(5, 2)),
    F4(toSquare(5, 3)),
    F5(toSquare(5, 4)),
    F6(toSquare(5, 5)),
    F7(toSquare(5, 6)),
    F8(toSquare(5, 7)),

    G1(toSquare(6, 0)),
    G2(toSquare(6, 1)),
    G3(toSquare(6, 2)),
    G4(toSquare(6, 3)),
    G5(toSquare(6, 4)),
    G6(toSquare(6, 5)),
    G7(toSquare(6, 6)),
    G8(toSquare(6, 7)),

    H1(toSquare(7, 0)),
    H2(toSquare(7, 1)),
    H3(toSquare(7, 2)),
    H4(toSquare(7, 3)),
    H5(toSquare(7, 4)),
    H6(toSquare(7, 5)),
    H7(toSquare(7, 6)),
    H8(toSquare(7, 7));

    companion object {
        private val values = values()
        fun byValue(value: Int) = values.firstOrNull { it.value == value }
    }

}
