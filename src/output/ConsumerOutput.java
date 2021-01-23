package output;

public class ConsumerOutput implements Comparable<ConsumerOutput> {
  private int id;
  private boolean isBankrupt;
  private long budget;

  public ConsumerOutput(final int id, final boolean isBankrupt, final long budget) {
    this.id = id;
    this.isBankrupt = isBankrupt;
    this.budget = budget;
  }

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final boolean getIsBankrupt() {
    return isBankrupt;
  }

  public final void setIsBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public final long getBudget() {
    return budget;
  }

  public final void setBudget(final long budget) {
    this.budget = budget;
  }

  /**
   * ii ordoneaza dout id
   * @param o consumatorul cu care il compara
   * @return rezultatul comparatiei
   */
  @Override
  public int compareTo(final ConsumerOutput o) {
    return Integer.compare(this.getId(), o.getId());
  }
}
