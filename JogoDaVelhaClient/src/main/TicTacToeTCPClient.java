package main;

import connection.PlayerInfoReader;
import domain.Player;
import game.GameManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TicTacToeTCPClient implements Runnable {

    private static final String host = "localhost";
    private static final int port = 3002;

    @Override
    public void run() {
        connect();
    }

    private void connect() {
        try {
            Socket socket = new Socket(host, port);
            GameManager gameManager = new GameManager(new ConnectionHandler(socket));
            gameManager.manageGame();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
