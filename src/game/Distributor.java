package game;

import constants.Constants;
import strategies.EnergyChoiceStrategyType;
import strategies.EnergyStrategy;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;

import java.util.ArrayList;

public class Distributor implements Player, Intermediate {
  private int id;
  private int contractLength;
  private long budget;
  private int infrastructureCost;
  private double productionCost;
  private ArrayList<Consumer> clients;
  private long contractPrice;
  private int lunarCost;
  private int energyNeededKW;
  private String producerStrategy;
  private ArrayList<Producer> producers;
  private EnergyStrategy strategy;


  public static final double MAGICNUMBER = 0.2;

  public Distributor(final int id,
                     final int contractLength,
                     final int initialBudget,
                     final int initialInfrastructureCost,
                     final int energyNeededKW,
                     final String producerStrategy) {
    this.id = id;
    this.contractLength = contractLength;
    this.budget = initialBudget;
    this.infrastructureCost = initialInfrastructureCost;
    this.clients = new ArrayList<>();
    this.contractPrice = 0;
    this.lunarCost = 0;
    this.energyNeededKW = energyNeededKW;
    this.producerStrategy = producerStrategy;
    this.producers = new ArrayList<>();
    if (this.producerStrategy.equals(EnergyChoiceStrategyType.QUANTITY.toString())) {
      this.strategy = new QuantityStrategy();
    } else if (this.producerStrategy.equals(EnergyChoiceStrategyType.PRICE.toString())) {
      this.strategy = new PriceStrategy();
    } else {
      this.strategy = new GreenStrategy();
    }
  }

  public final int getEnergyNeededKW() {
    return energyNeededKW;
  }

  public final void setEnergyNeededKW(final int energyNeededKW) {
    this.energyNeededKW = energyNeededKW;
  }

  public final String getProducerStrategy() {
    return producerStrategy;
  }

  public final void setProducerStrategy(final String producerStrategy) {
    this.producerStrategy = producerStrategy;
  }

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final int getContractLength() {
    return contractLength;
  }

  public final void setContractLength(final int contractLength) {
    this.contractLength = contractLength;
  }

  public final long getBudget() {
    return budget;
  }

  public final void setBudget(final long initialBudget) {
    this.budget = initialBudget;
  }

  public final int getInfrastructureCost() {
    return infrastructureCost;
  }

  public final void setInfrastructureCost(final int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }

  /**
   * calculeaza cata energie are in acest moment un distribuitor
   * @return nivelul de energie
   */
  public final int getEnergyLevel() {
    double en = 0;
    for (Producer p : producers) {
      en += p.getEnergyPerDistributor();
    }
    return (int) en;
  }

  public final EnergyStrategy getStrategy() {
    return strategy;
  }

  /**
   * reaplica strategia de alegere a producatorilor pentru un idstribuitor
   * @param prod lista de producatori din care sa aleaga
   */
  public void getNewProducers(ArrayList<Producer> prod) {
    ArrayList<Producer> aux = this.getStrategy().getSortedDistributorList(prod);
    for (int i = 0; i < aux.size(); i++) {
      if (this.getEnergyLevel() >= this.getEnergyNeededKW()) {
        break;
      }
      if (aux.get(i).getMaxDistributors() > aux.get(i).getDistributors().size()) {
        this.addProducer(aux.get(i));
        aux.get(i).addDistributor(this);
      }
    }
    this.makeProductionCost();
  }

  public final void setStrategy(final EnergyStrategy strategy) {
    this.strategy = strategy;
  }

  public final double getProductionCost() {
    return productionCost;
  }

  public final void setInitialProductionCost(final int initialProductionCost) {
    this.productionCost = initialProductionCost;
  }

  public final ArrayList<Producer> getProducers() {
    return producers;
  }

  public final void setProducers(final ArrayList<Producer> producers) {
    this.producers = producers;
  }

  public final ArrayList<Consumer> getClients() {
    return clients;
  }

  public final void setClients(final ArrayList<Consumer> clients) {
    this.clients = clients;
  }

  /**
   * adauga un consumator in lista de clienti
   * @param c consumatorul
   */
  public final void addClient(final Consumer c) {
    this.clients.add(c);
  }

  /**
   * scoate un consumator din lista de clienti
   * @param c consumatorul
   */
  public final void removeClient(final Consumer c) {
    this.clients.remove(c);
  }

  /**
   * adauga un producator in lista
   * @param producer producatorul
   */
  public final void addProducer(final Producer producer) {
    this.producers.add(producer);
  }

  public final long getContractPrice() {
    return contractPrice;
  }

  public final void setContractPrice(final int contractPrice) {
    this.contractPrice = contractPrice;
  }

  public final void setProductionCost(final int productionCost) {
    this.productionCost = productionCost;
  }

  public final int getLunarCost() {
    return lunarCost;
  }

  public final void setLunarCost(final int lunarCost) {
    this.lunarCost = lunarCost;
  }

  /**
   * calculeaza costul de productie al unui distribuitor
   */
  public void makeProductionCost() {
    this.productionCost = 0;
    for (Producer p : this.producers) {
      this.productionCost += p.getEnergyPerDistributor() * p.getPriceKW();
    }
    this.productionCost = Math.round(Math.floor(this.productionCost / Constants.MAGICNUMBER));
  }

  /**
   * calculeaza pretul contractului
   */
  public void makeContractPrice() {
    long profit = Math.round(Math.floor(MAGICNUMBER * this.productionCost));
    if (this.getClients().size() != 0) {
      this.contractPrice = Math.round(Math.floor(this.infrastructureCost / this.clients.size())
              + this.productionCost + profit);
    } else {
      this.contractPrice = (int) (this.infrastructureCost + this.productionCost + profit);
    }
  }

  /**
   * calculeaza costul lunar
   */
  public void makeLunarCost() {
    this.lunarCost = (int) (this.infrastructureCost + this.productionCost * clients.size());
  }

  /**
   * adauga bani la buget
   * @param money suma care trebuie adaugata
   */
  public void addBudget(final long money) {
    this.budget = this.budget + money;
  }

  /**
   * implementeaza functia din interfata
   * plateste costurile lunare
   */
  @Override
  public void payDues() {
    this.setBudget(this.getBudget() - this.getLunarCost());
  }

  /**
   * face update la distribuitor atunci cand i se modifica unul dintre producatori
   * @param gamestate starea jocului in acel moment
   */
  @Override
  public void update(Gamestate gamestate) {
    for (Producer p : this.getProducers()) {
      p.removeDistributor(this);
    }
    this.setProducers(new ArrayList<>());
  }
}
