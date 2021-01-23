package strategies;

import game.Producer;

import java.util.ArrayList;

public interface EnergyStrategy {

  /**
   * returneaza lista ordonata de producatori
   * @param producers lista de porducatori
   * @return lista ordonata
   */
  ArrayList<Producer> getSortedDistributorList(ArrayList<Producer> producers);
}
