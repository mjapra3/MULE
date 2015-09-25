package sample;


/**
 * Created by mani on 9/24/15.
 */
public class GameController {

    public static void nextTurn() {
        if (Controller.currentPlayerTurn == Controller.players.size() - 1) {
            Controller.currentPlayerTurn = 0;
            Controller.roundNum++;
            if (Controller.roundNum == 2) {
                Controller.landSelectionMode = false;
                System.out.println("Land Selection Mode is now over.");
                System.out.println("You will now have to purchase property to claim it!");
            }
        }
        else Controller.currentPlayerTurn++;
    }

    public static boolean buyProperty() {
        Player cur = Controller.players.get(Controller.currentPlayerTurn);
        if (cur.getMoneyStash() - 300 < 0) {
            System.out.println("You do not have enough funds to purchase a property.");
            return false;
        } else {
            cur.subtractMoney(300);
            System.out.println(cur.getName() + " has $" + cur.getMoneyStash() + " leftover.");
            return true;
        }
    }

    public static void landSelectionPhase() {
        System.out.println(Controller.players.get(Controller.currentPlayerTurn).getName() + " please select a plot of land...");
    }
}
