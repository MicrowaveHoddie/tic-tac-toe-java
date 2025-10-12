package org.example;

public class App {
  public String getGreeting() {
    return "Welcome to the Tic Tac Toe program!";
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());
      new Game().start();
  }
}
