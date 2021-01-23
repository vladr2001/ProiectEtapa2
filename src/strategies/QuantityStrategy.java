package strategies;

import game.Producer;

import java.util.ArrayList;
import java.util.Collections;

public class QuantityStrategy implements EnergyStrategy {
  /**
   * ordoneaza lista dupa strategia QUANTITY
   * @param producers lista de porducatori
   * @return lista ordonata
   */
  @Override
  public ArrayList<Producer> getSortedDistributorList(ArrayList<Producer> producers) {
    for (Producer p : producers) {
      p.setSortCriteria(EnergyChoiceStrategyType.QUANTITY.toString());
    }

    Collections.sort(producers);
    return producers;
  }
}
