package game;

public interface PlayerFactory {
  /**
   * functia de creare a unui consumator
   * @param hasContract daca are sau nu contract
   * @param id id-ul consumatorului
   * @param initialBudget bugetul sau initial
   * @param monthlyIncome veniturile lunare
   * @return
   */
  Player createPlayer(boolean hasContract, int id, int initialBudget, int monthlyIncome);
}
