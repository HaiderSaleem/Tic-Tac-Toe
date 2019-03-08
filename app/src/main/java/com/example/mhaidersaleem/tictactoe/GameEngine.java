package com.example.mhaidersaleem.tictactoe;

import java.util.Random;

public class GameEngine {

    private static final Random RANDOM = new Random();
    private char[] elts;
    private char currentPlayer;
    private boolean ended;

    public GameEngine() {
        elts = new char[9];
        newGame();
    }

    public boolean isEnded() {
        return ended;
    }

    public char play(int x, int y) {
        if (!ended  &&  elts[3 * y + x] == ' ') {
            elts[3 * y + x] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    public char getElt(int x, int y) {
        return elts[3 * y + x];
    }

    public void newGame() {
        for (int i = 0; i  < elts.length; i++) {
            elts[i] = ' ';
        }

        currentPlayer = 'X';
        ended = false;
    }

    public char checkEnd() {
        for (int i = 0; i < 3; i++) {
            if (getElt(i, 0) != ' ' &&
                    getElt(i, 0) == getElt(i, 1)  &&
                    getElt(i, 1) == getElt(i, 2)) {
                ended = true;
                return getElt(i, 0);
            }

            if (getElt(0, i) != ' ' &&
                    getElt(0, i) == getElt(1, i)  &&
                    getElt(1, i) == getElt(2, i)) {
                ended = true;
                return getElt(0, i);
            }
        }

        if (getElt(0, 0) != ' '  &&
                getElt(0, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(2, 2)) {
            ended = true;
            return getElt(0, 0);
        }

        if (getElt(2, 0) != ' '  &&
                getElt(2, 0) == getElt(1, 1)  &&
                getElt(1, 1) == getElt(0, 2)) {
            ended = true;
            return getElt(2, 0);
        }

        for (int i = 0; i < 9; i++) {
            if (elts[i] == ' ')
                return ' ';
        }

        return 'T';
    }

    public char computer() {
        if (!ended) {
            int position = -1;

            do {
                position = RANDOM.nextInt(9);
            } while (elts[position] != ' ');

            elts[position] = currentPlayer;
            changePlayer();
        }

        return checkEnd();
    }

}