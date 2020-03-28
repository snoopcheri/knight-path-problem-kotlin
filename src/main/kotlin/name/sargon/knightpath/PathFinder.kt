package name.sargon.knightpath

import name.sargon.knightpath.MoveGenerator.Companion.generateMoves
import java.lang.String.format

class PathFinder(private val depth: Map<Board, Int>) {

    var solutions = mutableListOf<Array<Move>>()

    fun findPath(start: Board, end: Board) {
        findPath(start, end, emptyArray())
    }

    private fun findPath(start: Board, end: Board, path: Array<Move>) {
        if (end == start) {
            storeSolution(path)
            return
        }

        val successors = successorsOf(start, depth[start]!!)
        assert(successors.isNotEmpty())

        val successor = successors.first()
        findPath(successor.second, end, path.plus(successor.first))
    }

    private fun storeSolution(path: Array<Move>) {
        solutions.add(path)

        val solutionCountString = format("%4d", solutions.size)
        println("#${solutionCountString} - ${path.contentToString()}")
    }

    private fun successorsOf(board: Board, depth: Int): List<Pair<Move, Board>> {
        return generateMoves(board)
            .map { move -> Pair(move, board.afterMove(move)) }
            .filter { this.depth[it.second] == depth - 1 }
    }

}
