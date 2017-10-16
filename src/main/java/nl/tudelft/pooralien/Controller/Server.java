package nl.tudelft.pooralien.Controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import nl.tudelft.pooralien.Observer;
import nl.tudelft.pooralien.Subject;

public final class Server implements Subject {
    private static Server server;
    private Queue<ServerThread> players;
    private ReadWriteLock readWriteLock;
    private Lock readLock;
    private Lock writeLock;
    private ArrayList<Observer> observers;

    private Server() {
        this.observers = new ArrayList<>();
        this.players = new LinkedList<>();
        this.readWriteLock = new ReentrantReadWriteLock();
        this.readLock = readWriteLock.readLock();
        this.writeLock = readWriteLock.writeLock();
    }

    public static synchronized Server getServer() {
        if (server == null) {
            server = new Server();
        }
        return server;
    }

    public boolean destroy() {
        // destroy the server here and clean up
        sendToAll("ServerIsDying;");
        readLock.lock();
        try {
            for (ServerThread player : players) {
                player.terminate();
            }
        } finally {
            readLock.unlock();
        }
        return true;
    }

    public void add(ServerThread player) {
        writeLock.lock();
        try {
            players.add(player);
        } finally {
            writeLock.unlock();
        }
        this.notifyObservers();
    }

    public void sendToOthers(String message, ServerThread currentPlayer) {
        // we synchronize on this because another thread might be calling
        // removeConnection so then we don't mees up the outputStreams
        readLock.lock();
        try {
            for (ServerThread player : players) {
                if (currentPlayer.equals(player)) {
                    continue;
                }
                player.sendMessage(message);
            }
        } finally {
            readLock.unlock();
        }
    }

    public void sendToAll(String message) {
        // we synchronize on this because another thread might be calling
        // removeConnection so then we don't mees up the outputStreams
        readLock.lock();
        try {
            for (ServerThread player : players) {
                player.sendMessage(message);
            }
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Remove a socket, and it's corresponding output from our list.
     *
     * @param player is the client
     */
    public void removeConnection(ServerThread player) {
        // Synchronize so we don't messup outputStreams,
        // if another thread calls sendToAll method.
        writeLock.lock();
        try {
            System.out.println("Removing connection " + player);
            if (players.element().equals(player)) {
                nextTurn();
            }
            // remove it from our hashtable and clients queue
            players.remove(player);
            // make sure it's closed
            player.terminate();
        } finally {
            writeLock.unlock();
        }
        this.notifyObservers();
    }

    public void startMultiPlayerGame() {
        sendToAll("Ready;");
        BoardFactory bFactory = new StandardBoardFactory();
        Board board = bFactory.createRandomBoard();
        int backgroundTileCount = 10;
        Color standardColor = Color.BLUE;
        BackgroundTileCatalog backgroundTileCatalog =
            new BackgroundTileCatalog(backgroundTileCount, standardColor);
       
        sendToAll("NewBoard;" + board.toString());

        sendToAll("NewBackgroundCatalog;" + backgroundTileCatalog.toString());

        Game.getGame().setMultiplayer(true);
        nextTurn();
    }

    public int getSize() {
        return players.size();
    }

    public void nextTurn() {
        writeLock.lock();
        try {
            // who is the current player.
            ServerThread currentPlayer = players.remove();
            // add the current player to the back of the queue
            players.add(currentPlayer);
            // send a message to the next player
            ServerThread nextPlayer = players.element();
            currentPlayer.sendMessage("Wait;");
            nextPlayer.sendMessage("Play;");
        } catch (NoSuchElementException e) {
            System.out.println(e);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer item : observers) {
            item.update(this);
        }
    }
}
