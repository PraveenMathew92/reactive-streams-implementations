package Subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscribeInteger implements Subscriber<Integer>{
    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
    }

    @Override
    public void onNext(Integer integer) {
        System.out.println("Received : " + integer);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("COMPLETED");
    }
}
