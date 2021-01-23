package output;

import java.util.ArrayList;

public class DistributorOutput implements Comparable<DistributorOutput> {
  private int id;
  private int energyNeededKW;
  private int contractCost;
  private long budget;
  private String producerStrategy;
  private boolean isBankrupt;
  private ArrayList<Contract> contracts = new ArrayList<>();

  public DistributorOutput(final int id, final int energyNeededKW, final int contractCost,
                           final long budget, final String producerStrategy,
                           final boolean isBankrupt) {
    this.id = id;
    this.energyNeededKW = energyNeededKW;
    this.contractCost = contractCost;
    this.budget = budget;
    this.producerStrategy = producerStrategy;
    this.isBankrupt = isBankrupt;
  }

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final long getBudget() {
    return budget;
  }

  public final void setBudget(final long budget) {
    this.budget = budget;
  }

  public final boolean getIsBankrupt() {
    return isBankrupt;
  }

  public final void setIsBankrupt(final boolean isBankrupt) {
    this.isBankrupt = isBankrupt;
  }

  public final ArrayList<Contract> getContracts() {
    return contracts;
  }

  public final void setContracts(final ArrayList<Contract> contracts) {
    this.contracts = contracts;
  }

  public final int getEnergyNeededKW() {
    return energyNeededKW;
  }

  public final void setEnergyNeededKW(final int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }

  public final int getContractCost() {
    return contractCost;
  }

  public final void setContractCost(final int contractCost) {
    this.contractCost = contractCost;
  }

  public final String getProducerStrategy() {
    return producerStrategy;
  }

  public final void setProducerStrategy(final String producerStrategy) {
    this.producerStrategy = producerStrategy;
  }

  /**
   * adauga un contract in lista
   * @param c contractul
   */
  public final void addContract(final Contract c) {
    this.contracts.add(c);
  }

  /**
   * comapra 2 distribuitori dupa id
   * @param o celalalt obiect
   * @return
   */
  @Override
  public int compareTo(final DistributorOutput o) {
    return Integer.compare(this.getId(), o.getId());
  }
}
