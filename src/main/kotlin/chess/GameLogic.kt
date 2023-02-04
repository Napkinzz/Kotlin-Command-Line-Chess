package chess

import pieces.*

class GameLogic(chessBoard: ChessBoard) {
    private var chessBoard: ChessBoard

    init {
        this.chessBoard = chessBoard
    }

    fun startGame() {
        initChessPieces()
        chessBoard.render()
    }

    private fun initChessPieces() {
        initPawns()
        initKnights()
        initRooks()
        initBishops()
        initQueens()
        initKings()
    }
    private fun initKings() {
        chessBoard.setPiece(Location(0,4), King(Color.W))
        chessBoard.setPiece(Location(7,4), King(Color.B))
    }
    private fun initQueens() {
        chessBoard.setPiece(Location(0,3), Queen(Color.W))
        chessBoard.setPiece(Location(7,3), Queen(Color.B))
    }

    private fun initBishops() {
        chessBoard.setPiece(Location(0,2), Bishop(Color.W))
        chessBoard.setPiece(Location(0,5), Bishop(Color.W))
        chessBoard.setPiece(Location(7,2), Bishop(Color.B))
        chessBoard.setPiece(Location(7,5), Bishop(Color.B))
    }
    private fun initKnights(){
        chessBoard.setPiece(Location(0,1), Knight(Color.W))
        chessBoard.setPiece(Location(0,6), Knight(Color.W))
        chessBoard.setPiece(Location(7,1), Knight(Color.B))
        chessBoard.setPiece(Location(7,6), Knight(Color.B))
    }

    private fun initRooks(){
        chessBoard.setPiece(Location(0, 0), Rook(Color.W))
        chessBoard.setPiece(Location(0, 7), Rook(Color.W))
        chessBoard.setPiece(Location(7, 0), Rook(Color.B))
        chessBoard.setPiece(Location(7, 7), Rook(Color.B))
    }

    private fun initPawns() {
        for (col in 0 until 8) {
            chessBoard.setPiece(Location(1, col), Pawn(Color.W))
            chessBoard.setPiece(Location(6, col), Pawn(Color.B))
        }
    }

    fun gameOver(turn: Turn): Boolean {
        return reachEnd() || elimAll() || stalemate(turn)
    }

    private fun elimAll(): Boolean {
        var whiteFound = false
        var blackFound = false

        for (row in 0 until 8) {
            for (col in 0 until 8) {
                when (chessBoard.getPiece(Location(row, col)).color) {
                    Color.B -> blackFound = true
                    Color.W -> whiteFound = true
                    else -> {}
                }
            }
        }
        if (whiteFound && !blackFound) {
            println("\nWhite Wins!")
            return true
        }
        if (blackFound && !whiteFound) {
            println("\nBlack Wins!")
            return true
        }
        return false
    }

    private fun reachEnd(): Boolean {
        for (col in 0 until 8) {
            if (chessBoard.getPiece(Location(0, col)).color == Color.B) {
                println("\nBlack Wins!")
                return true
            }
            if (chessBoard.getPiece(Location(7, col)).color == Color.W) {
                println("\nWhite Wins!")
                return true
            }
        }
        return false
    }

    private fun stalemate(turn: Turn): Boolean {
        for (row in 0 until 7) {
            for (col in 0 until 8) {
                val piece = chessBoard.getPiece(Location(row, col))
                if (piece.color == turn.getColor() && piece.canMove(Location(row, col), this.chessBoard, turn)
                        .isNotEmpty()
                ) {
                    return false
                }
            }
        }
        println("\nStalemate!")
        return true
    }

    fun sourceCoordinateVerifier(chessMove: ChessMove, turn: Turn): Boolean {
        val startCoordinates = chessMove.startLocation()
        val piece = chessBoard.getPiece(startCoordinates)
        return piece.type != PieceType.EMPTY && piece.color == turn.getColor()
    }


}