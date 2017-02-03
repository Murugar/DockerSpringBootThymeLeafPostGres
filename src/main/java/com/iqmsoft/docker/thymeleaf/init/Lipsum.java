package com.iqmsoft.docker.thymeleaf.init;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SuppressWarnings("PMD.DefaultPackage")
public final class Lipsum {

  public static final String ASSET_PATH = "/assets/lipsum.txt";

  private static String lipsumText;
  private static List<String> words;

  /**
   * Private constructor for utility class.
   */
  private Lipsum() {}

  /**
   * Get the full lorem ipsum text.
   * To improve performance it is statically cached.
   *
   * @return the plain text block
   */
  public static String getLipsumText() {
    if (lipsumText != null) {
      return lipsumText;
    }
    lipsumText = readTextFile(ASSET_PATH);
    return lipsumText;
  }

  /**
   * Read a file using some Java 8 goodness.
   *
   * @param filePath the relative classpath of the file
   * @return the file text content
   */
  public static String readTextFile(final String filePath) {
    final Stream<String> stream = new BufferedReader(new InputStreamReader(
            Lipsum.class.getResourceAsStream(filePath))).lines();

    return stream.collect(Collectors.joining("\n"));
  }

  /**
   * Get a random lipsum word.
   * @return a word
   */
  public static String getRandomWord() {
    final List<String> wordList = getWordList();
    final int rand = new Random().nextInt(wordList.size());
    return wordList.get(rand);
  }

  /**
   * Get a complete list of each unique word in the lipsum text.
   * Each word is also captialised and the word list is statically cached.
   * @return the word list.
   */
  public static List<String> getWordList() {
    if (words != null) {
      return words;
    }
    final int minLength = 3;
    final List<String> wordList = Arrays.asList(StringUtils.split(clean(getLipsumText())));
    words = wordList.stream()
            .distinct()
            .filter(f -> f.length() > minLength)
            .map(Lipsum::capitalise)
            .collect(Collectors.toList());
    return words;
  }

  /**
   * Clean a string of text removing any not ascii characters.
   * @param text the text to clean
   * @return the cleaned text
   */
  public static String clean(final String text) {
    return StringUtils.replacePattern(text, "[.,]+", "").toLowerCase();
  }

  /**
   * Capitalise a word.
   * @param word the original word
   * @return the capitalised word
   */
  public static String capitalise(final String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

  /**
   * Get a list of random words.
   * @param size the size of the list
   */
  public static List<String> getRandomWordList(final int size) {
    final List<String> wordList = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      wordList.add(getRandomWord());
    }
    return wordList;
  }
}