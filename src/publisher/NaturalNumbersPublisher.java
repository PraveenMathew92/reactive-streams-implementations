package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class NaturalNumbersPublisher implements Publisher<Integer> {
    private final Supplier<IntStream> naturalNumbersSupplier = () -> IntStream.iterate(1, i -> i + 1);

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new Subscription(subscriber));
    }

    class Subscription implements org.reactivestreams.Subscription {
        private final Subscriber subscriber;
        private final Iterator iterator;

        Subscription(Subscriber subscriber) {
            this.subscriber = subscriber;
            this.iterator = naturalNumbersSupplier.get().iterator();
        }

        @Override
        public void request(long elementCount) {
            LongStream.range(0, elementCount)
                    .forEach(x -> subscriber.onNext(iterator.next()));
        }

        @Override
        public void cancel() {

        }
    }
}
