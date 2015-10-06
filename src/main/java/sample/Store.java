package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * Created by Alexandra Link on 10/1/15.
 */
public class Store {

    //Stock indexing:
    //Food(0); Energy(1); Smithore(2); Crystite(3); Mule(4)
    public static int[] stock = {16, 16, 0, 0, 25};
    // Standard/Tournament stock = {8, 8, 8, 0, 14};

    public static int[] prices = {30, 25, 50, 100, 100};

    public Store() {

    }

    //********************************************** BUY FROM STORE ****************************************************

    public void buyFood(int numWanted) {
        if (verifyAvailability(0, numWanted) && verifyFunds(0, numWanted)) {
            (Controller.players.get(Controller.currentPlayerTurn)).addFood(numWanted);
            (Controller.players.get(Controller.currentPlayerTurn)).subtractMoney(30 * numWanted);
            stock[0] = stock[0] - numWanted;
        } else {
            System.out.println("Error purchasing food.");
        }
    }

    public void buyEnergy(int numWanted) {
        if (verifyAvailability(1, numWanted) && verifyFunds(1, numWanted)) {
            (Controller.players.get(Controller.currentPlayerTurn)).addEnergy(numWanted);
            (Controller.players.get(Controller.currentPlayerTurn)).subtractMoney(25 * numWanted);
            stock[1] = stock[1] - numWanted;
        } else {
            System.out.println("Error purchasing energy.");
        }
    }

    public void buyOre(int numWanted) {
        if (verifyAvailability(2, numWanted) && verifyFunds(2, numWanted)) {
            (Controller.players.get(Controller.currentPlayerTurn)).addEnergy(numWanted);
            (Controller.players.get(Controller.currentPlayerTurn)).subtractMoney(50 * numWanted);
            stock[2] = stock[2] - numWanted;
        } else {
            System.out.println("Error purchasing ore.");
        }
    }

    public void buyCrystite(int numWanted) {
        if (verifyAvailability(3, numWanted) && verifyFunds(3, numWanted)) {
            (Controller.players.get(Controller.currentPlayerTurn)).addCrystite(numWanted);
            (Controller.players.get(Controller.currentPlayerTurn)).subtractMoney(50 * numWanted);
            stock[3] = stock[3] - numWanted;
        } else {
            System.out.println("Error purchasing ore.");
        }
    }

    public  void buyMule(Mule.Configuration type) {
        if (verifyAvailability(4, 1)) {
            int price;
            if (type == Mule.Configuration.FOOD) {
                price = 125;
            } else if (type == Mule.Configuration.ENERGY) {
                price = 150;
            } else if (type == Mule.Configuration.ORE) {
                price = 175;
            } else {
                price = 200;
            }
            if ((Controller.players.get(Controller.currentPlayerTurn)).getMoneyStash() >= price &&
                    (Controller.players.get(Controller.currentPlayerTurn).getMule() == null)) {
                Controller.players.get(Controller.currentPlayerTurn).addMule(new Mule(type));
                Controller.players.get(Controller.currentPlayerTurn).subtractMoney(price);
                stock[4]--;
                System.out.println("Mule purchase was successfull!");
            } else {
                System.out.println("Not enough money to buy this mule");
            }
        } else {
            System.out.println("Out of stock :(");
        }

    }

    //********************************************* SELL BACK TO STORE *************************************************

    public void sellFood(int numSold) {
        if (Controller.players.get(Controller.currentPlayerTurn).getFood() >= numSold) {
            stock[0] = stock[0] + numSold;
            Controller.players.get(Controller.currentPlayerTurn).addMoney(30 * numSold);
            Controller.players.get(Controller.currentPlayerTurn).subtractFood(numSold);
        } else {
            System.out.println("You do not have that much food to sell.");
        }
    }

    public void sellEnergy(int numSold) {
        if (Controller.players.get(Controller.currentPlayerTurn).getEnergy() >= numSold) {
            stock[1] = stock[1] + numSold;
            Controller.players.get(Controller.currentPlayerTurn).addMoney(25 * numSold);
            Controller.players.get(Controller.currentPlayerTurn).subtractEnergy(numSold);
        } else {
            System.out.println("You do not have that much energy to sell.");
        }
    }

    public void sellOre(int numSold) {
        if (Controller.players.get(Controller.currentPlayerTurn).getOre() >= numSold) {
            stock[2] = stock[2] + numSold;
            Controller.players.get(Controller.currentPlayerTurn).addMoney(50 * numSold);
            Controller.players.get(Controller.currentPlayerTurn).subtractOre(numSold);
        } else {
            System.out.println("You do not have that much ore to sell.");
        }
    }

    public void sellCrystite(int numSold) {
        if (Controller.players.get(Controller.currentPlayerTurn).getOre() >= numSold) {
            stock[3] = stock[3] + numSold;
            Controller.players.get(Controller.currentPlayerTurn).addMoney(100 * numSold);
            Controller.players.get(Controller.currentPlayerTurn).subtractOre(numSold);
        } else {
            System.out.println("You do not have that much crystite to sell.");
        }
    }

    public void sellMule() {
        Player curr = Controller.players.get(Controller.currentPlayerTurn);
        if (curr.getMule() != null) {
            Mule currMule = curr.getMule();
            int sellback;
            if (currMule.getType() == Mule.Configuration.ENERGY) {
                sellback = 150;
            } else if (currMule.getType() == Mule.Configuration.FOOD) {
                sellback = 125;
            } else if (currMule.getType() == Mule.Configuration.ORE) {
                sellback = 150;
            } else {
                sellback = 200;
            }
            curr.addMoney(sellback);
            curr.sellMule();
            stock[4]++;
        } else {
            System.out.println("You don't have a mule to sell.");
        }
    }

    //***************************************** Private Helper Methods ************************************************

    private boolean verifyAvailability(int stockIndex, int numPurchased) {
        return (stock[stockIndex] >= numPurchased);
    }

    private boolean verifyFunds(int moneyIndex, int numPurchased) {
        int fundsNeeded = prices[moneyIndex] * numPurchased;
        Player curr = Controller.players.get(Controller.currentPlayerTurn);
        return (curr.getMoneyStash()) >= fundsNeeded;
    }
}