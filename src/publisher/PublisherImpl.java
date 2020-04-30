package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/*
public class PublisherImpl implements Publisher {
            dont start without subscribe() -> onSubscribe() -> initialization logic can be executed by the Subscriber

            can invoke onNext of Subscriber.
            onNext on subscriber <= Subscription.elementCount
            end with onComplete and onError

            fail -> onError

            onComplete|onError -> Subscriber's Subscription -> cancel()

            stop signalling after subscription cancelled


    @Override
    public void subscribe(Subscriber subscriber) {
        Subscription subscription = new Subscription.SubscriptionImpl(this, subscriber);
        subscriber.onSubscribe(subscription);

    }


}
*/
