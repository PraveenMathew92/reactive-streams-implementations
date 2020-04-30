package Subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Objects;

public class IntegerSubscriber implements Subscriber<Integer>{
    private Subscription subscription;

    public IntegerSubscriber() {
        this.subscription = null;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        if(Objects.isNull(subscription)) {
            throw new NullPointerException();
        }
        if(Objects.nonNull(this.subscription)) {
            subscription.cancel();
            return;
        }
        this.subscription = subscription;
        System.out.println("Subscribed " + getClass().getSimpleName() + " with a subscription to "
                + subscription.getClass().getEnclosingClass().getSimpleName());
        subscription.request(1);
    }

    @Override
    public void onNext(Integer integer) {
        if(Objects.isNull(integer)) {
            throw new NullPointerException();
        }

        subscription.request(1);

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
