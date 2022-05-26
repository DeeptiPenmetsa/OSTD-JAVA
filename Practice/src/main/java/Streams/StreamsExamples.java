package Streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsExamples {

    public static void main(String[] args){
        //ExamplesOnStreams1();
        //ExamplesOnStreams2();

        Blog blog1 = new Blog("1", Arrays.asList("Nice", "Very Nice"));
        Blog blog2 = new Blog("2",  Arrays.asList("Disappointing", "Ok", "Could be better"));

        List<Blog> blogs = new ArrayList<>();
        blogs.add(blog1);
        blogs.add(blog2);

        Map<String, List<List<String>>> authorcomments = blogs.stream()
                .collect(Collectors.groupingBy(Blog::getAuthorName,
                Collectors.mapping(Blog::getComments, Collectors.toList())));
        authorcomments.forEach((s, c) -> System.out.format("author %s: comments %s: ", s, c));


    }

    private static void ExamplesOnStreams2() {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        Map<Integer, List<Person>> personsByAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));

        //personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
        IntSummaryStatistics ageSummary =
                persons.stream().collect(Collectors.summarizingInt(p -> p.age));

        String phrase = persons.stream().filter(p -> p.getAge() >= 18).map(p -> p.name)
                .collect(Collectors.joining(" and ", "In germany ",
                        " are of legal age."));
        //System.out.format(phrase);

        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(p -> p.age, p -> p.name,
                        (name1, name2) -> name1 + ";" + name2));

        System.out.println(map);

        Collector<Person, StringJoiner, String> personCollector =
                Collector.of(() -> new StringJoiner("|"),
                (j, p) -> j.add(p.name.toUpperCase()),
                        (j1, j2) -> j1.merge(j2), StringJoiner::toString);

        String person = persons.stream().collect(personCollector);
        // System.out.format(person);

        List<Foo> foos = new ArrayList<>();
        IntStream.range(1,4).forEach(i -> foos.add(new Foo("Foo" + i)));
        foos.forEach(f -> IntStream.range(1,4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));

        //foos.stream().flatMap(f -> f.bars.stream()).forEach(b -> System.out.format(b.name));

        /*IntStream.range(1, 4)
    .mapToObj(i -> new Foo("Foo" + i))
    .peek(f -> IntStream.range(1, 4)
        .mapToObj(i -> new Bar("Bar" + i + " <- " f.name))
        .forEach(f.bars::add))
    .flatMap(f -> f.bars.stream())
    .forEach(b -> System.out.println(b.name));*/

        persons.stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);

        Person result =
                persons.stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });

        //System.out.format("name=%s; age=%s", result.name, result.age);

        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        //System.out.println(ageSum);

        persons
                .parallelStream()
                .reduce(0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                                    sum, p, Thread.currentThread().getName());
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
                                    sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });
    }

    private static void ExamplesOnStreams1() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, "grocery", 200));
        transactions.add(new Transaction(2, "grocery", 300));
        transactions.add(new Transaction(3, "clothes", 1200));
        transactions.add(new Transaction(4, "footware", 200));
        transactions.add(new Transaction(5, "clothes", 2000));
        transactions.add(new Transaction(6, "grocery", 500));
        transactions.add(new Transaction(7, "footware", 400));

        //traditional way to find all transactions of type grocery
        // and return list of transaction IDs in descending order
        listTransactionIdsOfTypeGrocery(transactions);

        //using java8
        List<Integer> list = transactions.stream().filter(t->t.getType() == "grocery")
                .sorted(Comparator.comparing(Transaction::getTransactionId).reversed())
                .map(Transaction::getTransactionId).collect(Collectors.toList());

        //two even squares
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> evenSquares = numbers.stream().filter(t -> t % 2 == 0)
                .map(t-> t * t).limit(2).collect(Collectors.toList());

        //allMatch, noneMatch, anyMatch
        boolean lessThanTen = numbers.stream().allMatch(t -> t < 10);

        //findFirst, findAny
        Optional<Transaction> transaction = transactions.stream()
                .filter(t -> t.getType() == "grocery").findAny();
        transaction.ifPresent(System.out::println);

        List<String> words = Arrays.asList("Oracle", "Java", "Magazine");
        List<Integer> wordLengths =
                words.stream()
                        .map(String::length)
                        .collect(Collectors.toList());

        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        int product1 = numbers.stream().reduce(1, Integer::max);
        int statement = transactions.stream().mapToInt(Transaction::getValue).sum();
        IntStream oddNumbers = IntStream.rangeClosed(10, 30)
                .filter(n -> n % 2 == 1).map(IntUnaryOperator.identity());
        oddNumbers.forEach(System.out::println);

        Stream<Integer> number = Stream.iterate(0, n -> n + 10);
        number.limit(5).forEach(System.out::println);

        List<String> stringList = new ArrayList<String>();

        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        List<String> flatList = stringList.stream()
                .flatMap(value -> Arrays.stream(value.split(" ")))
                .collect(Collectors.toList());
        System.out.println(flatList);
    }

    private static void listTransactionIdsOfTypeGrocery(List<Transaction> transactions) {
        List<Transaction> groceryTransactions = new ArrayList<>();
        for(Transaction t: transactions){
            if(t.getType() == "grocery"){
                groceryTransactions.add(t);
            }
        }
        Collections.sort(groceryTransactions, new Comparator<Transaction>(){

            @Override
            public int compare(Transaction t1, Transaction t2){
                return Integer.compare(t2.getValue(), t1.getValue());
            }
        });
        List<Integer> transactionIds = new ArrayList<>();
        for(Transaction t: groceryTransactions){
            transactionIds.add(t.getTransactionId());
        }

        //System.out.println(transactionIds);
    }
}
