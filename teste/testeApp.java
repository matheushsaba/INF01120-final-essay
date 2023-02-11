package teste;
import app.*;
import org.jfugue.player.Player;
import org.jfugue.theory.Note;

public class testeApp {
    public static void main (String[] args) {

        // Teste de exemplo da biblioteca
        Player player = new Player();
        //player.play("C D E F G A B");
        player.play("C D");

        // Teste de criação de objeto da classe Note
        TextToSoundConverter converter = new TextToSoundConverter();
        Note note = converter.convertTextToNote("C");
    }
}
