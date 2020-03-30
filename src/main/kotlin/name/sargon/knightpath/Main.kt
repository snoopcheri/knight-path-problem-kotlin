package name.sargon.knightpath

import name.sargon.knightpath.BoardGenerator.Companion.generateDistinctBoards
import name.sargon.knightpath.NamedSquare.*

fun main() {
    johannesExchangeProblem()
//    standardChessKnightExchangeProblem()
}

@Suppress("unused")
fun standardChessKnightExchangeProblem() {
    val allowedSquares = Bitboard(-1)
    val allBoards = generateDistinctBoards(allowedSquares, 2, 2)

    val startBoard = Board(
        Bitboard().set(G1.value).set(B1.value),
        Bitboard().set(G8.value).set(B8.value),
        allowedSquares
    )

    val endBoard = startBoard.withSwitchedKnights()

    val solver = Solver(allBoards)
    val solution = solver.solveFor(endBoard)
    showSolution(solution, startBoard, endBoard)

    val printer = PathPrinter(solution)
    printer.printPath(startBoard, endBoard)
}

@Suppress("unused")
private fun johannesExchangeProblem() {
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

    val startBoard = Board(
        Bitboard().set(B4.value).set(C2.value),
        Bitboard().set(A1.value).set(C1.value),
        allowedSquares
    )

    val endBoard = startBoard.withSwitchedKnights()

    val solver = Solver(allBoards)
    val solution = solver.solveFor(endBoard)
    showSolution(solution, startBoard, endBoard)

    val printer = PathPrinter(solution)
    printer.printPath(startBoard, endBoard)
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
