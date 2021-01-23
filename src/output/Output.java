package output;

import java.util.ArrayList;

public class Output {
  private ArrayList<ConsumerOutput> consumers;
  private ArrayList<DistributorOutput> distributors;
  private ArrayList<ProducerOutput> energyProducers;

  public Output() {
    this.consumers = new ArrayList<>();
    this.distributors = new ArrayList<>();
    this.energyProducers = new ArrayList<>();
  }

  public final ArrayList<ConsumerOutput> getConsumers() {
    return consumers;
  }

  public final void setConsumers(final ArrayList<ConsumerOutput> consumers) {
    this.consumers = consumers;
  }

  public final ArrayList<DistributorOutput> getDistributors() {
    return distributors;
  }

  public final void setDistributors(final ArrayList<DistributorOutput> distributors) {
    this.distributors = distributors;
  }

  public final ArrayList<ProducerOutput> getEnergyProducers() {
    return energyProducers;
  }

  public final void setEnergyProducers(final ArrayList<ProducerOutput> energyProducers) {
    this.energyProducers = energyProducers;
  }

  /**
   * adauga un consumator in lista
   * @param c consumatorul
   */
  public final void addConsumer(final ConsumerOutput c) {
    this.consumers.add(c);
  }

  /**
   * adauga un distribuitor in lista
   * @param d distribuitorul
   */
  public final void addDistributor(final DistributorOutput d) {
    this.distributors.add(d);
  }

  /**
   * adauga un producator in lista
   * @param p producatorul
   */
  public final void addProducer(final ProducerOutput p) {
    this.energyProducers.add(p);
  }
}
