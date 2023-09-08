package fe;
public interface Game {
    void start(Integer sizeWord, Integer maxTry);

    Answer inputValue(String value);

    GameStatus getGameStatus();

    void addToLog(String log);
    String getLog();
    void setMaxTry(Integer maxTry);
    void setLog(String log);
    void exit();

    void addTry(int addTry);
}