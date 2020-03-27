package name.sargon.knightpath

class Squares(val allowedSquares: Bitboard) {

    var squares: Bitboard = Bitboard(0)

    fun set(square: Square) {
        assert(allowedSquares.get(square))
        squares = squares.set(square)
    }

    override fun toString(): String {
        val str = StringBuilder()

        for (y in maxY().downTo(minY())) {
            str.append(y + 1)

            for (x in minX()..maxX()) {
                str.append(" ")

                val square = toSquare(x, y)
                if (allowedSquares.get(square)) {
                    str.append(if (squares.get(square)) "*" else ".")
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

}
