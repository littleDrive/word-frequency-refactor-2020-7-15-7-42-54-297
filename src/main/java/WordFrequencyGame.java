import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String SPACE_PATTERN = "\\s+";
    public static final String NEWLINE = "\n";
    public static final String STRING = " ";

    public String getResult(String sentence) {

        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {
            try {
                String[] words = sentence.split(SPACE_PATTERN);

                List<WordInfo> wordInfoList = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfoList.add(wordInfo);
                }

                Map<String, List<WordInfo>> map = getListMap(wordInfoList);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    wordInfos.add(wordInfo);
                }
                wordInfoList = wordInfos;

                wordInfoList.sort((firstword, secondword) -> secondword.getWordCount() - firstword.getWordCount());

                StringJoiner joiner = new StringJoiner(NEWLINE);
                for (WordInfo wordInfo : wordInfoList) {
                    String wordString = wordInfo.getValue() + STRING + wordInfo.getWordCount();
                    joiner.add(wordString);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList) {
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            } else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
