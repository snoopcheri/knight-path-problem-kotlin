package name.sargon.knightpath

import name.sargon.knightpath.Board.Colour.BLACK
import name.sargon.knightpath.Board.Colour.WHITE

data class Board(val whiteKnights: Bitboard, val blackKnights: Bitboard, val allowedSquares: Bitboard) {

    fun knights(): Bitboard {
        return whiteKnights.or(blackKnights)
    }

    fun withKnight(square: Square, colour: Colour): Board {
        return when (colour) {
            WHITE -> Board(whiteKnights.set(square), blackKnights, allowedSquares)
            BLACK -> Board(whiteKnights, blackKnights.set(square), allowedSquares)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other?.javaClass != javaClass) {
            return false
        }

        other as Board

        return whiteKnights.value == other.whiteKnights.value &&
                blackKnights.value == other.blackKnights.value
    }

    override fun hashCode(): Int {
        return 31 * whiteKnights.value.hashCode() + blackKnights.value.hashCode()
    }

    override fun toString(): String {
        val str = StringBuilder()

        for (y in maxY().downTo(minY())) {
            str.append(y + 1)

            for (x in minX()..maxX()) {
                str.append(" ")

                val square = toSquare(x, y)
                if (allowedSquares.get(square)) {
                    if (whiteKnights.get(square)) {
                        str.append("N")
                    } else if (blackKnights.get(square)) {
                        str.append("n")
                    } else {
                        str.append(".")
                    }
                } else {
                    str.append(" ")
                }
            }

            str.append("\n")
        }

        str.append(" ")
        for (x in minX()..maxX()) {
            str.append(" " + ('a' + x))
        }
        str.append("\n")

        return str.toString()
    }

    private fun minX(): Int {
        return allowedSquares.iterator().asSequence()
            .map { sq -> fromSquare(sq).first }
            .min()!!
    }

    private fun maxX(): Int {
        return allowedSquares.iterator().asSequence()
            .map { sq -> fromSquare(sq).first }
            .max()!!
    }

    private fun minY(): Int {
        return allowedSquares.iterator().asSequence()
            .map { sq -> fromSquare(sq).second }
            .min()!!
    }

    private fun maxY(): Int {
        return allowedSquares.iterator().asSequence()
            .map { sq -> fromSquare(sq).second }
            .max()!!
    }


    enum class Colour {
        WHITE,
        BLACK
    }

}
