package edu.cnm.deepdive.codebreaker;

import edu.cnm.deepdive.codebreaker.model.Game;
import edu.cnm.deepdive.codebreaker.service.GameRepository;
import java.io.IOException;

public class Application {

  private static final String DEFAULT_POOL = "ABCDEF";
  private static final int DEFAULT_LENGTH = 3;

  private final GameRepository repository;

  private Game game;

  private Application(String[] args) throws IOException {
    String pool = DEFAULT_POOL;
    int length = DEFAULT_LENGTH;
    // case 0: first it says, is it a case?, then evaluates top down default/cases
    switch (args.length) {
      default:
        // Deliberate fall-through!
      case 2:
        length = Integer.parseInt(args[1]);
        // Deliberate fall-through!
      case 1:
        pool = args[0];
        break;
      case 0:
        // Do nothing
    }
    repository = new GameRepository();
    startGame(pool, length);
  }

  public static void main(String[] args) throws IOException {
    Application application = new Application(args);
    // TODO While code is not guessed:
    //   1. Read guess from user input
    //   2. Submit guess to codebreaker service
    //   3. Display guess results
    //while(!game.isSolved()) {
    //  System.in.read();
    //}
  }

  private void startGame(String pool, int length) throws IOException {
    game = repository.startGame(pool, length);
  }
}
