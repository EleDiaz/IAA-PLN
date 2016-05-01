/**
 * 
 */
package es.ull.iaa.pln;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author eleazardd
 *
 */
public class Filter {

    public Filter(Stream<String> lines) {
        Stream words = lines.flatMap(line ->
                                     Stream.of(Arrays.toString(line.chars().skip(6).toArray()).split(" "))
                                     );

        TreeSet<String> set = new TreeSet<>();

        Pattern pat = Pattern.compile("(?:(?<symb>[.,'?()!\"/]*)(?<word>[-A-Za-z]*))+");

        Pattern wordPat = Pattern.compile("[-A-Za-z]+");

        Pattern hashtagPat = Pattern.compile("#[-A-Za-z0-9]+");

        // En este punto tenemos cosas como hola.hola  xx!! jklñ423.Aaa
        words.forEach(new Consumer<String>() {
                public void accept(String word) {
                    if (hashtagPat.matcher(word).matches()) {
                        set.add(word);
                    }
                    else if (wordPat.matcher(word).matches()) {
                        set.add(word);
                    }
                    else {
                        Matcher match = pat.matcher(word);
                        while (match.find()) {
                            set.add(match.group("symb")); // hay que tratarlo
                            set.add(match.group("word")); // hay que tratar ""
                        }
                    }
                };
            });

        // palabras sanas -> [A-Za-z-]+   // ver palabras capitales y palabras escritas hOlA QUe TAL // el - lo consideramos parte para palabras como semi-automatic
        // palabras no sanas todo lo demas,
				// estrategia dividir en [.,'?()!"/]
				// ver las palabras "sanas" que puedan existir en la nueva division
				// y añadir los signos de esclamacion al vocabulario
        // Las palabras deben ser añadidas al 
    }

}
