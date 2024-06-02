import java.util.*;

public class CashFlowMinimizer {

    public static int getMinIndex(Person[] listOfNetAmounts, int numBanks) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) continue;

            if (listOfNetAmounts[i].netAmount < min) {
                minIndex = i;
                min = listOfNetAmounts[i].netAmount;
            }
        }
        return minIndex;
    }

    public static int getSimpleMaxIndex(Person[] listOfNetAmounts, int numBanks) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) continue;

            if (listOfNetAmounts[i].netAmount > max) {
                maxIndex = i;
                max = listOfNetAmounts[i].netAmount;
            }
        }
        return maxIndex;
    }

    public static Pair<Integer, String> getMaxIndex(Person[] listOfNetAmounts, int numBanks, int minIndex, Person[] input, int maxNumTypes) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        String matchingType = "";

        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) continue;
            if (listOfNetAmounts[i].netAmount < 0) continue;

            List<String> v = new ArrayList<>(maxNumTypes);
            Iterator<String> ls = listOfNetAmounts[minIndex].types.iterator();
            Iterator<String> rs = listOfNetAmounts[i].types.iterator();
            while (ls.hasNext() && rs.hasNext()) {
                String type1 = ls.next();
                String type2 = rs.next();
                if (type1.equals(type2)) {
                    v.add(type1);
                }
            }

            if (!v.isEmpty() && max < listOfNetAmounts[i].netAmount) {
                max = listOfNetAmounts[i].netAmount;
                maxIndex = i;
                matchingType = v.get(0);
            }
        }
        return new Pair<>(maxIndex, matchingType);
    }

    public static void printAns(List<List<Pair<Integer, String>>> ansGraph, int numBanks, Person[] input) {
        System.out.println("\nThe transactions for minimum cash flow are as follows:\n");
        for (int i = 0; i < numBanks; i++) {
            for (int j = 0; j < numBanks; j++) {
                if (i == j) continue;

                if (ansGraph.get(i).get(j).getKey() != 0 && ansGraph.get(j).get(i).getKey() != 0) {
                    if (ansGraph.get(i).get(j).getKey() == ansGraph.get(j).get(i).getKey()) {
                        ansGraph.get(i).get(j).setKey(0);
                        ansGraph.get(j).get(i).setKey(0);
                    } else if (ansGraph.get(i).get(j).getKey() > ansGraph.get(j).get(i).getKey()) {
                        ansGraph.get(i).get(j).setKey(ansGraph.get(i).get(j).getKey() - ansGraph.get(j).get(i).getKey());
                        ansGraph.get(j).get(i).setKey(0);
                        System.out.println(input[i].name + " pays " + ansGraph.get(i).get(j).getKey() + " to " + input[j].name + " by " + ansGraph.get(i).get(j).getValue());
                    } else {
                        ansGraph.get(j).get(i).setKey(ansGraph.get(j).get(i).getKey() - ansGraph.get(i).get(j).getKey());
                        ansGraph.get(i).get(j).setKey(0);
                        System.out.println(input[j].name + " pays " + ansGraph.get(j).get(i).getKey() + " to " + input[i].name + " by " + ansGraph.get(j).get(i).getValue());
                    }
                } else if (ansGraph.get(i).get(j).getKey() != 0) {
                    System.out.println(input[i].name + " pays " + ansGraph.get(i).get(j).getKey() + " to " + input[j].name + " by " + ansGraph.get(i).get(j).getValue());
                } else if (ansGraph.get(j).get(i).getKey() != 0) {
                    System.out.println(input[j].name + " pays " + ansGraph.get(j).get(i).getKey() + " to " + input[i].name + " by " + ansGraph.get(j).get(i).getValue());
                }
                ansGraph.get(i).get(j).setKey(0);
                ansGraph.get(j).get(i).setKey(0);
            }
        }
        System.out.println();
    }

    public static void minimizeCashFlow(int numBanks, Person[] input, Map<String, Integer> indexOf, int numTransactions, int[][] graph, int maxNumTypes) {
        Person[] listOfNetAmounts = new Person[numBanks];

        for (int b = 0; b < numBanks; b++) {
            listOfNetAmounts[b] = new Person();
            listOfNetAmounts[b].name = input[b].name;
            listOfNetAmounts[b].types = new HashSet<>(input[b].types);

            int amount = 0;
            for (int i = 0; i < numBanks; i++) {
                amount += graph[i][b];
            }
            for (int j = 0; j < numBanks; j++) {
                amount += (-1) * graph[b][j];
            }
            listOfNetAmounts[b].netAmount = amount;
        }

        List<List<Pair<Integer, String>>> ansGraph = new ArrayList<>(numBanks);
        for (int i = 0; i < numBanks; i++) {
            ansGraph.add(new ArrayList<>(Collections.nCopies(numBanks, new Pair<>(0, ""))));
        }

        int numZeroNetAmounts = 0;
        for (int i = 0; i < numBanks; i++) {
            if (listOfNetAmounts[i].netAmount == 0) numZeroNetAmounts++;
        }

        while (numZeroNetAmounts != numBanks) {
            int minIndex = getMinIndex(listOfNetAmounts, numBanks);
            Pair<Integer, String> maxAns = getMaxIndex(listOfNetAmounts, numBanks, minIndex, input, maxNumTypes);
            int maxIndex = maxAns.getKey();

            if (maxIndex == -1) {
                ansGraph.get(minIndex).get(0).setKey(Math.abs(listOfNetAmounts[minIndex].netAmount));
                ansGraph.get(minIndex).get(0).setValue(input[minIndex].types.iterator().next());
                int simpleMaxIndex = getSimpleMaxIndex(listOfNetAmounts, numBanks);
                ansGraph.get(0).get(simpleMaxIndex).setKey(Math.abs(listOfNetAmounts[minIndex].netAmount));
                ansGraph.get(0).get(simpleMaxIndex).setValue(input[simpleMaxIndex].types.iterator().next());
                listOfNetAmounts[simpleMaxIndex].netAmount += listOfNetAmounts[minIndex].netAmount;
                listOfNetAmounts[minIndex].netAmount = 0;

                if (listOfNetAmounts[minIndex].netAmount == 0) numZeroNetAmounts++;
                if (listOfNetAmounts[simpleMaxIndex].netAmount == 0) numZeroNetAmounts++;
            } else {
                int transactionAmount = Math.min(Math.abs(listOfNetAmounts[minIndex].netAmount), listOfNetAmounts[maxIndex].netAmount);
                ansGraph.get(minIndex).get(maxIndex).setKey(transactionAmount);
                ansGraph.get(minIndex).get(maxIndex).setValue(maxAns.getValue());
                listOfNetAmounts[minIndex].netAmount += transactionAmount;
                listOfNetAmounts[maxIndex].netAmount -= transactionAmount;

                if (listOfNetAmounts[minIndex].netAmount == 0) numZeroNetAmounts++;
                if (listOfNetAmounts[maxIndex].netAmount == 0) numZeroNetAmounts++;
            }
        }

        printAns(ansGraph, numBanks, input);
    }

    public static void main(String[] args) {
        System.out.println("\n\t\t\t\t********************* Welcome to CASH FLOW MINIMIZER SYSTEM ***********************\n\n\n");
        System.out.println("Enter the number of people participating in the transactions.");
        Scanner scanner = new Scanner(System.in);
        int numBanks = scanner.nextInt();

        Person[] input = new Person[numBanks];
        Map<String, Integer> indexOf = new HashMap<>();

        System.out.println("Enter the details of the person and transactions as stated:");
        System.out.println("People name, number of payment modes them has, and the payment modes.");
        System.out.println("People name and payment modes should not contain spaces.");

        int maxNumTypes = 0;
        for (int i = 0; i < numBanks; i++) {
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

        int[][] graph = new int[numBanks][numBanks];
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
                System.out.println("Invalid person names: " + from + ", " + to);
            }
        }

        minimizeCashFlow(numBanks, input, indexOf, numTransactions, graph, maxNumTypes);
        scanner.close();
    }
}
