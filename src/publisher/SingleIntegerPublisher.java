package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SingleIntegerPublisher implements Publisher<Integer>{
    private final int value;

    public SingleIntegerPublisher(int value) {
        this.value = value;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new SingleIntegerSubscription(subscriber));
    }

    class SingleIntegerSubscription implements Subscription{
        private final Subscriber subscriber;

        SingleIntegerSubscription(Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        @Override
        public void request(long l) {
            subscriber.onNext(value);
            subscriber.onComplete();
        }

        @Override
        public void cancel() {

        }
    }
}
