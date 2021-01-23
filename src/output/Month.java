package output;

import java.util.ArrayList;
import java.util.Comparator;

public class Month {
  private int month;
  private ArrayList<Integer> distributorsIds;

  public Month(final int month, final ArrayList<Integer> distributorIds) {
    this.month = month;
    this.distributorsIds = distributorIds;
    this.distributorsIds.sort(Comparator.comparingInt(d -> d));
  }

  public final int getMonth() {
    return month;
  }

  public final void setMonth(final int month) {
    this.month = month;
  }

  public final ArrayList<Integer> getDistributorsIds() {
    return distributorsIds;
  }

  public final void setDistributorsIds(final ArrayList<Integer> distributorsIds) {
    this.distributorsIds = distributorsIds;
  }
}
