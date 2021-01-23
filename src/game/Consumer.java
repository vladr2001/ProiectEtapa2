package game;
import java.util.ArrayList;

public class Consumer implements Player {
  private boolean hasContract;
  private int id;
  private int budget;
  private int monthlyIncome;
  private ArrayList<Long> dues;
  private ArrayList<Distributor> distributorsOwed;
  private int distributorId;
  private int monthsLeft;
  private long price;
  private boolean isBankrupt;

  private static final double MAGICNUMBER = 1.2;

  public Consumer(final boolean hasContract, final int id,
                  final int initialBudget, final int monthlyIncome) {
    this.hasContract = hasContract;
    this.id = id;
    this.budget = initialBudget;
    this.monthlyIncome = monthlyIncome;
    this.dues = new ArrayList<>();
    this.distributorId = -1;
    this.distributorsOwed = new ArrayList<>();
    this.monthsLeft = 0;
    this.price = 0;
    this.isBankrupt = false;
  }

  public final boolean isHasContract() {
    return hasContract;
  }

  public final void setHasContract(final boolean hasContract) {
    this.hasContract = hasContract;
  }

  public final boolean isBankrupt() {
    return isBankrupt;
  }

  public final void setBankrupt(final boolean bankrupt) {
    isBankrupt = bankrupt;
  }

  public final int getId() {
    return this.id;
  }

  public final void setId(final int newId) {
    this.id = newId;
  }

  public final int getBudget() {
    return budget;
  }

  public final void setBudget(final int initialBudget) {
    this.budget = initialBudget;
  }

  public final int getMonthlyIncome() {
    return monthlyIncome;
  }

  public final void setMonthlyIncome(final int monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public final ArrayList<Long> getDues() {
    return dues;
  }

  public final long getPrice() {
    return price;
  }

  public final void setPrice(final long price) {
    this.price = price;
  }

  public final void setDues(final ArrayList<Long> dues) {
    this.dues = dues;
  }

  public final int getDistributorId() {
    return this.distributorId;
  }

  public final void setDistributorId(final int newId) {
    this.distributorId = newId;
  }

  public final ArrayList<Distributor> getDistributorsOwed() {
    return distributorsOwed;
  }

  public final void setDistributorsOwed(final ArrayList<Distributor> distributorsOwed) {
    this.distributorsOwed = distributorsOwed;
  }

  /**
   * adauga un distributor in lista(cea in care ii salveaza
   * pe cei carora le datoreaza bani)
   * @param d noul distributor
   */
  public final void addDistributorOwed(final Distributor d) {
    this.distributorsOwed.add(d);
  }

  /**
   * adauga o suma care trebuie platita in lista
   * @param due suma care trebuie adaugata
   */
  public final void addDues(final long due) {
    this.dues.add(due);
  }

  public final int getMonthsLeft() {
    return monthsLeft;
  }

  public final void setMonthsLeft(final int monthsLeft) {
    this.monthsLeft = monthsLeft;
  }

  /**
   * scoate un distribuitor din lista de datorii
   * @param d distribuitorul care trebuie scos
   */
  public final void removeDistributorOwed(final Distributor d) {
    for (Distributor dis : this.distributorsOwed) {
      if (d.getId() == dis.getId()) {
        this.distributorsOwed.remove(dis);
        break;
      }
    }
  }

  /**
   * adauga bani in bugetul consumatorului
   * @param money suma care trebuie adaugata
   */
  public final void addBudget(final int money) {
    this.budget = this.budget + money;
  }

  /**
   * functia in care consumatorul isi plateste facturile
   * o suprascrisca deoarece ambele clase(Consumers si Distributors)
   * implementeaza aceeasi interfata
   */
  @Override
  public void payDues() {
    long due = 0;
    // Verifica ce are de platit
    if (this.dues.size() == 1) {
      due = this.dues.get(0);
    } else if (this.dues.size() == 2
            && this.distributorsOwed.get(0).equals(this.distributorsOwed.get(1))) {
      due = Math.round(Math.floor(MAGICNUMBER * this.dues.get(0))) + this.dues.get(1);
    } else if (this.dues.size() == 2) {
      due = Math.round(Math.floor(MAGICNUMBER * this.dues.get(0)));
    }
    if (due <= this.getBudget()) {
      // Plateste daca isi permite
      this.budget = this.budget - (int) due;
      if (this.dues.size() == 1) {
        this.distributorsOwed.get(0).addBudget(due);
        this.distributorsOwed = new ArrayList<>();
        this.dues = new ArrayList<>();
      } else if (this.dues.size() == 2
              && this.distributorsOwed.get(0).equals(this.distributorsOwed.get(1))) {
        this.distributorsOwed.get(0).addBudget(Math.round(Math.floor(
                MAGICNUMBER * this.dues.get(0))));
        this.distributorsOwed.get(1).addBudget(this.dues.get(1));
        this.distributorsOwed = new ArrayList<>();
        this.dues = new ArrayList<>();
      } else if (this.dues.size() == 2) {
        this.distributorsOwed.get(0).addBudget(due);
        this.distributorsOwed.remove(0);
        this.dues.remove(0);
      }
    } else {
      // Daca nu isi permite si nu mai poate amana in scoate din joc
      if (this.dues.size() == 2) {
        this.distributorsOwed.get(1).removeClient(this);
        this.setBankrupt(true);
      }
    }
  }
}
