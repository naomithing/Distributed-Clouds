package naomi2332244;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;


public class BankAccount extends AbstractActor {
	int balance=100;
    public static Props props() {
        return Props.create(BankAccount.class, BankAccount::new);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Deposit.class, this::onDeposit)
                .match(Withdraw.class, this::onWithdraw)
                .build();
    }

    private void onDeposit(Deposit msg) {
        System.out.println("Account received deposit of : "+ msg.amount + " from " + getSender());
        this.balance+=msg.amount;
    }

    private void onWithdraw(Withdraw msg) {
        System.out.println("Account received withdraw of: "+ msg.amount + " from " + getSender());
        this.balance-=msg.amount;

    }
}