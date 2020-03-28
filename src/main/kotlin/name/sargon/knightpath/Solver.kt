package name.sargon.knightpath

import java.util.*

class Solver(private val boards: List<Board>) {

    private val solutions = hashMapOf<Board, Map<Board, Int>>()

    fun solveFor(initialBoard: Board): Map<Board, Int> {
        println("Solving for board\n${initialBoard}")
        return solutions.computeIfAbsent(initialBoard) { board -> solve(board) }
    }

    private fun solve(initialBoard: Board): Map<Board, Int> {
        val unsolved = boards.toMutableList()
        val solved = hashMapOf<Board, Int>()
        var depth = 0

        markAsSolved(initialBoard, 0, unsolved, solved)

        while (true) {
            print("depth=${depth}, #unsolved=${unsolved.size}, #solved=${solved.size}")

            val solvedAtDepth = mutableListOf<Board>()

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

            println(" -> found ${solvedAtDepth.size} new solutions")

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

    private fun markAsSolved(board: Board, depth: Int, unsolved: MutableList<Board>, solved: HashMap<Board, Int>) {
        assert(unsolved.contains(board))
        assert(!solved.containsKey(board))

        unsolved.remove(board)
        solved[board] = depth
    }

}
