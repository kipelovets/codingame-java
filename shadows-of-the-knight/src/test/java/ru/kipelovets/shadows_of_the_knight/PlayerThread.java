package ru.kipelovets.shadows_of_the_knight;

public class PlayerThread extends Thread {

  @Override
  public void run() {
    Player.main(null);
  }
  
}
