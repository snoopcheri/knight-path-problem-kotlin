package name.sargon.knightpath

import name.sargon.knightpath.NamedSquare.*


class MoveGenerator {
    companion object {
        fun generateMoves(board: Board): List<Move> {
            return board.knights()
                .map { knight -> generateMoves(board, knight) }
                .flatten()
        }

        private val knightDistances = arrayOf(
            distanceFromE4To(F6),
            distanceFromE4To(G5),
            distanceFromE4To(G3),
            distanceFromE4To(F2),
            distanceFromE4To(D2),
            distanceFromE4To(C3),
            distanceFromE4To(C5),
            distanceFromE4To(D6)
        )

        private fun distanceFromE4To(to: NamedSquare): Int {
            return to.value - E4.value
        }

        private fun generateMoves(board: Board, from: Square): List<Move> {
            return knightDistances
                .map { distance -> from + distance }
                .filter { to -> board.squareIsEmpty(to) }
                .map { to -> Move(from, to) }
                .toList()
        }
    }
}
