package name.sargon.knightpath


class MoveGenerator {
    companion object {
        fun generateMoves(board: Board): List<Move> {
            return board.knights()
                .map { knight -> generateMoves(board, knight) }
                .flatten()
        }

        private val knightDistances = arrayOf(
            Pair(1, 2),
            Pair(2, 1),
            Pair(2, -1),
            Pair(1, -2),
            Pair(-1, -2),
            Pair(-2, -1),
            Pair(-2, 1),
            Pair(-1, 2)
        )

        private fun generateMoves(board: Board, from: Square): List<Move> {
            val moves = mutableListOf<Move>()

            for (knightDistance in knightDistances) {
                val to = squareOf(from, knightDistance)

                if (to != null && board.squareIsEmpty(to)) {
                    moves.add(Move(from, to))
                }
            }

            return moves
        }

        private fun squareOf(from: Square, knightDistance: Pair<Int, Int>): Square? {
            var (x, y) = fromSquare(from)
            x += knightDistance.first
            y += knightDistance.second

            if (x in 0..7 && y in 0..7) {
                return toSquare(x, y)
            }

            return null
        }
    }
}
