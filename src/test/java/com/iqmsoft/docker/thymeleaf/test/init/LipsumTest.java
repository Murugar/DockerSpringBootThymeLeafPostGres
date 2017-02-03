package com.iqmsoft.docker.thymeleaf.test.init;

import org.junit.Test;

import com.iqmsoft.docker.thymeleaf.init.Lipsum;

import java.util.List;

import static org.junit.Assert.*;


public class LipsumTest {

  @Test
  public void getLipsum() throws Exception {
    final String lipsum = Lipsum.getLipsumText();
    assertTrue(lipsum.startsWith("Morbi dictum"));
  }

  @Test
  public void readTextFile() throws Exception {
    final String lipsum = Lipsum.readTextFile(Lipsum.ASSET_PATH);
    assertTrue(lipsum.startsWith("Morbi dictum"));
  }

  @Test
  public void getRandomWord() throws Exception {
    final String word = Lipsum.getRandomWord();
    assertTrue(word.length() > 0);
  }

  @Test
  public void getWordList() throws Exception {
    final List<String> wordList = Lipsum.getWordList();
    assertFalse(wordList.isEmpty());
  }

  @Test
  public void clean() throws Exception {
    final String text = "one, two, three.";
    final String clean = Lipsum.clean(text);
    assertFalse(clean.matches("[.,]+"));
  }

  @Test
  public void capitalise() throws Exception {
    final String word = "barry";
    final String capitalised = Lipsum.capitalise(word);
    assertEquals(capitalised.substring(0, 1), "B");
  }

  @Test
  public void getRandomWordList() throws Exception {
    final int size = 10;
    final List<String> wordList = Lipsum.getRandomWordList(size);
    assertEquals(wordList.size(), size);
  }
}