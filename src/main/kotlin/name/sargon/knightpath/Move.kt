package name.sargon.knightpath

data class Move(val from: Square, val to: Square) {

    override fun toString(): String {
        val fromSquare = NamedSquare.byValue(from).toString().toLowerCase()
        val toSquare = NamedSquare.byValue(to).toString().toLowerCase()

        return "${fromSquare}-${toSquare}"
    }

}
