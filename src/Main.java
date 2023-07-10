import java.util.Scanner;

class Main {

    static Scanner scanner = new Scanner(System.in);
    static String userName;
    static String password;
    static String[][] suppliers = new String[0][2];
    static String[][] items = new String[0][4];
    static String[][] priceQty = new String[0][2];
    static String[] categorys = new String[0];

    public static void main(String[] args) {
        loginPage();
    }

    public static void loginPage() {
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\tLOGIN PAGE                               \t|");
        System.out.println("+---------------------------------------------------------------------------------------+");

        userName = "Sithum";
        password = "2002";

        while (true) {
            System.out.print("\nUser Name : ");
            String inputUserName = scanner.next();
            if (inputUserName.equals(userName)) {
                while (true) {
                    System.out.print("Password : ");
                    String inputPassword = scanner.next();
                    if (inputPassword.equals(password)) {
                        clearConsole();
                        homePage();
                        break;
                    } else {
                        System.out.println("Password is incorrect. please try again!\n");
                    }
                }
                break;
            } else {
                System.out.println("User Name is invaid. please try again!");
            }
        }
    }

    private final static void clearConsole() {
        final String os = System.getProperty("os.name");
        try {
            if (os.equals("Linux")) {
                System.out.print("\033\143");
            } else if (os.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void homePage() {
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\tWELCOME TO IJSE STOCK MANAGEMENT SYSTEM\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");

        System.out.println("\n[1] Change the Credentials\t\t\t\t\t[2] Supplier Manage\n[3] Stock Manage"
                + "\t\t\t\t\t\t[4] Log out\n[5] Exit the system");

        L1:
        while (true) {

            System.out.print("\nEnter an option to continue > ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    clearConsole();
                    changeCredentials();
                    break L1;
                case "2":
                    clearConsole();
                    supplierManage();
                    break L1;
                case "3":
                    clearConsole();
                    stockManage();
                    break L1;
                case "4":
                    clearConsole();
                    loginPage();
                    break L1;
                case "5":
                    clearConsole();
                    System.out.println("Exiting....");
                    System.exit(5);
                    break;
                default:
                    System.out.println("wrong input please corrent input.");
            }
        }
    }

    private static void stockManage() {
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\tSTOCK MANAGEMENT \t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");

        System.out.println("\n[1] Manage Item Categories \t\t\t\t\t[2] Add Item\n[3] Get Items Supplier Wise"
                + "\t\t\t\t\t[4] View Item\n[5] Rank Items Per unit Price\t\t\t\t\t[6] Home Page");

        L1:
        while (true) {
            System.out.print("\nEnter an option to continue > ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    clearConsole();
                    manageItemCategories();
                    break L1;
                case "2":
                    clearConsole();
                    addItem();
                    break L1;
                case "3":
                    clearConsole();
                    getItemsSupplierWise();
                    break L1;
                case "4":
                    clearConsole();
                    viewItem();
                    break L1;
                case "5":
                    clearConsole();
                    rankItemsPerunitPrice();
                    break L1;
                case "6":
                    clearConsole();
                    homePage();
                    break L1;
                default:
                    System.out.println("wrong input.please try again!");
            }
        }
    }

    private static void rankItemsPerunitPrice() {
        char yn = ' ';
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\tRANK UNIT PRICE \t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+\n");
        sortArray();
        System.out.printf("%1s%1s%1s%1s%1s%1s", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+\n");
        System.out.printf("%1s%15s%5s%15s%6s%15s%6s%13s%8s%12s%9s%15s%7s", "|", "SUPPLIER ID", "|", "ITEM CODE", "|", "DESCRIPTION", "|", "PRICE", "|", "QTY", "|", "CATEGORY", "|\n");
        System.out.printf("%1s%1s%1s%1s%1s%1s", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+\n");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("%1s%11s%9s%12s%9s%13s%8s%12s%9s%11s%10s%15s%7s", "|", items[i][1], "|", items[i][0], "|", items[i][3], "|", Double.parseDouble(priceQty[i][0]), "|", priceQty[i][1], "|", items[i][2], "|\n");
        }
        System.out.printf("%1s%1s%1s%1s%1s%1s", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+", "--------------------+\n");

        System.out.print("do you want to go stock management ? (Y/N) > ");
        yn = scanner.next().charAt(0);

        while (true) {
            if (yn == 'Y' || yn == 'y') {
                clearConsole();
                stockManage();
                break;
            } else if (yn == 'N' || yn == 'n') {
                clearConsole();
                homePage();
                break;
            } else {
                System.out.print("Wrong input please enter correct input (Y/N) > ");
                yn = scanner.next().charAt(0);
            }
        }
    }

    private static void viewItem() {
        char yn = ' ';
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\tVIEW ITEM \t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------+\n");

        for (int i = 0; i < categorys.length; i++) {
            System.out.println(categorys[i]);
            System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n");
            System.out.printf("%1s%17s%8s%20s%12s%17s%7s%15s%9s%12s%9s", "|", "SUPPLIER ID", "|", "ITEM CODE", "|", "DESCRIPTION", "|", "PRICE", "|", "QTY", "|\n");
            System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n");
            for (int j = 0; j < items.length; j++) {
                if (categorys[i].equals(items[j][2])) {
                    System.out.printf("%1s%13s%12s%17s%15s%15s%9s%14s%10s%11s%10s", "|", items[j][1], "|", items[j][0], "|", items[j][3], "|", priceQty[j][0], "|", priceQty[j][1], "|\n");
                }
            }
            System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n\n");
        }

        System.out.print("do you want to go stock management ? (Y/N) > ");
        yn = scanner.next().charAt(0);

        while (true) {
            if (yn == 'Y' || yn == 'y') {
                clearConsole();
                stockManage();
                break;
            } else if (yn == 'N' || yn == 'n') {
                clearConsole();
                homePage();
                break;
            } else {
                System.out.print("Wrong input please enter correct input (Y/N) > ");
                yn = scanner.next().charAt(0);
            }
        }
    }

    private static void getItemsSupplierWise() {

        char yn = ' ';
        do {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\tSEARCH ITEM SUPPLIER WISE \t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");
            if (items.length != 0) {
                L1:
                while (true) {
                    System.out.print("\nEnter Supplier Id : ");
                    String supId = scanner.next();
                    if (checkDuplicateSupId(supId)) {
                        System.out.println("Can't find supplier id. please try again !");
                    } else {
                        int index = findSupIdIndex(supId);
                        System.out.println("Supplier Name : " + suppliers[index][1]);

                        System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n");
                        System.out.printf("%1s%17s%8s%22s%10s%17s%7s%18s%6s%14s%7s", "|", "ITEM CODE", "|", "DESCRIPTION", "|", "UNIT PRICE", "|", "QTY ON HAND", "|", "CATEGORY", "|\n");
                        System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n");
                        for (int i = 0; i < 1; i++) {
                            System.out.printf("%1s%14s%11s%18s%14s%13s%11s%13s%11s%12s%9s", "|", items[i][0], "|", items[i][3], "|", priceQty[i][0], "|", priceQty[i][1], "|", categorys[i], "|\n");
                        }
                        System.out.printf("%1s%1s%1s%1s%1s", "+------------------------+", "-------------------------------+", "-----------------------+", "-----------------------+", "-------------------+\n");

                        System.out.print("Search successfully! do you want to another search ? (Y/N) > ");
                        yn = scanner.next().charAt(0);

                        while (true) {
                            if (yn == 'Y' || yn == 'y') {
                                clearConsole();
                                break L1;
                            } else if (yn == 'N' || yn == 'n') {
                                clearConsole();
                                stockManage();
                                break L1;
                            } else {
                                System.out.print("Wrong input please enter correct input (Y/N) > ");
                                yn = scanner.next().charAt(0);
                            }
                        }
                    }
                }
            } else {
                System.out.print("\nOOPS ! It seems that you dont't have any items in the system." +
                        "\nDo you want to add a new item ? (Y/N) > ");
                yn = scanner.next().charAt(0);
                while (true) {
                    if (yn == 'Y' || yn == 'y') {
                        clearConsole();
                        supplierManage();
                        break;
                    } else if (yn == 'N' || yn == 'n') {
                        homePage();
                        break;
                    } else {
                        System.out.print("Wrong input please enter correct input (Y/N) > ");
                        yn = scanner.next().charAt(0);
                    }
                }
            }
        } while (yn == 'Y' || yn == 'y');
    }

    private static void addItem() {
        char yn = ' ';
        char yn2 = ' ';
        L1:
        do {
            System.out.println("+-------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\tADD ITEM \t\t\t\t|");
            System.out.println("+-------------------------------------------------------------------------------+");
            extendItemPriceQtyArray();
            if (categorys.length != 0) {
                if (suppliers.length != 0) {
                    while (true) {
                        System.out.print("\nItem Code : ");
                        String code = scanner.next();
                        if (checkDuplicateItemID(code)) {
                            int index = checkEmptyItemIndex();
                            items[index][0] = code;
                            System.out.println("\nSupplier list:");
                            System.out.printf("%1s%1s%15s", "----------------+", "--------------------+", "----------------------------------+\n");
                            System.out.printf("%1s%8s%8s%16s%5s%24s%12s", "|", "#", "|", "SUPPLIER ID", "|", "SUPPLIER NAME", "|\n");
                            System.out.printf("%1s%1s%15s", "----------------+", "--------------------+", "----------------------------------+\n");
                            for (int i = 0; i < suppliers.length; i++) {
                                System.out.printf("%1s%8s%8s%12s%9s%22s%14s", "|", (i + 1), "|", suppliers[i][0], "|", suppliers[i][1], "|\n");
                            }
                            System.out.printf("%1s%1s%15s", "----------------+", "--------------------+", "----------------------------------+\n");

                            System.out.print("\nEnter the suppier number > ");
                            int num = scanner.nextInt();

                            for (int i = 0; i < suppliers.length; i++) {
                                if (num == (i + 1)) {
                                    items[index][1] = suppliers[i][0];
                                }
                            }
                            System.out.println("item Categories:");
                            System.out.printf("%1s%1s", "+---------------", "+---------------------------+\n");
                            System.out.printf("%1s%8s%8s%21s%8s", "|", "#", "|", "CATEGORY NAME", "|\n");
                            System.out.printf("%1s%1s", "+---------------", "+---------------------------+\n");
                            for (int i = 0; i < categorys.length; i++) {
                                System.out.printf("%1s%8s%8s%18s%11s", "|", (i + 1), "|", categorys[i], "|\n");
                            }
                            System.out.printf("%1s%1s", "+---------------", "+---------------------------+");

                            System.out.print("\nEnter the category number > ");
                            int num2 = scanner.nextInt();

                            for (int i = 0; i < categorys.length; i++) {
                                if (num2 == (i + 1)) {
                                    items[index][2] = categorys[i];
                                }
                            }

                            System.out.print("Description : ");
                            String description = scanner.next();
                            items[index][3] = description;

                            System.out.print("Unit Price : ");
                            String unitPrice = scanner.next();
                            priceQty[index][0] = unitPrice;

                            System.out.print("Qty on hand : ");
                            String qty = scanner.next();
                            priceQty[index][1] = qty;

                            System.out.print("added successfully! do you want to add another item (Y/N) > ");
                            yn2 = scanner.next().charAt(0);

                            while (true) {
                                if (yn2 == 'Y' || yn2 == 'y') {
                                    clearConsole();
                                    continue L1;
                                } else if (yn2 == 'N' || yn2 == 'n') {
                                    clearConsole();
                                    stockManage();
                                    break L1;
                                } else {
                                    System.out.print("Wrong input please enter correct input (Y/N) > ");
                                    yn2 = scanner.next().charAt(0);
                                }
                            }
                        } else {
                            System.out.println("Category is allready exist.please try another item!");
                        }
                    }

                } else {
                    System.out.print("\nOOPS ! It seems that you dont't have any suppliers in the system." +
                            "\nDo you want to add a new supplier ? (Y/N) > ");
                    yn = scanner.next().charAt(0);
                    while (true) {
                        if (yn == 'Y' || yn == 'y') {
                            clearConsole();
                            supplierManage();
                            break;
                        } else if (yn == 'N' || yn == 'n') {
                            homePage();
                            break;
                        } else {
                            System.out.print("Wrong input please enter correct input (Y/N) > ");
                            yn = scanner.next().charAt(0);
                        }
                    }
                }
            } else {
                System.out.print("\nOOPS ! It seems that you dont't have any item categories in the system." +
                        "\nDo you want to add a new item category (Y/N) > ");
                yn = scanner.next().charAt(0);
                while (true) {
                    if (yn == 'Y' || yn == 'y') {
                        clearConsole();
                        manageItemCategories();
                        break;
                    } else if (yn == 'N' || yn == 'n') {
                        homePage();
                        break;
                    } else {
                        System.out.print("Wrong input please enter correct input (Y/N) > ");
                        yn = scanner.next().charAt(0);
                    }
                }
            }
        } while (yn2 == 'Y' || yn2 == 'y');
    }

    private static void manageItemCategories() {
        System.out.println("+---------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t MANAGE ITEM CATEGORY\t\t\t\t\t|");
        System.out.println("+---------------------------------------------------------------------------------------+");

        System.out.println("\n[1] Add New Item Category \t\t\t\t" +
                "[2] Delete Item Category\n[3] Update Item Category"
                + "\t\t\t\t[4] Stock Management");

        L1:
        while (true) {

            System.out.print("\nEnter an option to continue > ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    clearConsole();
                    addNewItemCategory();
                    break L1;
                case "2":
                    clearConsole();
                    deleteItemCategory();
                    break L1;
                case "3":
                    clearConsole();
                    updateItemCategory();
                    break L1;
                case "4":
                    clearConsole();
                    stockManage();
                    loginPage();
                    break L1;
                case "5":
                    return;
                default:
                    System.out.println("wrong input please corrent input.");
            }
        }

    }

    private static void updateItemCategory() {
        char yn = ' ';
        char yn2 = ' ';
        do {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\tUPDATE ITEM CATEGORY\t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");

            if (categorys.length != 0) {
                L1:
                while (true) {
                    System.out.print("\nCategory Name : ");
                    String name = scanner.next();

                    if (checkDuplicateCategory(name)) {
                        System.out.println("Can't find category . please try again !");
                    } else {
                        int index = findCategoryIndex(name);

                        System.out.print("Enter the new category name : ");
                        String newName = scanner.next();

                        categorys[index] = newName;


                        System.out.print("update Successfully! Do you want to update another category (Y/N) > ");
                        yn2 = scanner.next().charAt(0);

                        while (true) {
                            if (yn2 == 'Y' || yn2 == 'y') {
                                clearConsole();
                                break L1;
                            } else if (yn2 == 'N' || yn2 == 'n') {
                                clearConsole();
                                supplierManage();
                                break L1;
                            } else {
                                System.out.print("Wrong input please enter correct input (Y/N) > ");
                                yn2 = scanner.next().charAt(0);
                            }
                        }
                    }
                }
            } else {
                System.out.print("\nOOPS ! you have not added category yet.\nDo you want to add new category (Y/N) > ");
                yn = scanner.next().charAt(0);
                while (true) {
                    if (yn == 'Y' || yn == 'y') {
                        clearConsole();
                        addNewItemCategory();
                        break;
                    } else if (yn == 'N' || yn == 'n') {
                        homePage();
                        break;
                    } else {
                        System.out.print("Wrong input please enter correct input (Y/N) > ");
                        yn = scanner.next().charAt(0);
                    }
                }
            }

        } while (yn == 'Y' || yn == 'y');
    }

    private static void deleteItemCategory() {
        char yn = ' ';
        char yn2 = ' ';
        do {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t DELETE ITEM CATEGORY   \t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");

            if (categorys.length != 0) {
                L1:
                while (true) {
                    System.out.print("Category Name  : ");
                    String category = scanner.next();
                    if (checkDuplicateCategory(category)) {
                        System.out.println("Can't find category. please try again !");
                    } else {

                        int index = findCategoryIndex(category);
                        String[] tempCategory = new String[categorys.length - 1];
                        for (int i = index; i < categorys.length - 1; i++) {
                            categorys[i] = categorys[i + 1];
                        }

                        for (int i = 0; i < categorys.length - 1; i++) {
                            for (int j = 0; j < 3; j++) {
                                tempCategory[i] = categorys[i];
                            }
                        }

                        categorys = tempCategory;

                        System.out.print("Deleted successfully! do yo want to delete another (Y/N) > ");
                        yn2 = scanner.next().charAt(0);

                        while (true) {
                            if (yn2 == 'Y' || yn2 == 'y') {
                                clearConsole();
                                break L1;
                            } else if (yn2 == 'N' || yn2 == 'n') {
                                clearConsole();
                                supplierManage();
                                break L1;
                            } else {
                                System.out.print("Wrong input please enter correct input (Y/N) > ");
                                yn2 = scanner.next().charAt(0);
                            }
                        }
                    }
                }
            } else {
                System.out.print("\nOOPS ! you have not added category yet.\nDo you want to add new category (Y/N) > ");
                yn = scanner.next().charAt(0);
                while (true) {
                    if (yn == 'Y' || yn == 'y') {
                        clearConsole();
                        addNewItemCategory();
                        break;
                    } else if (yn == 'N' || yn == 'n') {
                        homePage();
                        break;
                    } else {
                        System.out.print("Wrong input please enter correct input (Y/N) > ");
                        yn = scanner.next().charAt(0);
                    }
                }
            }

        } while (yn2 == 'Y' || yn2 == 'y');
    }

    private static void addNewItemCategory() {
        char yn2 = ' ';
        L1:
        do {
            System.out.println("+-------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t ADD ITEM CATEGORY \t\t\t\t|");
            System.out.println("+-------------------------------------------------------------------------------+");
            extendCategoryArray();

            while (true) {
                System.out.print("\nEnter the new item category : ");
                String category = scanner.next();
                int index = checkEmptyItemCategoryIndex();
                if (checkDuplicateCategory(category)) {
                    categorys[index] = category;
                    System.out.print("added successfully! do you want to add another category (Y/N) > ");
                    yn2 = scanner.next().charAt(0);

                    while (true) {
                        if (yn2 == 'Y' || yn2 == 'y') {
                            clearConsole();
                            continue L1;
                        } else if (yn2 == 'N' || yn2 == 'n') {
                            stockManage();
                            break L1;
                        } else {
                            System.out.print("Wrong input please enter correct input (Y/N) > ");
                            yn2 = scanner.next().charAt(0);
                        }
                    }
                } else {
                    System.out.println("Category Is allready exist.please try another category!");
                }
            }
        } while (yn2 == 'Y' || yn2 == 'y');
    }

    private static void supplierManage() {
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\tSUPPLIER MANAGE \t\t\t\t\t|");
        System.out.println("+-----------------------------------------------------------------------------------------------+");

        System.out.println("\n[1] Add Supplier \t\t\t\t\t\t\t[2] Update Supplier\n[3] Delete Supplier"
                + "\t\t\t\t\t\t\t[4] View Suppliers\n[5] Search Supplier\t\t\t\t\t\t\t[6] Home Page");

        L1:
        while (true) {

            System.out.print("\nEnter an option to continue > ");
            String option = scanner.next();

            switch (option) {
                case "1":
                    clearConsole();
                    addSupplier();
                    break L1;
                case "2":
                    clearConsole();
                    updateSupplier();
                    break L1;
                case "3":
                    clearConsole();
                    deleteSupplier();
                    break L1;
                case "4":
                    clearConsole();
                    viewSupplier();
                    break L1;
                case "5":
                    clearConsole();
                    searchSupplier();
                    break L1;
                case "6":
                    clearConsole();
                    homePage();
                    break L1;
                default:
                    System.out.println("wrong input.please try again!");
            }
        }

    }

    private static void searchSupplier() {
        char yn = ' ';
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\tSEARCH SUPPLIER \t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------+");

        L1:
        while (true) {
            System.out.print("\nSupplier Id : ");
            String id = scanner.next();

            if (checkDuplicateSupId(id)) {
                System.out.println("Can't find supplier id. try again!");
            } else {
                int index = findSupIdIndex(id);
                System.out.println("Supplier Name : " + suppliers[index][1]);

                System.out.print("Added Successfully! Do you want to add another find (Y/N) > ");
                yn = scanner.next().charAt(0);

                while (true) {
                    if (yn == 'Y' || yn == 'y') {
                        continue L1;
                    } else if (yn == 'N' || yn == 'n') {
                        clearConsole();
                        supplierManage();
                        break L1;
                    } else {
                        System.out.print("Wrong input please enter correct input (Y/N) > ");
                        yn = scanner.next().charAt(0);
                    }
                }
            }
        }
    }

    private static void viewSupplier() {
        char yn = ' ';
        System.out.println("+-------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\tSUPPLIER VIEW \t\t\t\t\t|");
        System.out.println("+-------------------------------------------------------------------------------+");

        System.out.println("+-----------------------+-----------------------");
        System.out.println("|       SUPPLIER ID     |     SUPPLIER NAME    |");
        System.out.println("+-----------------------+----------------------+");
        for (int i = 0; i < suppliers.length; i++) {
            System.out.printf("|       %-16s|       %15s|\n", suppliers[i][0], suppliers[i][1]);
        }
        System.out.printf("%1s%1s%15s", "+-----------------------", "+", "----------------------" + "+\n");

        System.out.print("Do you want to go supplier manage page (Y/N) > ");
        yn = scanner.next().charAt(0);

        while (true) {
            if (yn == 'Y' || yn == 'y') {
                clearConsole();
                supplierManage();
                break;
            } else if (yn == 'N' || yn == 'n') {
                return;
            } else {
                System.out.print("Wrong input please enter correct input (Y/N) > ");
                yn = scanner.next().charAt(0);
            }
        }
    }

    private static void deleteSupplier() {
        char yn = ' ';
        do {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\tDELETE SUPPLIER \t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");

            L1:
            while (true) {
                System.out.print("Supplier Id : ");
                String id = scanner.next();
                if (checkDuplicateSupId(id)) {
                    System.out.println("Can't find supplier id. please try again !");
                } else {

                    int index = findSupIdIndex(id);
                    String[][] tempSupplier = new String[suppliers.length - 1][2];
                    for (int i = index; i < suppliers.length - 1; i++) {
                        for (int j = 0; j < 2; j++) {
                            suppliers[i][j] = suppliers[i + 1][j];
                        }
                    }

                    for (int i = 0; i < suppliers.length - 1; i++) {
                        for (int j = 0; j < 2; j++) {
                            tempSupplier[i][j] = suppliers[i][j];
                        }
                    }

                    suppliers = tempSupplier;

                    System.out.print("Deleted successfully! do yo want to delete another (Y/N) > ");
                    yn = scanner.next().charAt(0);

                    while (true) {
                        if (yn == 'Y' || yn == 'y') {
                            clearConsole();
                            break L1;
                        } else if (yn == 'N' || yn == 'n') {
                            clearConsole();
                            supplierManage();
                            break L1;
                        } else {
                            System.out.print("Wrong input please enter correct input (Y/N) > ");
                            yn = scanner.next().charAt(0);
                        }
                    }
                }
            }
        } while (yn == 'Y' || yn == 'y');
    }

    private static void updateSupplier() {
        char yn = ' ';
        do {
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\tUPDATE SUPPLIER \t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");


            L1:
            while (true) {
                System.out.print("\nSupplier Id : ");
                String supId = scanner.next();

                if (checkDuplicateSupId(supId)) {
                    System.out.println("Can't find supplier id. please try again !");
                } else {
                    int index = findSupIdIndex(supId);
                    System.out.println("Supplier Name : " + suppliers[index][1]);

                    System.out.print("Enter the new supplier name : ");
                    String newName = scanner.next();

                    suppliers[index][1] = newName;
                    System.out.println(suppliers[index][1]);

                    System.out.print("update Successfully! Do you want to update another supplier (Y/N) > ");
                    yn = scanner.next().charAt(0);

                    while (true) {
                        if (yn == 'Y' || yn == 'y') {
                            clearConsole();
                            break L1;
                        } else if (yn == 'N' || yn == 'n') {
                            clearConsole();
                            supplierManage();
                            break L1;
                        } else {
                            System.out.print("Wrong input please enter correct input (Y/N) > ");
                            yn = scanner.next().charAt(0);
                        }
                    }
                }
            }
        } while (yn == 'Y' || yn == 'y');
    }

    private static void addSupplier() {
        char yn = ' ';
        do {
            extendSupplierArray();
            System.out.println("+---------------------------------------------------------------------------------------+");
            System.out.println("|\t\t\t\t\tADD SUPPLIER \t\t\t\t\t|");
            System.out.println("+---------------------------------------------------------------------------------------+");

            // for (int i = 0; i < suppliers.length; i++) {
            //    System.out.println(suppliers[i][0]);
            //}
            int idIndex = checkEmptySupIdIndex();
            int nameIndex = checkEmptySupNameIndex();
            while (true) {
                System.out.print("\nSupplier Id : ");
                String id = scanner.next();
                //   System.out.println(checkDuplicateSupId(id));
                if (checkDuplicateSupId(id)) {

                    suppliers[idIndex][0] = id;

                    System.out.print("Supplier Name : ");
                    String name = scanner.next();
                    suppliers[nameIndex][1] = name;

                    System.out.print("\nadded successfully! Do you want to add another supplier (Y/N) > ");
                    yn = scanner.next().charAt(0);

                    if (yn == 'N' || yn == 'n') {
                        clearConsole();
                        supplierManage();
                        break;
                    } else if (yn == 'Y' || yn == 'y') {
                        clearConsole();
                        break;
                    }
                } else {
                    System.out.println("already exist. try another supplier id!");
                }
            }


        } while (yn == 'Y' || yn == 'y');
    }

    private static void changeCredentials() {
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        System.out.println("|\t\t\t\t\tCREDENTIAL MANAGE\t\t\t\t\t|");
        System.out.println("+-----------------------------------------------------------------------------------------------+");
        while (true) {
            System.out.print("\nPlease enter the user name to verify it's you: ");
            String inputUserName = scanner.next();

            if (inputUserName.equals(userName)) {
                while (true) {
                    System.out.print("\nEnter your current password: ");
                    String inputPassword = scanner.next();

                    if (inputPassword.equals(password)) {
                        System.out.print("Enter your new password: ");
                        String newPassword = scanner.next();
                        password = newPassword;
                        System.out.print("\nPassword change successfully.Do you want to go home page (Y/N): ");
                        String yesNo = scanner.next();
                        clearConsole();

                        while (true) {
                            if (yesNo.equals("Y") || yesNo.equals("y")) {
                                homePage();
                                break;
                            } else if (yesNo.equals("N") || yesNo.equals("n")) {
                                return;
                            } else {
                                System.out.print("wrong input.please enter correct input (Y/N): ");
                                yesNo = scanner.next();
                            }
                        }
//                        System.out.println(password);
                        break;
                    } else {
                        System.out.println("Incorrect Password.Please try again!");
                    }
                }
                break;
            } else {
                System.out.println("Invalid User Name.Please Try again");
            }
        }
    }

    public static int checkEmptySupIdIndex() {
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][0] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int checkEmptySupNameIndex() {
        for (int i = 0; i < suppliers.length; i++) {
            if (suppliers[i][1] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int checkEmptyItemCategoryIndex() {
        for (int i = 0; i < categorys.length; i++) {
            if (categorys[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static int checkEmptyItemIndex() {
        for (int i = 0; i < items.length; i++) {
            if (items[i][0] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkDuplicateSupId(String id) {
        for (int i = 0; i < suppliers.length; i++) {
            if (id.equals(suppliers[i][0])) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDuplicateItemID(String id) {
        for (int i = 0; i < items.length; i++) {
            if (id.equals(items[i][0])) {
                System.out.println("checkDuplicateItemID");
                return false;
            }
        }
        return true;
    }

    public static boolean checkDuplicateCategory(String name) {
        for (int i = 0; i < categorys.length; i++) {
            if (name.equals(categorys[i])) {
                return false;
            }
        }
        return true;
    }

    public static int findSupIdIndex(String id) {
        for (int i = 0; i < suppliers.length; i++) {
            if (id.equals(suppliers[i][0])) {
                return i;
            }
        }
        return -1;
    }

    public static int findCategoryIndex(String name) {
        for (int i = 0; i < categorys.length; i++) {
            if (name.equals(categorys[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void extendSupplierArray() {
        String[][] suppliersTemp = new String[suppliers.length + 1][2];
        for (int i = 0; i < suppliers.length; i++) {
            for (int j = 0; j < 2; j++) {
                suppliersTemp[i][j] = suppliers[i][j];
            }
        }
        suppliers = suppliersTemp;
    }

    public static void extendItemPriceQtyArray() {
        String[][] itemsTemp = new String[items.length + 1][4];
        String[][] priceQtyTemp = new String[priceQty.length + 1][2];

        for (int i = 0; i < priceQty.length; i++) {
            for (int j = 0; j < 2; j++) {
                priceQtyTemp[i][j] = priceQty[i][j];
            }
        }

        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < 4; j++) {
                itemsTemp[i][j] = items[i][j];
            }
        }

        priceQty = priceQtyTemp;
        items = itemsTemp;
    }

    public static void extendCategoryArray() {
        String[] categoriesTemp = new String[categorys.length + 1];

        for (int i = 0; i < categorys.length; i++) {

            categoriesTemp[i] = categorys[i];
        }

        categorys = categoriesTemp;
    }

    public static void sortArray() {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - 1; j++) {
                if (Double.parseDouble(priceQty[j][0]) > Double.parseDouble(priceQty[j + 1][0])) {
                    double tempPrice = Double.parseDouble(priceQty[j][0]);
                    int tempQty = Integer.parseInt(priceQty[j][1]);
                    String tempItemId = items[j][0];
                    String tempSupId = items[j][1];
                    String tempCategory = items[j][2];
                    String tempDescription = items[j][3];

                    priceQty[j][0] = priceQty[j + 1][0];
                    priceQty[j + 1][0] = String.valueOf(tempPrice);

                    priceQty[j][1] = priceQty[j + 1][1];
                    priceQty[j + 1][1] = String.valueOf(tempQty);

                    items[j][0] = items[j + 1][0];
                    items[j + 1][0] = tempItemId;

                    items[j][1] = items[j + 1][1];
                    items[j + 1][1] = tempSupId;

                    items[j][2] = items[j + 1][2];
                    items[j + 1][2] = tempCategory;

                    items[j][3] = items[j + 1][3];
                    items[j + 1][3] = tempDescription;
                }
            }
        }
    }
}
