package sample;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.scene.control.Button;
/**
 * Created by mani on 9/10/15.
 */
public class Player implements InventoryInterface {
    private String name;
    private Main.Race race;
    private Color color;
    private Inventory inventory;
    private ArrayList<LandPlot> landOwned;
    private int landCount;
    private int score;

    public Player(String name, Main.Race race, Color color) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.inventory = new Inventory();
        landOwned = new ArrayList<>();
        landCount = 0;
    }

    public String toString() {
        String ret = "Name: " + this.name + "\n" + "Race: " + this.race.toString() + "\n" + "Color: " + this.color.toString();
        return ret;
    }

    public int getScore() {
        calculateScore();
        return score;
    }

    public void calculateScore() {
        score = 1 * getMoneyStash() + 500 * getLandCount() + 1 * (getFood() + getEnergy() + getOre());
    }

    public String getName() {return this.name; }

    public Color getColor() { return this.color; }

    public int getMoneyStash() {return this.inventory.moneyStash; }

    public int getEnergy() {return this.inventory.energy; }

    public int getFood() {return this.inventory.food; }

    public int getOre() {return this.inventory.ore; }

    public int getLandCount() { return this.landCount; }

    public ArrayList<LandPlot> getLandOwned() {
        return this.landOwned;
    }

    public boolean ownsLand(Button p) {
        boolean isOwner = false;
        for (LandPlot l : landOwned) {
            if (l.getButton().equals(p)) isOwner = true;
        }

        return isOwner;
    }

    public LandPlot getLand(Button p) {
        LandPlot land = null;
        for (LandPlot l : landOwned) {
            if (l.getButton().equals(p)) land = l;
        }

        return land;
    }

    public Mule getMule() {
        Mule temp = this.inventory.muleOnPerson;
        this.inventory.muleOnPerson = null;
        return temp;
    }
    public boolean hasMule() { return this.inventory.muleOnPerson != null; }

    public void addLand(LandPlot land) {
        landOwned.add(land);
        landCount++;
    }

    public void removeLand(LandPlot land) {
        landOwned.remove(land);
        landCount--;
    }


    /**
     * Adds money to a player's money stash in their inventory
     * @param valueToAdd How much money to add to player's money stash
     */
    public void addMoney(int valueToAdd) {
        if (valueToAdd < 0) {
            System.out.println("The value is negative.");
        } else {
            inventory.moneyStash += valueToAdd;
        }
    }

    /**
     * Removes money from a player's money stash in their inventory
     * @param valueToSubtract The amount of money being taken out of inventory
     */
    public void subtractMoney(int valueToSubtract) {
        if (valueToSubtract < 0) {
            System.out.println("The value is negative.");
        } else {
            inventory.moneyStash -= valueToSubtract;
        }
    }

    public void addEnergy(int energyAdded) {
        if (energyAdded < 0) {
            System.out.println("Attempt to add negative energy");
        } else {
            inventory.energy += energyAdded;
        }
    }

    public void subtractEnergy(int energySubtract) {
        if (energySubtract < 0) {
            System.out.println("Attempt to subtract negative energy");
        } else {
            inventory.energy -= energySubtract;
        }
    }

    public void addFood(int foodAdded) {
        if (foodAdded < 0) {
            System.out.println("Attempt to add negative food");
        } else {
            inventory.food += foodAdded;
        }
    }

    public void subtractFood(int foodSubtract) {
        if (foodSubtract < 0) {
            System.out.println("Attempt to subtract negative food");
        } else {
            inventory.food -= foodSubtract;
        }
    }

    public void addOre(int oreAdded) {
        if (oreAdded < 0) {
            System.out.println("Attempt to add negative ore.");
        } else {
            inventory.ore += oreAdded;
        }
    }

    public void subtractOre(int oreSubtract) {
        if (oreSubtract < 0) {
            System.out.println("Attempt to subtract negative ore.");
        } else {
            inventory.ore -= oreSubtract;
        }
    }

    public void addCrystite(int crystiteAdded) {
        if (crystiteAdded < 0) {
            System.out.println("Attempt to add negative crystite.");
        } else {
            inventory.crystite += crystiteAdded;
        }
    }

    public void addMule(Mule newMule) {
        if (newMule == null || inventory.muleOnPerson != null) {
            System.out.println("Attempted to add null mule");
        } else {
            inventory.muleOnPerson = newMule;
        }
    }

    public void sellMule() {
        if (inventory.muleOnPerson != null) {
            inventory.muleOnPerson = null;
        } else {
            System.out.println("Error- No mule to sell.");
        }
    }

    public String inventoryToString() {
        String inventoryList;
        if (inventory.muleOnPerson == null) {
            inventoryList = (this.name + "'s Inventory: \n Money: " + inventory.moneyStash + "\n Energy: "
                + inventory.energy + "\n Food: " + inventory.food + "\n Ore: " + inventory.ore + "\n Crystite: " +
                    inventory.crystite + "\n No mule on person.");
        } else {
            inventoryList = (this.name + "'s Inventory: \n Money: " + inventory.moneyStash + "\n Energy: "
                    + inventory.energy + "\n Food: " + inventory.food + "\n Ore: " + inventory.ore + "\n Crystite: " +
                    inventory.crystite + "\n Mule on person.");
        }
        return inventoryList;
    }



//***************************************************************************************************************
    /**
     * Keeps track of all the player's belongings
     */
    private class Inventory {
        private int moneyStash;
        private int energy;
        private int food;
        private int ore;
        private int crystite;
        private Mule muleOnPerson;

        private Inventory() {
            this.moneyStash = 1000;
            this.energy = 4;
            this.food = 8;
            this.ore = 0;
            this.crystite = 0;
            muleOnPerson = null;
        }

    }
}
