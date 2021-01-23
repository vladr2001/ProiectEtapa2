package output;

public class Contract implements Comparable<Contract> {
  private int consumerId;
  private long price;
  private int remainedContractMonths;

  public Contract(final int consumerId, final long price, final int remainedContractMonths) {
    this.consumerId = consumerId;
    this.price = price;
    this.remainedContractMonths = remainedContractMonths;
  }

  public final int getConsumerId() {
    return consumerId;
  }

  public final void setConsumerId(final int consumerId) {
    this.consumerId = consumerId;
  }

  public final long getPrice() {
    return price;
  }

  public final void setPrice(final long price) {
    this.price = price;
  }

  public final int getRemainedContractMonths() {
    return remainedContractMonths;
  }

  public final void setRemainedContractMonths(final int remainedContractMonths) {
    this.remainedContractMonths = remainedContractMonths;
  }

  /**
   * compara 2 obiecte
   * @param o celalalt obiect
   * @return rezultatul comparatiei
   */
  @Override
  public int compareTo(final Contract o) {
    return Integer.compare(this.getConsumerId(), o.getConsumerId());
  }
}
