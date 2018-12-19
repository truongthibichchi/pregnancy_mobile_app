package org.a3tn.pregnancy_mobile.Model;

public class Glossary {
    private int id;
    private String word;
    private String meaning;

    public Glossary() {
    }

    public Glossary(int id, String word, String meaning) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
