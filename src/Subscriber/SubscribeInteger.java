package Subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscribeInteger implements Subscriber<Integer>{
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        System.out.println("Subscribed");
        subscription.request(1);
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("Received : " + integer);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("COMPLETED");
        subscription.cancel();
    }
}
