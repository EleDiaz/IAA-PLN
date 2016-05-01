/**
 * 
 */
package es.ull.iaa.pln;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author eleazardd
 *
 */
public class Filter {
    private TreeSet<String> set = new TreeSet<>();

    public Filter(Stream<String> lines) {
        Stream<String> words = lines.flatMap(line -> {
        	return Stream.of(line.chars()
        			.skip(6)
        			.collector(Collectors.joining(""))
        			).split(" ");
        });

        Pattern pat = Pattern.compile("(?:(?<symb>[.,'?()!\"/]*)(?<word>[A-Za-z]*))+");

        Pattern wordPat = Pattern.compile("[A-Za-z]+");

        Pattern hashtagPat = Pattern.compile("#[A-Za-z0-9]+");

        // En este punto tenemos cosas como hola.hola  xx!! jklñ423.Aaa
        words.forEach(new Consumer<String>() {
                public void accept(String word) {
                	System.out.println(word);
                    if (hashtagPat.matcher(word).matches()) {
                        set.add(word);
                        System.out.println(word);
                    }
                    else if (wordPat.matcher(word).matches()) {
                        set.add(word);
                        System.out.println(word);

                    }
                    else {
                        Matcher match = pat.matcher(word);
                        while (match.find()) {
                            addSymbols(match.group("symb"));
                            addWord(match.group("word"));
                        }
                    }
                };
            });
    }


    public TreeSet<String> getSet() {
        return set;
    }

    private void addWord(String word) {
        if (!word.isEmpty()) {
            set.add(word);
        }
    }

    private void addSymbols(String word) {
        for (char ch : word.toCharArray()) {
            set.add(new Character(ch).toString());
        }
    }
}
