package fe;

import java.util.Scanner;

public class GameProcess {

    public void gameRun() {
        Game game = choiceOfGame();
        chooseMainOption(game);
        do {
            chooseGameOptions(game);
        }
        while (!game.getGameStatus().equals(GameStatus.WINNER) && !game.getGameStatus().equals(GameStatus.LOSE));
    }

    private void chooseGameOptions(Game game) {
        boolean dummyCheck = true;
        while (dummyCheck) {
            System.out.println("Что делаем:\n1. answer или ответ - Ввести свой ответ\n2. log или лог - Показать логи\n3. im fool или я дурак - +5 попыток\n4. restart или рестарт - Рестарт игры\n5. end или конец - Закончить игру");
            String choice = readLine();
            switch (choice) {
                case "a", "an", "answer", "о", "ответ", "1" -> {
                    System.out.println("Введи слово: ");
                    String word = readLine();
                    Answer answer = game.inputValue(word);
                    System.out.printf("быки = %s, коровы = %s\n", answer.getBull(), answer.getCow());
                    game.addToLog(answer.toString());
                    dummyCheck = false;
                }
                case "log", "logs", "лог", "логи", "2" -> {
                    System.out.println(game.getLog());
                }
                case "im fool", "я дурак", "3" -> {
                    System.out.println("Добавлено 5 попыток");
                    game.addToLog("AddedTries: 5");
                    game.addTry(5);
                }
                case "restart", "рестарт", "r", "р", "4" -> {
                    game.addToLog("User restarted all program");
                    String temp = game.getLog();
                    game = choiceOfGame();
                    game.setLog(temp);
                    chooseMainOption(game);
                }
                case "end", "конец", "5" -> {
                    game.exit();
                }
                default -> {
                    dummy();
                }
            }
        }
    }

    public Game choiceOfGame() {
        Game game = null;
        boolean dummyCheck = true;
        while (dummyCheck) {
            System.out.println("Выбери язык игры:\n1. num или цифры - Я великий математик.\n2. ru или ру - Я не знаю английский язык.\n3. en или eng - Я не знаю русский язык.");
            String choice = readLine();
            switch (choice) {
                case "num", "numbers", "number", "цифры", "1" -> {
                    System.out.println("Циферные игры");
                    game = new GameNumber();
                    game.addToLog("GameLang: Numbers");
                    dummyCheck = false;
                }
                case "ru", "russian", "rus", "ру", "русский", "рус", "2" -> {
                    System.out.println("Твой путь понятен и прост");
                    game = new RuGame();
                    game.addToLog("GameLang: Russian");
                    dummyCheck = false;
                }
                case "en", "eng", "english", "англ", "английский", "3" -> {
                    System.out.println("Я это запомню");
                    game = new EnGame();
                    game.addToLog("GameLang: English");
                    dummyCheck = false;
                }
                default -> {
                    dummy();
                }
            }
        }
        return game;
    }

    public void chooseMainOption(Game game) {
        boolean dummyCheck = true;
        while (dummyCheck) {
            System.out.println("Что делаем:\n1. start или старт - Начинаем игру\n2. log или лог - Показать логи сессии\n3. change или надоело - Меняем язык игры");
            String choice = readLine();
            switch (choice) {
                case "start", "старт", "begin", "начали" -> {
                    int wordLength;
                    int maxTry;
                    try {
                        System.out.print("Длина угадываемого слова: ");
                        wordLength = Integer.parseInt(readLine());
                        System.out.println("Количество попыток: ");
                        maxTry = Integer.parseInt(readLine());
                    } catch (NumberFormatException e) {
                        dummy();
                        continue;
                    }
                    game.addToLog(String.format("wordLength = %s ,maxTry = %s", wordLength, maxTry));
                    game.start(wordLength, maxTry);
                    dummyCheck = false;
                }
                case "log", "logs", "лог", "логи", "2" -> {
                    System.out.println(game.getLog());
                }
                case "change", "надоело", "3" -> {
                    choiceOfGame();
                }
                default -> {
                    dummy();
                }
            }
        }
    }

    public String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase().trim();
    }

    void dummy() {
        System.out.println("тест на дурачка пройден и результат неутешителен");
    }
}
