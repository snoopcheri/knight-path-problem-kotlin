package name.sargon.knightpath

import name.sargon.knightpath.Board.Colour
import name.sargon.knightpath.Board.Colour.BLACK
import name.sargon.knightpath.Board.Colour.WHITE

class BoardGenerator {
    companion object {
        fun generateBoards(allowedSquares: Bitboard, whiteKnightsCount: Int, blackKnightsCount: Int): List<Board> {
            var boards = listOf(Board(Bitboard(), Bitboard(), allowedSquares))

            repeat(whiteKnightsCount) {
                boards = boardsWithKnight(boards, WHITE)
            }

            repeat(blackKnightsCount) {
                boards = boardsWithKnight(boards, BLACK)
            }

            return boards
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
