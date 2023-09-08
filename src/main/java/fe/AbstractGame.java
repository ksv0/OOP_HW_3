package fe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {

    Integer sizeWord;
    Integer maxTry;
    String word;
    GameStatus gameStatus = GameStatus.INIT;
    String log = "";

    public String generateWord() {
        List<String> alphabet = generateCharList();
        Random r = new Random();
        String result = "";
        for (int i = 0; i < sizeWord; i++) {
            int index = r.nextInt(alphabet.size());
            result = result.concat(alphabet.get(index));
            alphabet.remove(index);
        }
        return result;
    }

    abstract List<String> generateCharList();

    @Override
    public void start(Integer sizeWord, Integer maxTry) {
        setSizeWord(sizeWord);
        setMaxTry(maxTry);
        setWord(generateWord());
        setGameStatus(GameStatus.START);
        System.out.println("подсказка: " + word);
    }

    @Override
    public Answer inputValue(String value) {
        maxTry--;
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == value.charAt(i)) {
                bulls++;
                cows++;
            } else {
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) == value.charAt(i)) {
                        cows++;
                    }
                }
            }
        }
        if (word.length() == bulls) {
            System.out.println("Все девки твои");
            setGameStatus(GameStatus.WINNER);
        }
        if (maxTry == 0 && !gameStatus.equals(GameStatus.WINNER)) {
            System.out.println("Лежать + сосать");
            setGameStatus( GameStatus.LOSE);
        }
        return new Answer(value, bulls, cows);
    }
    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    public void addToLog(String logLine) {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E yyyy.MM.dd, hh:mm:ss a zzz");
        this.log = this.log + formatForDateNow.format(dateNow) + log + "\n";
    }


    @Override
    public void exit() {
        setGameStatus( GameStatus.LOSE);
    }
    public void setSizeWord(Integer sizeWord) {
        this.sizeWord = sizeWord;
    }

    public void setMaxTry(Integer maxTry) {
        this.maxTry = maxTry;
    }

    @Override
    public void addTry(int addTry) {
        this.maxTry += addTry;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Integer getSizeWord() {
        return sizeWord;
    }

    public Integer getMaxTry() {
        return maxTry;
    }

    public String getWord() {
        return word;
    }

    public String getLog() {
        return log;
    }

}
