import Subscriber.SubscribeInteger;
import processor.Filter;
import processor.Map;
import publisher.NaturalNumbersPublisher;
import publisher.SingleIntegerPublisher;

public class Implementation {
    public static void main(String[] args) {
//        SingleIntegerPublisher publisher = new SingleIntegerPublisher(5);
//        publisher.subscribe(new SubscribeInteger());

        NaturalNumbersPublisher naturalNumbersPublisher = new NaturalNumbersPublisher();
        Map doubleValue = new Map(i -> i*2);


        naturalNumbersPublisher.subscribe(doubleValue);
        doubleValue.subscribe(new SubscribeInteger());

//        naturalNumbersPublisher.subscribe(new SubscribeInteger());
    }
}
