import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true){
            System.out.println("1 - ввести данные \n2 - посмотреть формат\n3 - выход");
            System.out.print(">>");
            Scanner scanner = new Scanner(System.in);
            Integer in = scanner.nextInt();
            if (in == 1) {
                try {
                    inputData();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (in == 2) printFormData();
            else if (in == 3) break;
            else System.err.println("Не правильный ввод!!!");
        }
        System.out.println("Завершение...");

    }
    public static void inputData() throws IOException{
        System.out.println("Введите свои данные в формате: ");
        System.out.println("Фамилия Имя Отчество дата.рождения номер.телефона пол");
        System.out.print(">> ");
        String[] arr = input();
        Human human = createHuman(arr);
            saveFile(human);
    }
    public static void printFormData(){
        System.out.println("-------------------------");
        System.out.println("Форматы данных:\n" +
                "фамилия, имя, отчество - строки\n" +
                "дата рождения - строка формата dd.mm.yyyy\n" +
                "номер телефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.");
        System.out.println("-------------------------");
    }
    public static String[] input(){
        Scanner scanner = new Scanner(System.in);
        String inHuman = scanner.nextLine();
        String[] arr = inHuman.split(" ");
        if (arr.length != 6) throw new RuntimeException("Проверьте введенные данные, вы ввели меньше или больше данных чем требуеться ");
        return arr;
    }

    public static Human createHuman(String[] arr) {
        String surname = arr[0];
        String name = arr[1];
        String lastname = arr[2];
        String birth = isNotDate(arr[3]);
        String phone;
        if (arr[4].length() == 11) phone =  arr[4];
        else throw new RuntimeException("Не верный номер!!!");
        Character gender = arr[5].charAt(0);
        return new Human(surname, name, lastname, birth, phone, gender);
    }
    public static String isNotDate(String date){
        String[] arr = date.split("\\.");
        ArrayList<Integer> arrInt = new ArrayList<>();
        for (String el : arr) {
            arrInt.add(Integer.parseInt(el));
        }
        if (arr.length != 3) throw new RuntimeException("Не верный фориат даты!!!");
        else if (arrInt.get(0) < 0 && arrInt.get(0) > 31 && arrInt.get(0).toString().length() < 3) throw new RuntimeException("Токого дня не существует!!!");
        else if (arrInt.get(1) > 12 && arrInt.get(1) < 0 && arrInt.get(1).toString().length() < 3) throw new RuntimeException("Токого месяца не существет!!!");
        else if (arrInt.get(2).toString().length() > 5) throw  new RuntimeException("Не верный год!!!");
        return date;
    }
    public static void saveFile(Human human) throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(human.getSurname()+".txt"), true)){
            fileWriter.write(human.toString() + "\n");
        }
    }
}