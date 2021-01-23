import com.fasterxml.jackson.databind.ObjectMapper;
import game.Gamestate;
import input.Input;
import output.Output;

import java.io.File;

public final class Main {
  private Main() {
  }

  /**
   * functia main
   * @param args argumentele de input
   * @throws Exception arunga exceptiile
   */
  public static void main(final String[] args) throws Exception {
    if (args.length != 2) {
      // Verifica daca a primit ambele argumente
      System.out.println("Wrong input!");
      return;
    }

    // Creaza fisierele
    File inputFile = new File(args[0]);
    File outputFile = new File(args[1]);

    if (!inputFile.exists()) {
      // Verifica daca exista fisierul de input
      System.out.println("Files do not exist");
      return;
    }

    // Citeste input-ul
    ObjectMapper objectMapper =  new ObjectMapper();
    Input input = objectMapper.readValue(inputFile, Input.class);
    Gamestate.getInstance().playInitialRound(input);

    // Joaca fiecare runda
    for (int i = 0; i <= Gamestate.getInstance().getNumberOfTurns(); i++) {
      int flag = Gamestate.getInstance().playRound(i, input);
      if (flag == -1) {
        // Daca da return la -1 se opreste
        break;
      }
    }

    // Creaza output-ul
    Output out = Gamestate.getInstance().makeOutput();
    objectMapper.writeValue(outputFile, out);
    Gamestate.getInstance().reset();
  }
}
