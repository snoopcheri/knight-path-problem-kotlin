package name.sargon.knightpath

import name.sargon.knightpath.BoardGenerator.Companion.generateBoards
import name.sargon.knightpath.MoveGenerator.Companion.generateMoves
import name.sargon.knightpath.NamedSquare.*

fun main() {
    val allowedSquares: Bitboard = Bitboard()
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

    val boards = generateBoards(allowedSquares, 2, 2)
    println("#boards = ${boards.size}")
    println("#distinct(boards) = ${boards.distinct().size}")

    println(boards.first())
    println(boards.last())


    val board = Board(
        Bitboard().set(B4.value).set(C2.value),
        Bitboard().set(A1.value).set(C1.value),
        allowedSquares
    )

    println(board)
    println(generateMoves(board))
}
