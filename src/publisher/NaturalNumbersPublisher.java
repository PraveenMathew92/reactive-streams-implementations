package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

public class NaturalNumbersPublisher implements Publisher<Integer> {
    private Supplier<IntStream> supplier;

    public NaturalNumbersPublisher() {
        supplier = () -> IntStream.iterate(1, i -> i + 1)
        .limit(10);
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        if(isNull(subscriber)) {
            throw new NullPointerException();
        }
        subscriber.onSubscribe(new Subscription() {
            private boolean isTerminated = false;
            private Iterator<Integer> iterator = supplier.get()
                    .iterator();

            @Override
            public void request(long n) {
                if(isTerminated) return;

                if(n <= 0) {
                    subscriber.onError(new IllegalArgumentException());
                    cancel();
                }
            }

            @Override
            public void cancel() {
                if(isTerminated) return;

                System.out.println("Canceled subscription of " + subscriber.getClass().getSimpleName() + " to "
                        + this.getClass().getEnclosingClass().getSimpleName());
                isTerminated = true;
            }
        });
    }
}
