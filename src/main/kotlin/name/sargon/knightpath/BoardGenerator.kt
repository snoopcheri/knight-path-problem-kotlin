package name.sargon.knightpath

import name.sargon.knightpath.Board.Colour
import name.sargon.knightpath.Board.Colour.BLACK
import name.sargon.knightpath.Board.Colour.WHITE

class BoardGenerator {

    fun generateBoards(allowedSquares: Bitboard, whiteKnightsCount: Int, blackKnightsCount: Int): List<Board> {
        var boards = listOf(Board(Bitboard(), Bitboard(), allowedSquares))

        repeat(whiteKnightsCount) {
            boards = boardsWithAddedKnight(boards, WHITE)
        }

        repeat(blackKnightsCount) {
            boards = boardsWithAddedKnight(boards, BLACK)
        }

        return boards
    }

    private fun boardsWithAddedKnight(boards: List<Board>, colour: Colour): List<Board> {
        val newBoards = mutableListOf<Board>()

        for (board in boards) {
            val allowed = board.allowedSquares
            val occupied = board.knights()
            val empty = allowed.xor(occupied)
            val availableSquares = empty.iterator()
            while (availableSquares.hasNext()) {
                val square = availableSquares.next()
                newBoards.add(board.withKnight(square, colour))
            }
        }

        return newBoards

    }
}
