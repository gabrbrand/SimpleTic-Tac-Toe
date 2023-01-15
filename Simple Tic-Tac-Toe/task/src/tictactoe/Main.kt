package tictactoe

import kotlin.system.exitProcess

fun main() {
    TicTacToe()
}

class TicTacToe {
    private val board = MutableList(3) { MutableList(3) { ' ' } }
    private var currentPlayer = 'X'

    init {
        drawBoard()
        while (true) {
            makeMove()
            drawBoard()
            checkWinningCondition()
            currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        }
    }

    private fun drawBoard() {
        println("---------")
        board.forEach { row -> println("| ${row.joinToString(" ")} |") }
        println("---------")
    }

    private fun makeMove() {
        val format = Regex("\\d+ \\d+")
        val correctFormat = Regex("[1-3] [1-3]")

        while (true) {
            val coordinates = readln()

            if (format.matches(coordinates)) {
                val (row, column) = coordinates.split(" ").map { it.toInt() }

                if (correctFormat.matches(coordinates)) {
                    if (board[row - 1][column - 1] == ' ') {
                        board[row - 1][column - 1] = currentPlayer
                        break
                    } else {
                        println("This cell is occupied! Choose another one!")
                    }
                } else {
                    println("Coordinates should be from 1 to 3!")
                }
            } else if (!coordinates.all { it.isDigit() || it == ' ' }) {
                println("You should enter numbers!")
            }
        }
    }

    private fun checkWinningCondition() {
        for (i in 0..2) {
            // Check rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] != ' ') {
                    println("${board[i][0]} wins")
                    exitProcess(0)
                }
            }

            // Check columns
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] != ' ') {
                    println("${board[0][i]} wins")
                    exitProcess(0)
                }
            }
        }

        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] != ' ') {
                println("${board[0][0]} wins")
                exitProcess(0)
            }
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            if (board[2][0] != ' ') {
                println("${board[2][0]} wins")
                exitProcess(0)
            }
        }

        if (board.all { row -> row.all { cell -> cell != ' ' } }) {
            println("Draw")
            exitProcess(0)
        }
    }
}