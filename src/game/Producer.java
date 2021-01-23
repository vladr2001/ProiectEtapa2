package game;

import constants.Constants;
import entities.EnergyType;
import output.Month;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class Producer implements Comparable {
  private int id;
  private EnergyType energyType;
  private int maxDistributors;
  private double priceKW;
  private int energyPerDistributor;
  private ArrayList<Distributor> distributors;
  private String sortCriteria;
  private ArrayList<Month> monthlyStats;

  public Producer(final int id, final String energyType, final int maxDistributors,
                  final double priceKW, final int energyPerDistributor) {
    this.id = id;
    this.maxDistributors = maxDistributors;
    this.priceKW = priceKW;
    this.energyPerDistributor = energyPerDistributor;
    this.distributors = new ArrayList<>();
    if (energyType.equals(EnergyType.WIND.getLabel())) {
      this.energyType = EnergyType.WIND;
    } else if (energyType.equals(EnergyType.SOLAR.getLabel())) {
      this.energyType = EnergyType.SOLAR;
    } else if (energyType.equals(EnergyType.HYDRO.getLabel())) {
      this.energyType = EnergyType.HYDRO;
    } else if (energyType.equals(EnergyType.COAL.getLabel())) {
      this.energyType = EnergyType.COAL;
    } else if (energyType.equals(EnergyType.NUCLEAR.getLabel())) {
      this.energyType = EnergyType.NUCLEAR;
    }
    this.monthlyStats = new ArrayList<>();
  }

  public final int getId() {
    return id;
  }

  public final void setId(final int id) {
    this.id = id;
  }

  public final EnergyType getEnergyType() {
    return energyType;
  }

  public final void setEnergyType(final EnergyType energyType) {
    this.energyType = energyType;
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

  public final int getEnergyPerDistributor() {
    return energyPerDistributor;
  }

  public final ArrayList<Month> getMonthlyStats() {
    return monthlyStats;
  }

  public final void setMonthlyStats(final ArrayList<Month> monthlyStats) {
    this.monthlyStats = monthlyStats;
  }

  public final void setEnergyPerDistributor(final int energyPerDistributor) {
    this.energyPerDistributor = energyPerDistributor;
  }

  /**
   * scoate un distributor din lista de clienti
   * @param distributor distributorul
   */
  public void removeDistributor(Distributor distributor) {
    this.distributors.removeIf(d -> d.getId() == distributor.getId());
  }

  public final ArrayList<Distributor> getDistributors() {
    return this.distributors;
  }

  public final void setDistributors(final ArrayList<Distributor> distributors) {
    this.distributors = distributors;
  }

  public final String getSortCriteria() {
    return this.sortCriteria;
  }

  public final void setSortCriteria(final String sortCriteria) {
    this.sortCriteria = sortCriteria;
  }

  /**
   * adauga un distributor in lista de clienti
   * @param d distributorul
   */
  public void addDistributor(Distributor d) {
    this.distributors.add(d);
  }

  /**
   * face statusul acelei luni
   * @param round luna respectiva
   */
  public void addMonthlyStat(int round) {
    ArrayList<Integer> ids = new ArrayList<>();
    for (Distributor d : this.getDistributors()) {
      ids.add(d.getId());
    }
    Month m = new Month(round, ids);
    this.monthlyStats.add(m);
  }

  /**
   * functia de comparatie dintre 2 obiecte
   * @param o obiectul cu care compara
   * @return rezultatul comparatiei
   */
  @Override
  public int compareTo(Object o) {
    Producer p = (Producer) o;
    if (this.getSortCriteria().equals(EnergyChoiceStrategyType.GREEN.toString())) {
      if (this.getEnergyType().isRenewable() && !p.getEnergyType().isRenewable()) {
        return -1;
      } else if (!this.getEnergyType().isRenewable() && p.getEnergyType().isRenewable()) {
        return 1;
      } else {
        if (this.getPriceKW() != p.getPriceKW()) {
          return Double.compare(this.getPriceKW(), p.getPriceKW());
        } else {
          return Integer.compare(p.getEnergyPerDistributor(), this.getEnergyPerDistributor());
        }
      }
    } else if (this.getSortCriteria().equals(EnergyChoiceStrategyType.PRICE.toString())) {
      if (this.getPriceKW() != p.getPriceKW()) {
        return Double.compare(this.getPriceKW(), p.getPriceKW());
      }
      return Integer.compare(p.getEnergyPerDistributor(), this.getEnergyPerDistributor());

    } else if (this.getSortCriteria().equals(EnergyChoiceStrategyType.QUANTITY.toString())) {
      return Integer.compare(p.getEnergyPerDistributor(), this.getEnergyPerDistributor());
    } else if (this.getSortCriteria().equals(Constants.RESETLIST)) {
      return Integer.compare(this.getId(), p.getId());
    }
    return 0;
  }
}
