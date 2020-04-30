package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

public class NaturalNumbersPublisher implements Publisher<Integer> {
    private Supplier<IntStream> naturalNumbersSupplier;

    public NaturalNumbersPublisher() {
        naturalNumbersSupplier = () -> IntStream
                .iterate(1, i -> i + 1)
                .limit(10);
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        if(isNull(subscriber)) {
            throw new NullPointerException();
        }
        subscriber.onSubscribe(new Subscription(subscriber));
    }

    class Subscription implements org.reactivestreams.Subscription {
        private final Subscriber subscriber;
        private volatile Iterator iterator;
        private volatile boolean isTerminated;

        Subscription(Subscriber subscriber) {
            this.subscriber = subscriber;
            this.isTerminated = false;
            this.iterator = naturalNumbersSupplier
                    .get()
                    .iterator();
        }

        @Override
        public void request(long elementCount) {
            if (isTerminated) return;

            if(elementCount <= 0) {
                subscriber.onError(new IllegalArgumentException());
                cancel();
            }

            while (elementCount > 0 && !isTerminated){
                if (iterator.hasNext()) {
                    elementCount--;
                    Integer nextElement = (Integer) iterator.next();
                    new Thread(() -> {
                        subscriber.onNext(nextElement);
                    }).start();
                }
                else {
                    subscriber.onComplete();
                    cancel();
                }
            }
        }

        @Override
        public void cancel() {
            if(isTerminated) return;
            System.out.println("Canceled subscription of " + subscriber.getClass().getSimpleName() + " to "
                    + getClass().getDeclaringClass().getSimpleName());
            isTerminated = true;
        }
    }
}
