package strategies;

import game.Producer;

import java.util.ArrayList;
import java.util.Collections;

public class GreenStrategy implements EnergyStrategy {

  /**
   * ordoneaza producatorii dupa strategia GREEN
   * @param producers lista de porducatori
   * @return lista ordonata
   */
  @Override
  public ArrayList<Producer> getSortedDistributorList(ArrayList<Producer> producers) {
    ArrayList<Producer> aux = new ArrayList<>();
    for (Producer p : producers) {
      p.setSortCriteria(EnergyChoiceStrategyType.GREEN.toString());
      aux.add(p);
    }
    Collections.sort(aux);
    return aux;
  }
}
