package game;

public class ConsumerFactory implements PlayerFactory {
  public ConsumerFactory() {
  }

  /**
   * creeaza un consumator nou
   * @param hasContract daca are sau nu contract
   * @param id id-ul consumatorului
   * @param initialBudget bugetul sau initial
   * @param monthlyIncome veniturile lunare
   * @return
   */
  @Override
  public Player createPlayer(final boolean hasContract, final int id,
                             final int initialBudget,
                             final int monthlyIncome) {
    return new Consumer(hasContract, id, initialBudget, monthlyIncome);
  }
}
