package input;

public class ProducerChange {
  private int id;
  private int energyPerDistributor;

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public final void setEnergyPerDistributor(final int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }
}
