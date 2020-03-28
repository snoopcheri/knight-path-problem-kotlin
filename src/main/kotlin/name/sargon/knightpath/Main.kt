package name.sargon.knightpath

import name.sargon.knightpath.BoardGenerator.Companion.generateDistinctBoards
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

    val allBoards = generateDistinctBoards(allowedSquares, 2, 2)
    val solver = Solver(allBoards)

    val startBoard = Board(
        Bitboard().set(B4.value).set(C2.value),
        Bitboard().set(A1.value).set(C1.value),
        allowedSquares
    )

    val endBoard = Board(
        Bitboard().set(A1.value).set(C1.value),
        Bitboard().set(B4.value).set(C2.value),
        allowedSquares
    )

    val solution = solver.solveFor(endBoard)
    showSolution(solution, startBoard, endBoard)

    val finder = PathFinder(solution)
    finder.findPath(startBoard, endBoard)
}

private fun showSolution(solution: Map<Board, Int>, from: Board, to: Board) {
    println("From board\n${from}")
    println("To board\n${to}")

    if (solution.containsKey(from)) {
        println("#steps required = ${solution.get(from)}")
    } else {
        println("No path found")
    }
}
