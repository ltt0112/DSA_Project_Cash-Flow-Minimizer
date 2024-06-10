import java.util.*;

class Person {
    public String name;
    public int netAmount;
    public Set<String> types;
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

public class CashFlowMinimizer_Dijkstra {

    public static void dijkstra(int numPersons, Person[] listOfNetAmounts, List<List<Pair<Integer, String>>> ansGraph) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
        int[] dist = new int[numPersons];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // Start from the "World Person"

        pq.add(new Pair<>(0, 0)); // (distance, PersonIndex)

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> current = pq.poll();
            int currentPerson = current.getValue();


            for (int i = 0; i < numPersons; i++) {
                if (i != currentPerson && listOfNetAmounts[i].netAmount != 0) {
                    int newDist = dist[currentPerson] + Math.abs(listOfNetAmounts[i].netAmount);
                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        pq.add(new Pair<>(newDist, i));
                        //ansGraph.get(currentPerson).set(i, new Pair<>(Math.abs(listOfNetAmounts[i].netAmount), listOfNetAmounts[i].types.iterator().next()));
                        ansGraph.get(currentPerson).set(i, new Pair<>(listOfNetAmounts[i].netAmount, listOfNetAmounts[i].types.iterator().next()));
                    }
                }
            }
        }
    }

    public static void printAns(List<List<Pair<Integer, String>>> ansGraph, int numPersons, Person[] input) {
        System.out.println("\nThe transactions for minimum cash flow are as follows:\n");
        for (int i = 0; i < numPersons; i++) {
            for (int j = 0; j < numPersons; j++) {
                if (i == j) continue;

                if (ansGraph.get(i).get(j).getKey() != 0) {
                    if (ansGraph.get(i).get(j).getKey() < 0){
                        System.out.println(input[j].name + " pays Rs " + -1*ansGraph.get(i).get(j).getKey() + " to " + input[i].name + " via " + ansGraph.get(i).get(j).getValue());
                    }
                    else {
                        System.out.println(input[i].name + " pays Rs " + ansGraph.get(i).get(j).getKey() + " to " + input[j].name + " via " + ansGraph.get(i).get(j).getValue());
                    }
                }
            }
        }
        System.out.println();
    }

    public static void minimizeCashFlow(int numPersons, Person[] input, Map<String, Integer> indexOf, int numTransactions, int[][] graph, int maxNumTypes) {
        Person[] listOfNetAmounts = new Person[numPersons];

        for (int b = 0; b < numPersons; b++) {
            listOfNetAmounts[b] = new Person();
            listOfNetAmounts[b].name = input[b].name;
            listOfNetAmounts[b].types = new HashSet<>(input[b].types);

            int amount = 0;

            for (int i = 0; i < numPersons; i++) {
                amount += graph[i][b];
            }

            for (int j = 0; j < numPersons; j++) {
                amount += (-1) * graph[b][j];
            }
//            for (int i = 0; i < numPersons; i++) {
//                if (i != b) {
//                    amount -= graph[i][b];
//                    amount += graph[b][i];
//                }
//            }

            listOfNetAmounts[b].netAmount = amount;
        }

        List<List<Pair<Integer, String>>> ansGraph = new ArrayList<>(numPersons);
        for (int i = 0; i < numPersons; i++) {
            ansGraph.add(new ArrayList<>(Collections.nCopies(numPersons, new Pair<>(0, ""))));
        }

        dijkstra(numPersons, listOfNetAmounts, ansGraph);

//        adjustBalances(numPersons, listOfNetAmounts, ansGraph);

        printAns(ansGraph, numPersons, input);
    }

    public static void main(String[] args) {
        System.out.println("\n\t\t\t\t********************* Welcome to CASH FLOW MINIMIZER SYSTEM ***********************\n\n\n");
        System.out.println("This system minimizes the number of transactions among multiple Persons in the different corners of the world that use different modes of payment. There is one world Person (with all payment modes) to act as an intermediary between Persons that have no common mode of payment.\n");
        System.out.println("Enter the number of Persons participating in the transactions.");
        Scanner scanner = new Scanner(System.in);
        int numPersons = scanner.nextInt();

        Person[] input = new Person[numPersons];
        Map<String, Integer> indexOf = new HashMap<>();

        System.out.println("Enter the details of the Persons and transactions as stated:");
        System.out.println("Person name, number of payment modes it has, and the payment modes.");
        System.out.println("Person name and payment modes should not contain spaces.");

        int maxNumTypes = 0;
        for (int i = 0; i < numPersons; i++) {
            if (i == 0) {
                System.out.print("Main Person: ");
            } else {
                System.out.print("Person " + i + ": ");
            }
            String name = scanner.next();
            input[i] = new Person();
            input[i].name = name;
            indexOf.put(name, i);

            int numTypes = scanner.nextInt();

            if (i == 0) maxNumTypes = numTypes;

            Set<String> types = new HashSet<>(numTypes);

            for (int j = 0; j < numTypes; j++) {
                types.add(scanner.next());
            }

            input[i].types = types;
        }

        System.out.println("Enter the number of transactions.");
        int numTransactions = scanner.nextInt();

        int[][] graph = new int[numPersons][numPersons];

        System.out.println("Enter the details of the transactions as stated:");
        System.out.println("Person name from which the transaction originates, Person name to which the transaction is made, and the amount.");

        for (int i = 0; i < numTransactions; i++) {
            String from = scanner.next();
            String to = scanner.next();
            int amount = scanner.nextInt();

            Integer fromIndex = indexOf.get(from);
            Integer toIndex = indexOf.get(to);

            if (fromIndex != null && toIndex != null) {
                int fromIdx = fromIndex.intValue();
                int toIdx = toIndex.intValue();
                graph[fromIdx][toIdx] = amount;
            } else {
                System.out.println("Invalid Person names: " + from + ", " + to);
            }
        }

        minimizeCashFlow(numPersons, input, indexOf, numTransactions, graph, maxNumTypes);

        scanner.close();
    }
}
