package constants;

import java.nio.file.Paths;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Represents the "storage" of the game music and sounds. Classes that need
 * music/sounds call these static methods without having to import the MP3-file.
 */
public interface Sounds {

   final Media badBoys = new Media(Paths.get("assets/badboys.mp3").toUri().toString());
   final MediaPlayer MPBadBoys = new MediaPlayer(badBoys);

   final Media citySound = new Media(Paths.get("assets/citysound.mp3").toUri().toString());
   final MediaPlayer MPCitySound = new MediaPlayer(citySound);

   final Media dollar = new Media(Paths.get("assets/dollar.mp3").toUri().toString());
   final MediaPlayer MPDollar = new MediaPlayer(dollar);

   final Media race = new Media(Paths.get("assets/race.mp3").toUri().toString());
   final MediaPlayer MPRace = new MediaPlayer(race);

   final Media move = new Media(Paths.get("assets/move.mp3").toUri().toString());
   final MediaPlayer MPMove = new MediaPlayer(move);

   final Media clintan = new Media(Paths.get("assets/clintan.mp3").toUri().toString());
   final MediaPlayer MPClintan = new MediaPlayer(clintan);

   final Media west = new Media(Paths.get("assets/west.mp3").toUri().toString());
   final MediaPlayer MPWest = new MediaPlayer(west);

   public static void playBadBoys() {
      MPBadBoys.play();
   }

   public static void playCitySound() {
      MPCitySound.play();
   }

   public static void playDollar() {
      MPDollar.play();
   }

   public static void playRace() {
      MPRace.play();
   }

   public static void playMove() {
      MPMove.play();
   }

   public static void playClintan() {
      MPClintan.play();
   }

   public static void playWest() {
      MPWest.play();
   }

   public static void stopBadBoys() {
      MPBadBoys.stop();
   }

   public static void stopCitySound() {
      MPCitySound.stop();
   }

   public static void stopDollar() {
      MPDollar.stop();
   }

   public static void stopRace() {
      MPRace.stop();
   }

   public static void stopMove() {
      MPMove.stop();
   }

   public static void stopClintan() {
      MPClintan.stop();
   }

   public static void stopWest() {
      MPWest.stop();
   }

}
