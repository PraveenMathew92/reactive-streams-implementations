import Subscriber.FixedIntegerSubscriber;
import Subscriber.IntegerSubscriber;
import processor.Map;
import publisher.NaturalNumbersPublisher;

public class Implementation {
    public static void main(String[] args) {
        NaturalNumbersPublisher publisher = new NaturalNumbersPublisher();
        Map tableOfThree = new Map(i -> i*3);

        publisher.subscribe(new FixedIntegerSubscriber(5));

        publisher.subscribe(new IntegerSubscriber());

        publisher.subscribe(new FixedIntegerSubscriber(15));

        publisher.subscribe(tableOfThree);
        tableOfThree.subscribe(new FixedIntegerSubscriber(10));
    }
}
