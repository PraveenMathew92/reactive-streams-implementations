package Subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriberImpl implements Subscriber {
    /*
            signal demand -> supscription.request()
            decide when and how many elements

            cancel subscriptions no longer needed
     */
    private static final int BUFFER_SIZE = 128;
    private boolean terminalState;

    public SubscriberImpl() {
        this.terminalState = false;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        // cancel active subscription

        subscription.request(BUFFER_SIZE);
    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable throwable) {
        //not call publisher or subscription
        //cancel subscription

        terminalState = true;
    }

    @Override
    public void onComplete() {
        //not call publisher or subscription
        //cancel subscription


        terminalState = true;
    }
}
