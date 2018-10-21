import java.io.File;
import java.util.*;

public class Logic {
    public static void go(String filename){

        ArrayList<String> arrayWords = fileToArray(filename);

        // Если файл не существует или файл пустой, выполнять подсчет статистики не нужно
        if(arrayWords.isEmpty()){

        } else{
            // Посчитать статистику и вывести в консоль
            TreeMap<String, Integer> statistics = countDuplicates(arrayWords);
            // Найти число встречающееся чаще всех и вывести в консоль
            maxCountDisplay(statistics);
        }
    }

    /**
     * Обрабатывает файл с набором слов, написанных через пробел.
     * @param filename в качестве параметра принимает полное или относительное имя файла.
     * @return возвращает ArrayList состоящий из слов файла.
     */
    private static ArrayList<String> fileToArray(String filename){
        ArrayList<String> arrayWords = new ArrayList<>();
        try {
            // цикл while по файлу с помощью Scanner
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                // в arrayWords записываются все слова разделенные пробельными символами
                arrayWords.add(scanner.useDelimiter("\\s+").next());
            }
        } catch (Exception e) {
            System.err.println("There is no such file");
        }
        return arrayWords;
    }

    /**
     * Подсчитывает статистику и отображает её в консоли в алфавитном порядке
     * @param arrayWords принимает ArrayList строк.
     * @return возвращает TreeMap из пар <String, Integer>, где String - отдельное слово, а Integer - количество раз сколько оно встретилось.
     */
    private static TreeMap<String, Integer> countDuplicates(ArrayList arrayWords) {
        
        // TreeMap обеспечивает сортировку, дополнительно был добавлен компоратор,
        // что бы буквы вверхнего и нижнего регистра сравнивались одинаково
        TreeMap<String, Integer> statistics = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int cmp = o1.toLowerCase().compareTo(o2.toLowerCase());
                if (cmp != 0) return cmp;

                return o1.compareTo(o2);
            }
        });

        // Проход по arrayWords с помощью Iterator
        Iterator listIterator = arrayWords.listIterator();
        while(listIterator.hasNext()){
            // Записываем слово в переменную word
            String word = (String) listIterator.next();
            // Проверяем есть ли это слово в TreeMap statistic
            Integer count = statistics.get(word);
            // Если нет, то присваиваем count = 0
            if(count == null){
                count = 0;
            }
            // Записываем или перезаписываем слово в TreeMap с инкрементом count
            statistics.put(word, ++count);
        }
        System.out.println("+--------Word-Count---------+");
        // Проход по statistics и вывод информации в консоль
        System.out.println(statistics);
        //for (Map.Entry<String, Integer> stringEntry : statistics.entrySet()) {
        //    System.out.print(stringEntry.getKey() + "-" + stringEntry.getValue() + " ");
        //}
        System.out.println("");
        return statistics;
    }

    /**
     * ищет наиболее частво встречаемое слово в Map и выводит его в консоль.
     * @param statistics принимает TreeMap из пар <String, Integer>, где String - отдельное слово, а Integer - количество раз сколько оно встретилось.
     */
    private static void maxCountDisplay(TreeMap<String, Integer> statistics){
        System.out.println("+--------Result-------------+");
        // Поиск максимально значения в Map
        int maxValueInMap = (Collections.max(statistics.values()));
        // Вывод в консоль всех Value у которых Key максимальное значение
        for(Map.Entry<String, Integer> entry : statistics.entrySet()){
            if(entry.getValue() == maxValueInMap){
                System.out.println("The word \"" + entry.getKey() + "\" occurs "+ maxValueInMap + " times in the file.");
            }
        }
    }
}

