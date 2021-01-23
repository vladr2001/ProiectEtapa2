package game;

import constants.Constants;
import input.ConsumerInput;
import input.CostChange;
import input.DistributorInput;
import input.Input;
import input.ProducerChange;
import input.ProducerInput;
import output.ConsumerOutput;
import output.Contract;
import output.DistributorOutput;
import output.Output;
import output.ProducerOutput;
import strategies.EnergyStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class Gamestate {
  private static Gamestate game = null;

  private int numberOfTurns;
  private ArrayList<Consumer> consumers;
  private ArrayList<Distributor> distributors;
  private ArrayList<Consumer> bankruptConsumers;
  private ArrayList<Distributor> bankruptDistributors;
  private ArrayList<Producer> producers;

  private Gamestate() {
    this.distributors = new ArrayList<>();
    this.consumers = new ArrayList<>();
    this.bankruptConsumers = new ArrayList<>();
    this.bankruptDistributors = new ArrayList<>();
    this.producers = new ArrayList<>();
  }

  /**
   * metoda getInstance clasica a unui singleton
   * @return obiectul
   */
  public static Gamestate getInstance() {
    if (game == null) {
      game = new Gamestate();
    }
    return game;
  }

  /**
   * reseteaza singleton-ul
   */
  public void reset() {
    this.distributors.clear();
    this.consumers.clear();
    this.bankruptConsumers.clear();
    this.bankruptDistributors.clear();
    this.producers.clear();
  }

  public int getNumberOfTurns() {
    return numberOfTurns;
  }

  public void setNumberOfTurns(final int numberOfTurns) {
    this.numberOfTurns = numberOfTurns;
  }

  public ArrayList<Consumer> getConsumers() {
    return consumers;
  }

  public void setConsumers(final ArrayList<Consumer> consumers) {
    this.consumers = consumers;
  }

  public ArrayList<Distributor> getDistributors() {
    return distributors;
  }

  public void setDistributors(final ArrayList<Distributor> distributors) {
    this.distributors = distributors;
  }

  /**
   * adauga un consumator in lista
   * @param consumer consumatorul
   */
  public void addConsumer(final Consumer consumer) {
    this.consumers.add(consumer);
  }

  /**
   * adauga un producer in lista
   * @param producer producatorul
   */
  public void addProducer(final Producer producer) {
    this.producers.add(producer);
  }

  public ArrayList<Producer> getProducers() {
    return producers;
  }

  public void setProducers(ArrayList<Producer> producers) {
    this.producers = producers;
  }

  /**
   * scoate un producator din lista
   * @param producer producatorul
   */
  public void removeProducer(final Producer producer) {
    this.producers.removeIf(p -> p.getId() == producer.getId());
  }

  public ArrayList<Consumer> getBankruptConsumers() {
    return bankruptConsumers;
  }

  public void setBankruptConsumers(final ArrayList<Consumer> bankruptConsumers) {
    this.bankruptConsumers = bankruptConsumers;
  }

  public ArrayList<Distributor> getBankruptDistributors() {
    return bankruptDistributors;
  }

  public void setBankruptDistributors(final ArrayList<Distributor> bankruptDistributors) {
    this.bankruptDistributors = bankruptDistributors;
  }

  /**
   * scoate un consumator din lista
   * @param consumer consumatorul
   */
  public void removeConsumer(final Consumer consumer) {
    for (int i = 0; i < this.consumers.size(); i++) {
      if (consumer.getId() == this.consumers.get(i).getId()) {
        this.consumers.remove(i);
        break;
      }
    }
  }

  /**
   * adauga un distributor in lista
   * @param distributor distributorul
   */
  public void addDistributor(final Distributor distributor) {
    this.distributors.add(distributor);
  }

  /**
   * scoate un distributor din lista
   * @param distributor distributorul
   */
  public void removeDistributor(final Distributor distributor) {
    for (int i = 0; i < this.distributors.size(); i++) {
      if (this.distributors.get(i).getId() == distributor.getId()) {
        this.distributors.remove(i);
        break;
      }
    }
  }

  /**
   * joaca runda initiala
   * @param input input-ul
   */
  public void playInitialRound(final Input input) {
    // Ia datele initiale
    ConsumerFactory factory = new ConsumerFactory();
    this.setNumberOfTurns(input.getNumberOfTurns());
    for (ConsumerInput ci : input.getInitialData().getConsumers()) {
      Consumer consumer = (Consumer) factory.createPlayer(
              false, ci.getId(), ci.getInitialBudget(),
              ci.getMonthlyIncome());
      this.addConsumer(consumer);
    }

    for (DistributorInput di : input.getInitialData().getDistributors()) {
      Distributor distributor = new Distributor(di.getId(), di.getContractLength(),
              di.getInitialBudget(), di.getInitialInfrastructureCost(),
              di.getEnergyNeededKW(), di.getProducerStrategy());
      this.addDistributor(distributor);
    }

    for (ProducerInput pi : input.getInitialData().getProducers()) {
      Producer producer = new Producer(pi.getId(), pi.getEnergyType(),
              pi.getMaxDistributors(), pi.getPriceKW(),
              pi.getEnergyPerDistributor());
      this.addProducer(producer);
    }

    for (Distributor d : this.distributors) {
      ArrayList<Producer> aux = new ArrayList<>();
      EnergyStrategy strategy = d.getStrategy();
      aux = strategy.getSortedDistributorList(this.getProducers());
      for (int i = 0; i < aux.size(); i++) {
        if (d.getEnergyLevel() >= d.getEnergyNeededKW()) {
          break;
        }
        if (aux.get(i).getMaxDistributors() > aux.get(i).getDistributors().size()) {
          d.addProducer(aux.get(i));
          aux.get(i).addDistributor(d);
        }
      }
      d.makeProductionCost();
      d.makeContractPrice();
      d.makeLunarCost();
    }

    this.resetProducerList();
  }

  /**
   * aduce lista de producatori la ordinea initiala
   */
  public void resetProducerList() {
    for (Producer p : this.producers) {
      p.setSortCriteria(Constants.RESETLIST);
    }

    Collections.sort(this.producers);
  }

  /**
   * preia update-ul pentru fiecare luna
   * @param round runda
   * @param input input-ul
   */
  public void update(final int round, final Input input) {
    ConsumerFactory factory = new ConsumerFactory();
    if (round != 0) {
      // Face update daca nu e prima runda
      for (ConsumerInput ci : input.getMonthlyUpdates().get(round - 1).getNewConsumers()) {
        Consumer consumer = (Consumer) factory.createPlayer(false, ci.getId(),
                ci.getInitialBudget(), ci.getMonthlyIncome());
        this.addConsumer(consumer);
      }

      for (CostChange cc : input.getMonthlyUpdates().get(round - 1).getDistributorChanges()) {
        for (Distributor d : this.distributors) {
          if (d.getId() == cc.getId()) {
            d.setInfrastructureCost(cc.getInfrastructureCost());
            break;
          }
        }
      }
    }
  }

  /**
   * schimba datele unui producator
   * @param round runda
   * @param input inout-ul
   */
  public void updateProducer(final int round, final Input input) {
    ArrayList<Distributor> updatedDistributors = new ArrayList<>();
    for (ProducerChange pc : input.getMonthlyUpdates().get(round - 1).getProducerChanges()) {
      for (Producer p : this.producers) {
        if (p.getId() == pc.getId()) {
          p.setEnergyPerDistributor(pc.getEnergyPerDistributor());
          updatedDistributors.addAll(p.getDistributors());
        }
      }
    }
    updatedDistributors.sort(Comparator.comparingInt(Distributor::getId));

    for (Distributor d : updatedDistributors) {
      d.update(this);
      d.getNewProducers(this.producers);
    }
    this.resetProducerList();
  }

  /**
   * metoda care joaca fiecare runda in parte
   * @param round runda curenta
   * @param input input-ul
   * @return 0 daca continua; -1 daca se opreste
   */
  public Integer playRound(final int round, final Input input) {
    if (this.distributors.size() == 0) {
      // Daca nu mai sunt distribuitori opreste jocul
      return -1;
    }

    this.update(round, input);

    for (Distributor d : this.distributors) {
      // Calculeza preturile
      if (d.getProducers().size() == 0) {
        d.getNewProducers(this.producers);
        this.resetProducerList();
      }
      d.makeContractPrice();
    }

    for (int i = 0; i < this.consumers.size(); i++) {
      // Scoate clientii carora le-au expirat contractele
      if (this.consumers.get(i).getMonthsLeft() == 0) {
        int dId = this.consumers.get(i).getDistributorId();
        this.consumers.get(i).setHasContract(false);
        this.consumers.get(i).setDistributorId(-1);
        for (Distributor d : distributors) {
          if (d.getId() == dId) {
            d.removeClient(this.consumers.get(i));
          }
        }
      }
    }

    for (Consumer c : this.consumers) {
      // Adauga veniturile lunare
      c.addBudget(c.getMonthlyIncome());
    }

    for (Consumer c :  this.consumers) {
      // Consumatorii isi cauta contracte noi daca e nevoie
      if (!c.isHasContract()) {
        long minPrice = this.distributors.get(0).getContractPrice();
        Distributor minDistributor = this.distributors.get(0);
        for (Distributor d : this.distributors) {
          if (d.getContractPrice() < minPrice) {
            minPrice = d.getContractPrice();
            minDistributor = d;
          }
        }
        if (c.getDues().size() < 2) {
          c.addDues(minPrice);
          c.addDistributorOwed(minDistributor);
          c.setDistributorId(minDistributor.getId());
          c.setHasContract(true);
          c.setMonthsLeft(minDistributor.getContractLength());
          minDistributor.addClient(c);
          c.setPrice(minPrice);
        }
      } else {
        for (Distributor d : this.distributors) {
          if (d.getId() == c.getDistributorId()) {
            c.addDues(c.getPrice());
            c.addDistributorOwed(d);
          }
        }
      }
    }

    for (Distributor d : this.distributors) {
      // Calculeaza costurile lunare
      d.makeLunarCost();
    }

    for (Distributor d : this.distributors) {
      // Plateste costurile lunare
      d.payDues();
    }

    for (Consumer c : this.consumers) {
      // Platesc costurile lunare si scade o luna
      c.payDues();
      c.setMonthsLeft(c.getMonthsLeft() - 1);
    }

    // Scoate distribuitorii si consumatorii bankrupt din joc
    for (int i = 0; i < this.distributors.size(); i++) {
      if (this.distributors.get(i).getBudget() < 0) {
        this.bankruptDistributors.add(this.distributors.get(i));
        for (Producer p : this.distributors.get(i).getProducers()) {
          p.removeDistributor(this.distributors.get(i));
        }
        this.distributors.remove(i);
        i--;
      }
    }

    for (int i = 0; i < this.consumers.size(); i++) {
      if (this.consumers.get(i).isBankrupt()) {
        this.bankruptConsumers.add(this.consumers.get(i));
        this.consumers.remove(i);
        i--;
      }
    }
    if (round != 0) {
      this.updateProducer(round, input);
      for (Distributor d : this.distributors) {
        if (d.getProducers().size() == 0) {
          d.getNewProducers(this.producers);
          this.resetProducerList();
        }
      }
      for (Producer p : this.producers) {
        p.addMonthlyStat(round);
      }
    }
    return 0;
  }

  /**
   * creaza output-ul
   * @return output
   */
  public Output makeOutput() {
    Output out = new Output();
    for (Consumer c : this.getConsumers()) {
      ConsumerOutput co = new ConsumerOutput(c.getId(), false, c.getBudget());
      out.addConsumer(co);
    }

    for (Consumer c : this.getBankruptConsumers()) {
      ConsumerOutput co = new ConsumerOutput(c.getId(), true, c.getBudget());
      out.addConsumer(co);
    }

    for (Distributor d : this.getDistributors()) {
      DistributorOutput disout = new DistributorOutput(d.getId(),
              d.getEnergyNeededKW(), (int) d.getContractPrice(),
              d.getBudget(), d.getProducerStrategy(), false);
      for (Consumer c : d.getClients()) {
        Contract cont = new Contract(c.getId(), c.getPrice(), c.getMonthsLeft());
        disout.addContract(cont);
      }
      out.addDistributor(disout);
    }

    for (Distributor d : this.getBankruptDistributors()) {
      DistributorOutput disout = new DistributorOutput(d.getId(), d.getEnergyNeededKW(),
              (int) d.getContractPrice(),
              d.getBudget(), d.getProducerStrategy(), true);
      out.addDistributor(disout);
    }
    ArrayList<ConsumerOutput> outConsumers = out.getConsumers();
    ArrayList<DistributorOutput> outDistributors = out.getDistributors();
    Collections.sort(outConsumers);
    Collections.sort(outDistributors);
    out.setConsumers(outConsumers);
    out.setDistributors(outDistributors);

    for (Producer p : this.producers) {
      ProducerOutput po = new ProducerOutput(p.getId(),
              p.getMaxDistributors(), p.getPriceKW(), p.getEnergyType().getLabel(),
              p.getEnergyPerDistributor(), p.getMonthlyStats());
      out.addProducer(po);
    }
    return out;
  }
}
