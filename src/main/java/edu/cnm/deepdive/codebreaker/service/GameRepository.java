package edu.cnm.deepdive.codebreaker.service;

import edu.cnm.deepdive.codebreaker.model.Game;
import edu.cnm.deepdive.codebreaker.model.Guess;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class GameRepository {

  private final WebServiceProxy proxy;

  public GameRepository() {
    proxy = WebServiceProxy.getInstance();
  }

  public Game startGame(String pool, int length) throws IOException, BadGameException {
    Game game = new Game();
    game.setPool(pool);
    game.setLength(length);
    Call<Game> call = proxy.startGame(game);
    Response<Game> response = call.execute();
    if (!response.isSuccessful()) {
      throw new BadGameException(response.message());
    }
    return response.body();
  }

  public Guess submitGuess(Game game, String text) throws IOException, BadGuessException {
    Guess guess = new Guess();
    guess.setText(text);
    Response<Guess> response = proxy
        .submitGuess(guess, game.getId())
        .execute();
    if (!response.isSuccessful()) { //when http is in 400 or 500 range
      throw new BadGuessException(response.message());
    }
    return response.body();
  }

  public static class BadGuessException extends IllegalArgumentException {

    public BadGuessException(String message) {
      super(message);
    }
  }

  public static class BadGameException extends IllegalArgumentException {

    public BadGameException(String message) {
      super(message);
    }
  }

}
