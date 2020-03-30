package name.sargon.knightpath

import name.sargon.knightpath.Board.Colour
import name.sargon.knightpath.Board.Colour.BLACK
import name.sargon.knightpath.Board.Colour.WHITE
import kotlin.system.measureTimeMillis
import kotlin.time.milliseconds

class BoardGenerator {
    companion object {
        fun generateDistinctBoards(allowedSquares: Bitboard, whiteKnightsCount: Int, blackKnightsCount: Int): List<Board> {
            var boards = listOf(Board(Bitboard(), Bitboard(), allowedSquares))
            var distinctBoards = emptyList<Board>()
            val duration = (measureTimeMillis {
                repeat(whiteKnightsCount) {
                    boards = boardsWithKnight(boards, WHITE)
                }

                repeat(blackKnightsCount) {
                    boards = boardsWithKnight(boards, BLACK)
                }

                distinctBoards = boards.distinct()
            }).milliseconds

            println("Generated ${boards.size} boards (#distinct=${distinctBoards.size}) in $duration")

            return distinctBoards
        }

        private fun boardsWithKnight(boards: List<Board>, colour: Colour): List<Board> {
            return boards
                .map { board -> boardsWithKnight(board, colour) }
                .flatten()
        }

        private fun boardsWithKnight(board: Board, colour: Colour): List<Board> {
            return emptySquares(board)
                .map { square -> board.withKnight(square, colour) }
                .toList()
        }

        private fun emptySquares(board: Board): Bitboard {
            val allowed = board.allowedSquares
            val occupied = board.knights()
            val empty = allowed.xor(occupied)

            return empty
        }
    }
}
