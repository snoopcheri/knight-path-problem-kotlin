package name.sargon.knightpath

import java.util.*
import kotlin.system.measureTimeMillis
import kotlin.time.milliseconds

class Solver(private val boards: List<Board>) {

    private val solutions = hashMapOf<Board, Map<Board, Int>>()

    fun solveFor(endBoard: Board): Map<Board, Int> {
        println("Solving for board\n${endBoard}")
        return solutions.computeIfAbsent(endBoard) { board -> solve(board) }
    }

    private fun solve(endBoard: Board): Map<Board, Int> {
        val unsolved = boards.toMutableSet()
        val solved = hashMapOf<Board, Int>()
        var depth = 0

        markAsSolved(endBoard, 0, unsolved, solved)

        while (true) {
            print("depth=${depth}, #unsolved=${unsolved.size}, #solved=${solved.size}")

            val solvedAtDepth = mutableListOf<Board>()

            val duration = (measureTimeMillis {
                for (board in unsolved) {
                    val successors = successorsOf(board)

                    val minDepth = successors
                        .mapNotNull { solved[it] }
                        .min()

                    if (minDepth != null) {
                        assert(minDepth == depth)
                        solvedAtDepth.add(board)
                    }
                }
            }).milliseconds

            println(" -> found ${solvedAtDepth.size} new solutions in $duration")

            if (solvedAtDepth.isEmpty()) {
                break
            }

            for (board in solvedAtDepth) {
                markAsSolved(board, depth + 1, unsolved, solved)
            }

            depth++
        }

        return solved.toMap()
    }

    private fun successorsOf(board: Board): List<Board> {
        return MoveGenerator.generateMoves(board)
            .map { move -> board.afterMove(move) }
    }

    private fun markAsSolved(board: Board, depth: Int, unsolved: MutableSet<Board>, solved: HashMap<Board, Int>) {
        assert(unsolved.contains(board))
        assert(!solved.containsKey(board))

        unsolved.remove(board)
        solved[board] = depth
    }

}
