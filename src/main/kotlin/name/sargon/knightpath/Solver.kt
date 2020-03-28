package name.sargon.knightpath

class Solver(private val boards: List<Board>) {

    private val solutions = hashMapOf<Board, Map<Board, Int>>()

    fun solveFor(initialBoard: Board): Map<Board, Int> {
        println("Solving for board\n${initialBoard}")
        return solutions.computeIfAbsent(initialBoard) { board -> solve(board) }
    }

    private fun solve(initialBoard: Board): Map<Board, Int> {
        val unsolved = boards.toMutableList()
        unsolved.remove(initialBoard)
        val solved = hashMapOf(initialBoard to 0)
        var depth = 0

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
                assert(unsolved.contains(board))
                assert(!solved.containsKey(board))

                unsolved.remove(board)
                solved[board] = depth + 1
            }

            depth++
        }

        return solved.toMap()
    }

    private fun successorsOf(board: Board): List<Board> {
        return MoveGenerator.generateMoves(board)
            .map { move -> board.afterMove(move) }
    }

}
