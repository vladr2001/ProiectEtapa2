package input;

import java.util.ArrayList;

public class MonthlyUpdate {
  private ArrayList<ConsumerInput> newConsumers;
  private ArrayList<CostChange> distributorChanges;
  private ArrayList<ProducerChange> producerChanges;

  public final ArrayList<ConsumerInput> getNewConsumers() {
    return newConsumers;
  }

  public final void setNewConsumers(final ArrayList<ConsumerInput> newConsumers) {
    this.newConsumers = newConsumers;
  }

  public final ArrayList<CostChange> getDistributorChanges() {
    return distributorChanges;
  }

  public final void setDistributorChanges(final ArrayList<CostChange> distributorChanges) {
    this.distributorChanges = distributorChanges;
  }

  public final ArrayList<ProducerChange> getProducerChanges() {
    return producerChanges;
  }

  public final void setProducerChanges(final ArrayList<ProducerChange> producerChanges) {
    this.producerChanges = producerChanges;
  }
}
