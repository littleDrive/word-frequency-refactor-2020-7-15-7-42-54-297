import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String NEWLINE = "\n";
    public static final String STRING = " ";
    public static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {

            try {
                String[] words = sentence.split(SPACE_PATTERN);
                List<WordInfo> wordInfoList = getWordInfoList(words);
                wordInfoList = getWordInfoListByOrder(wordInfoList);

                return getJoinerOfWordInfoList(wordInfoList);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
    }

    private String getJoinerOfWordInfoList(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner(NEWLINE);
        for (WordInfo wordInfo : wordInfoList) {
            String wordString = wordInfo.getValue() + STRING + wordInfo.getWordCount();
            joiner.add(wordString);
        }
        return joiner.toString();
    }

    private List<WordInfo> getWordInfoListByOrder(List<WordInfo> wordInfoList) {

        List<WordInfo> wordInfos = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : getListMap(wordInfoList).entrySet()) {
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            wordInfos.add(wordInfo);
        }
        wordInfoList = wordInfos;

        wordInfoList.sort((firstword, secondword) -> secondword.getWordCount() - firstword.getWordCount());
        return wordInfoList;
    }

    private List<WordInfo> getWordInfoList(String[] words) {
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }
        return wordInfoList;
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> wordsToList = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!wordsToList.containsKey(wordInfo.getValue())) {
                ArrayList wordInfos = new ArrayList<>();
                wordInfos.add(wordInfo);
                wordsToList.put(wordInfo.getValue(), wordInfos);
            } else {
                wordsToList.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return wordsToList;
    }
}
