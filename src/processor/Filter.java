package processor;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.stream.LongStream;

import static java.util.Objects.isNull;

public class Filter implements Processor<Integer, Integer>{
    private Subscription subscription;
    private Subscriber subscriber;
    private Predicate<Integer> predicate;
    private Queue<Integer> filteredQueue = new LinkedList<>();

    public Filter(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(new FilterSubscription());
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer integer) {
        if(predicate.test(integer)) {
            filteredQueue.offer(integer);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        subscription.cancel();
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscription.cancel();
        subscriber.onComplete();
    }

    class FilterSubscription implements org.reactivestreams.Subscription{
        private boolean terminated;

        @Override
        public void request(long l) {
            if(terminated) return;
            LongStream.range(0, l)
            .forEach(i -> {
                while(isNull(filteredQueue.peek()))
                    subscription.request(l - i);
                subscriber.onNext(filteredQueue.poll());
            });
        }

        @Override
        public void cancel() {
            terminated = true;
        }
    }
}
