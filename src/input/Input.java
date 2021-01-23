package input;

import java.util.ArrayList;

public class Input {
  private int numberOfTurns;
  private Data initialData;
  private ArrayList<MonthlyUpdate> monthlyUpdates;

  public final int getNumberOfTurns() {
    return numberOfTurns;
  }

  public final void setNumberOfTurns(final int numberOfTurns) {
    this.numberOfTurns = numberOfTurns;
  }

  public final Data getInitialData() {
    return initialData;
  }

  public final void setInitialData(final Data initialData) {
    this.initialData = initialData;
  }

  public final ArrayList<MonthlyUpdate> getMonthlyUpdates() {
    return monthlyUpdates;
  }

  public final void setMonthlyUpdates(final ArrayList<MonthlyUpdate> monthlyUpdates) {
    this.monthlyUpdates = monthlyUpdates;
  }
}
