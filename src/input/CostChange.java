package input;

public class CostChange {
  private int id;
  private int infrastructureCost;
  private int productionCost;

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final int getInfrastructureCost() {
    return infrastructureCost;
  }

  public final void setInfrastructureCost(final int infrastructureCost) {
    this.infrastructureCost = infrastructureCost;
  }

  public final int getProductionCost() {
    return productionCost;
  }

  public final void setProductionCost(final int productionCost) {
    this.productionCost = productionCost;
  }
}
