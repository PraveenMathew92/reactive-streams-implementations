package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

public class SingleIntegerPublisherTest extends PublisherVerification<Integer> {
    public SingleIntegerPublisherTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Integer> createPublisher(long l) {
        return new SingleIntegerPublisher(10);
    }

    @Override
    public Publisher<Integer> createFailedPublisher() {
        return new SingleIntegerPublisher(10);
    }
}
