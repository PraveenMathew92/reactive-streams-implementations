import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SubscriptionImpl implements Subscription {
    /*
            can be unicast and multicast
     */
    private final Publisher publisher;
    private final Subscriber subscriber;

    SubscriptionImpl(Publisher publisher, Subscriber subscriber) {
        this.publisher = publisher;
        this.subscriber = subscriber;
    }

    @Override
    public void request(long elementCount) {
        //called from onNext and Subscription of Subscriber
    }

    @Override
    public void cancel() {
        //publisher should drop references to subscriber
    }
}
