package com.pprzadka.multiapp;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomSentenceGenerator {

    public RandomSentenceGenerator() {

    }

    String[] adjectives = new String[]{"small", "big", "huge", "tiny", "cute", "ugly", "beautiful",
            "awful", "weird", "lovely", "good", "bad", "nice"};
    String[] nouns = new String[]{"man", "woman", "house", "pet", "dog", "cat", "building",
            "flower", "plant", "bird", "bear", "horse", "apartment", "garden", "car", "bike", "pot",
            "vase", "painting", "sculpture", "balloon"};
    String[] adverbs = new String[]{"really", "very", "barely", "surprisingly", "naturally",
            "somewhat", "quite", "fairly", "pretty", "considerably", "notably"};
    String[] verbs = new String[]{"is", "looks", "was", "looked"};
    String[] articles = new String[]{"a", "the", "this", "that", "my", "your", "their", "his", "her"};
    String[] conjunctions = new String[]{"and", "but"};
    String[] endings = new String[]{"though", "surprisingly", "naturally", "obviously"};
    String[] sentenceConjunctions = new String[] {"moreover,", "what is more,", "and", "one important thing is that", "everyone should know that", "it is important that", "it is believed that"};

    public String generateSentence(int wordCount, boolean includeConjunction) {
        ArrayList<String> sentence = new ArrayList<>();
        sentence.add(verbs[ThreadLocalRandom.current().nextInt(0, verbs.length)] + " ");
        if(wordCount >= 2) {
            sentence.add(0, nouns[ThreadLocalRandom.current().nextInt(0, nouns.length)] + " ");
        }
        if(wordCount >= 3) {
            sentence.add(adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
        }
        if(wordCount >= 4) {
            sentence.add(0, articles[ThreadLocalRandom.current().nextInt(0, articles.length)] + " ");
        }
        if (wordCount >= 5) {
            if(wordCount == 5) {
                int option = ThreadLocalRandom.current().nextInt(0, 3);
                switch (option) {
                    case 0:
                        sentence.add(1, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
                        break;
                    case 1:
                        sentence.add(3, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 2:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(1, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
            }
        }
        if (wordCount >= 6) {
            if (wordCount == 6) {
                int option = ThreadLocalRandom.current().nextInt(0, 2);
                switch (option) {
                    case 0:
                        sentence.add(4, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 1:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(4, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
            }
        }
        if (wordCount >= 7) {
            if (wordCount == 7) {
                int option = ThreadLocalRandom.current().nextInt(0, 2);
                switch (option) {
                    case 0:
                        sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 1:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(conjunctions[ThreadLocalRandom.current().nextInt(0, conjunctions.length)] + " ");
            }
        }
        if(wordCount >= 8) {
            sentence.add(adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
        }
        if (wordCount >= 9) {
            if(wordCount == 9) {
                int option = ThreadLocalRandom.current().nextInt(0, 3);
                switch (option) {
                    case 0:
                        sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 1:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 2:
                        sentence.add(7, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
            }
        }
        if(wordCount >= 10) {
            if(wordCount == 10) {
                int option = ThreadLocalRandom.current().nextInt(0, 2);
                switch (option) {
                    case 0:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 1:
                        sentence.add(8, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                }
            }
            else {
                sentence.remove(8);
                sentence.remove(2);
                sentence.remove(1);
                sentence.add(articles[ThreadLocalRandom.current().nextInt(0, articles.length)] + " ");
                sentence.add(nouns[ThreadLocalRandom.current().nextInt(0, nouns.length)] + " ");
                sentence.add(verbs[ThreadLocalRandom.current().nextInt(0, verbs.length)] + " ");
                sentence.add(adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
            }
        }
        if (wordCount >= 11) {
            if(wordCount == 11) {
                int option = ThreadLocalRandom.current().nextInt(0, 4);
                switch (option) {
                    case 0:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 1:
                        sentence.add(9, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 2:
                        sentence.add(1, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
                        break;
                    case 3:
                        sentence.add(7, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(1, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
            }
        }
        if(wordCount >= 12) {
            if(wordCount == 12) {
                int option = ThreadLocalRandom.current().nextInt(0, 4);
                switch (option) {
                    case 0:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 1:
                        sentence.add(10, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 2:
                        sentence.add(8, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
                        break;
                    case 3:
                        sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(8, adjectives[ThreadLocalRandom.current().nextInt(0, adjectives.length)] + " ");
            }
        }
        if(wordCount >= 13) {
            if(wordCount == 13) {
                int option = ThreadLocalRandom.current().nextInt(0, 4);
                switch (option) {
                    case 0:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 1:
                        sentence.add(11, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 2:
                        sentence.add(8, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 3:
                        sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(11, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
            }
        }
        if (wordCount >= 14) {
            if(wordCount == 14) {
                int option = ThreadLocalRandom.current().nextInt(0, 3);
                switch (option) {
                    case 0:
                        sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                        break;
                    case 1:
                        sentence.add(8, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                    case 2:
                        sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                        break;
                }
            }
            else {
                sentence.add(8, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
            }
        }
        if(wordCount == 15) {
            int option = ThreadLocalRandom.current().nextInt(0, 2);
            switch (option) {
                case 0:
                    sentence.add(", " + endings[ThreadLocalRandom.current().nextInt(0, endings.length)] + " ");
                    break;
                case 1:
                    sentence.add(1, adverbs[ThreadLocalRandom.current().nextInt(0, adverbs.length)] + " ");
                    break;
            }
        }

        if (includeConjunction) {
            sentence.add(0, sentenceConjunctions[ThreadLocalRandom.current().nextInt(0, sentenceConjunctions.length)] + " ");
        }
        String sentenceString = TextUtils.join("", sentence).trim();
        sentenceString = sentenceString.replaceAll("\\s+(?=\\p{Punct})", "");
        sentenceString += ". ";
        return sentenceString.substring(0, 1).toUpperCase() + sentenceString.substring(1);
    }
}
