public class ConsoleUtil {
    public static StateEnum state;
    public static void generateOutput() {
        switch (state){
            case MENU -> showMenuOutput();
            case SEARCH -> showSearchOutput();
            case HISTORY -> showHistoryOutput();
        }
    }

    private static void showHistoryOutput() {
        System.out.println("Historique");
        RedisUtil.getHistory();
        state=StateEnum.MENU;
        generateOutput();
    }

    private static void showSearchOutput() {
        System.out.println("Saisir votre recherche : ");
        System.out.println("0 to get back to main menu");

    }

    private static void showMenuOutput() {
        System.out.println("Menu Principal");
        System.out.println("1. Search");
        System.out.println("2. History");
        System.out.println("3. Clear");
    }

    public static void clearHistory() {
        System.out.println("CLearing history");
        RedisUtil.clearHistory();
        state=StateEnum.MENU;
        generateOutput();
    }
}
