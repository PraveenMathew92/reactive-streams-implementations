import Subscriber.SubscribeInteger;
import processor.Map;
import publisher.NaturalNumbersPublisher;
import publisher.SingleIntegerPublisher;

public class Implementation {
    public static void main(String[] args) {
        SingleIntegerPublisher publisher = new SingleIntegerPublisher(5);
        publisher.subscribe(new SubscribeInteger());

        NaturalNumbersPublisher naturalNumbersPublisher = new NaturalNumbersPublisher();
//        naturalNumbersPublisher.subscribe(new SubscribeInteger());

        Map doubleValue = new Map(i -> 2*i);
        naturalNumbersPublisher.subscribe(doubleValue);
        doubleValue.subscribe(new SubscribeInteger());
    }
}
