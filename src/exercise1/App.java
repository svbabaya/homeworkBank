package exercise1;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        final double walletBank = 3199.1;

        Map<String, Integer> wallet = new HashMap<>();

        wallet.put("Михаил", 134);
        wallet.put("Татьяна", 212);
        wallet.put("Игорь", 344);
        wallet.put("Александр", 515);
        wallet.put("Георг", 211);
        wallet.put("Оля", 143);
        wallet.put("Светлана", 425);

        // Сумма всех кошельков в копейках
        int sumWallets = 0;
        for (Map.Entry<String, Integer> w : wallet.entrySet()) {
            sumWallets += w.getValue();
        }
//        System.out.println(sumWallets); // 1984

        // Общее количество денег в кошельках и банке в копейках
        int sumAll = (int) (walletBank * 100) + sumWallets;
//        System.out.println(sumAll); // 321894

        // Остаток от деления общего количества денег на количество клиентов в копейках
        int remOfDivision = sumAll % wallet.size();
//        System.out.println(remOfDivision); // 6

        // Максимально равное количество денег, которое должно оказаться в каждом кошельке после распределения в копейках
        int addition = (sumAll - remOfDivision) / wallet.size();
//        System.out.println(addition); // 45984

        // Создать map для дополнительных сумм в копейках
        Map<String, Integer> appendFromBank = new HashMap<>();
        appendFromBank.putAll(wallet);
        for (Map.Entry<String, Integer> a : appendFromBank.entrySet()) {
            a.setValue((addition - a.getValue()));
        }
//        System.out.println(appendFromBank);

        // Распределить остаток 6 копеек, по 1 копейке в порядке перебора
        int count = 1;
        for (Map.Entry<String, Integer> a : appendFromBank.entrySet()) {
            if (count <= remOfDivision) {
                a.setValue(a.getValue() + 1);
            } else {
                break;
            }
            count++;
        }
//        System.out.println(appendFromBank);

        // Результат
        for (Map.Entry<String, Integer> res : wallet.entrySet()) {
            System.out.printf("%s, wallet = %.2f, appendFromBank = %.2f, total = %.2f%n",
                    res.getKey(), ((double) res.getValue()) / 100, ((double) appendFromBank.get(res.getKey())) / 100,
                    ((double) (res.getValue() + appendFromBank.get(res.getKey()))) / 100);
        }

        System.out.println();
        System.out.println("Меньше всего досталось клиентам:");

        // Максимальное значение total
        int max = 0;
        for (Map.Entry<String, Integer> a : appendFromBank.entrySet()) {
            int total = a.getValue() + wallet.get(a.getKey());
            if (total > max) {
                max = total;
            }
        }
//        System.out.println(max);

        for (Map.Entry<String, Integer> min : wallet.entrySet()) {
            if ((min.getValue() + appendFromBank.get(min.getKey())) < max) {
                System.out.printf("%s%n", min.getKey());
            }
        }

    }
}
