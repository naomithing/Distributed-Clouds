package naomi2332244;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import java.io.IOException;
import java.util.Random;

class Main {

    public static void main(String[] args) {

        ActorSystem system = ActorSystem.create();
        ActorRef bankAccRef = system.actorOf(Props.create(BankAccount.class));

        for (int i = 0; i < 10; i++) {
        	int amount = (int) (Math.random() * 2001) - 1000; // Random value between -1000 and 1000
            if (amount > 0) {
                // Deposit
                bankAccRef.tell(new Deposit(amount), ActorRef.noSender());
            } else {
                // Withdrawal
                bankAccRef.tell(new Withdraw(-amount), ActorRef.noSender());
            }
        }

        // Terminate the system after processing all transactions
        system.terminate();
    }

}
