package strategies;

import game.Producer;

import java.util.ArrayList;
import java.util.Collections;

public class PriceStrategy implements EnergyStrategy {
  /**
   * ordoneaza lista de producatori dupa strategie PRICE
   * @param producers lista de porducatori
   * @return lista ordonata
   */
  @Override
  public ArrayList<Producer> getSortedDistributorList(ArrayList<Producer> producers) {
    for (Producer p : producers) {
      p.setSortCriteria(EnergyChoiceStrategyType.PRICE.toString());
    }

    Collections.sort(producers);
    return producers;
  }
}
