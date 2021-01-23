package output;

import java.util.ArrayList;

public class ProducerOutput {
  private int id;
  private int maxDistributors;
  private double priceKW;
  private String energyType;
  private int energyPerDistributor;
  private ArrayList<Month> monthlyStats;

  public ProducerOutput(final int id, final int maxDistributors, final double priceKW,
                        final String energyType, final int energyPerDistributor,
                        final ArrayList<Month> monthlyStats) {
    this.id = id;
    this.maxDistributors = maxDistributors;
    this.priceKW = priceKW;
    this.energyType = energyType;
    this.energyPerDistributor = energyPerDistributor;
    this.monthlyStats = monthlyStats;
  }

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final int getMaxDistributors() {
    return maxDistributors;
  }

  public final void setMaxDistributors(final int maxDistributors) {
    this.maxDistributors = maxDistributors;
  }

  public final double getPriceKW() {
    return priceKW;
  }

  public final void setPriceKW(final double priceKW) {
    this.priceKW = priceKW;
  }

  public final String getEnergyType() {
    return energyType;
  }

  public final void setEnergyType(final String energyType) {
    this.energyType = energyType;
  }

  public final int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public final void setEnergyPerDistributor(final int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

  public final ArrayList<Month> getMonthlyStats() {
    return monthlyStats;
  }

  public final void setMonthlyStats(final ArrayList<Month> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }
}
