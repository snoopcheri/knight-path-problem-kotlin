package name.sargon.knightpath


inline class Bitboard(val value: Long = 0): Iterable<Int> {

    companion object {

        fun bitIsValid(bit: Int): Boolean {
            return bit in 0..63
        }

    }

    fun countOnes(): Int = value.countOneBits()
    fun get(bit: Int): Boolean = (value and 1L.shl(bit)) != 0L

    fun set(bit: Int): Bitboard {
        return Bitboard(value or 1L.shl(bit))
    }

    fun cleared(bit: Int): Bitboard {
        return Bitboard(value and 1L.shl(bit).inv())
    }

    fun or(other: Bitboard): Bitboard {
        return Bitboard(value or other.value)
    }

    fun xor(other: Bitboard): Bitboard {
        return Bitboard(value xor other.value)
    }

    fun nextOne(): Int {
        val next = value.countTrailingZeroBits()
        assert(next in 0..63)

        return next
    }

    fun isEmpty(): Boolean {
        return value == 0L
    }

    override fun iterator(): Iterator<Int> {
        return BitIterator(this)
    }

}


class BitIterator(private var bitboard: Bitboard): Iterator<Int> {
    override fun hasNext(): Boolean {
        return !bitboard.isEmpty()
    }

    override fun next(): Int {
        val next = bitboard.nextOne()
        bitboard = bitboard.cleared(next)

        return next
    }

}
