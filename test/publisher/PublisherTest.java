package publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

public class PublisherTest extends PublisherVerification<Integer> {
    public PublisherTest() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Integer> createPublisher(long l) {
        return new NaturalNumbersPublisher();
    }

    @Override
    public Publisher<Integer> createFailedPublisher() {
        return null;
    }
}
