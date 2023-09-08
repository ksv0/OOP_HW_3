package fe;
public class Answer {
    public static int counter = 0;
    private String userInput;
    private Integer bull;
    private Integer cow;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public void setBull(Integer bull) {
        this.bull = bull;
    }

    public void setCow(Integer cow) {
        this.cow = cow;
    }

    public String getUserInput() {
        return userInput;
    }

    public Integer getBull() {
        return bull;
    }

    public Integer getCow() {
        return cow;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "UserInput='" + userInput + '\'' +
                ", bull=" + bull +
                ", cow=" + cow +
                '}';
    }

    public Answer(String userInput, Integer bull, Integer cow) {
        setUserInput(userInput);
        setBull(bull);
        setCow(cow);
        counter++;
    }
}
