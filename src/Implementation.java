import Subscriber.SubscribeInteger;
import processor.Filter;
import processor.Map;
import publisher.NaturalNumbersPublisher;
import publisher.SingleIntegerPublisher;

public class Implementation {
    public static void main(String[] args) {
        SingleIntegerPublisher publisher = new SingleIntegerPublisher(5);
        publisher.subscribe(new SubscribeInteger());

        NaturalNumbersPublisher naturalNumbersPublisher = new NaturalNumbersPublisher();

        Filter multiplesOfFour = new Filter(i -> i % 4 == 0);

        naturalNumbersPublisher.subscribe(multiplesOfFour);
        multiplesOfFour.subscribe(new SubscribeInteger());
    }
}
