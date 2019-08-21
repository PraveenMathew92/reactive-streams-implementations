package processor;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.function.Function;

public class Map implements Processor<Integer, Integer>{
    private Subscription subscription;
    private Subscriber subscriber;
    private Function<Integer, Integer> mapFunction;

    public Map(Function<Integer, Integer> mapFunction) {
        this.mapFunction = mapFunction;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer integer) {
        subscriber.onNext(mapFunction.apply(integer));
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
