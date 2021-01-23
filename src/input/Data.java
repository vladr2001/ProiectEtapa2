package input;

import java.util.ArrayList;

public class Data {
  private ArrayList<ConsumerInput> consumers;
  private ArrayList<DistributorInput> distributors;
  private ArrayList<ProducerInput> producers;

  public final ArrayList<ConsumerInput> getConsumers() {
    return consumers;
  }

  public final void setConsumers(final ArrayList<ConsumerInput> consumers) {
    this.consumers = consumers;
  }

  public final ArrayList<DistributorInput> getDistributors() {
    return distributors;
  }

  public final void setDistributors(final ArrayList<DistributorInput> distributors) {
    this.distributors = distributors;
  }

  public final ArrayList<ProducerInput> getProducers() {
    return producers;
  }

  public final void setProducers(final ArrayList<ProducerInput> producers) {
    this.producers = producers;
  }
}
