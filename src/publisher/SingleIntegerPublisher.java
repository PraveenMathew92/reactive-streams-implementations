package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class SingleIntegerPublisher implements Publisher<Integer>{
    private final int value;

    public SingleIntegerPublisher(int value) {
        this.value = value;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onNext(value);
        subscriber.onComplete();
    }
}
