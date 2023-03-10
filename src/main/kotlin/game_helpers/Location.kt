package game_helpers

import run.Logger

/**
 * A Location on the chess board
 *
 * @param row The row
 * @param col The column
 * @author Lukas Waschuk
 */
class Location(row: Int, col: Int) {
    private val logger = Logger(this.javaClass.name)

    private val location: Pair<Int, Int>

    init {
        logger.trace("Creating new Location")
        this.location = Pair(row, col)
        logger.trace("Created Location: ${this.location}")
    }

    /**
     * A public method to return the row of a Location
     *
     * @author Lukas Waschuk
     */
    fun row(): Int {
        return this.location.first
    }

    /**
     * A public method to return the column of a Location
     *
     * @author Lukas Waschuk
     */
    fun column(): Int {
        return this.location.second
    }

    /**
     * A public method to return the values of a Location as a Pair
     *
     * @return Pair containing the integer location of row/col
     * @author Lukas Waschuk
     */
    fun value(): Pair<Int, Int> {
        return this.location
    }

    /**
     * A public method to see if a prospective move is actually on the board
     *
     * @return Boolean representing if it is a valid move or not
     * @author Lukas Waschuk
     */
    fun isValid(): Boolean {
        return this.row() in ChessBoard.ROW_START..ChessBoard.ROW_END
                && this.column() in ChessBoard.COL_START..ChessBoard.COL_END
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Location) {
            return false
        }
        return row() == other.row() && column() == other.column()
    }

    override fun hashCode(): Int {
        return location.hashCode()
    }

    operator fun plus(direction: Location): Location {
        return Location(row() + direction.row(), column() + direction.column())
    }
}