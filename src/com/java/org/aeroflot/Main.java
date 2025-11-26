package org.aeroflot;

import org.aeroflot.model.*;
import org.aeroflot.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dispatcher dispatcher = new Dispatcher();
        Administrator administrator = new Administrator();

        List<CrewMember> crew = new ArrayList<>();
        Airplane airplane = null;
        Flight flight = null;

        boolean running = true;

        while (running) {
            // Главное меню
            System.out.println("\n=== Меню ===");
            System.out.println("1. Создать самолет");
            System.out.println("2. Добавить члена экипажа");
            System.out.println("3. Создать рейс");
            System.out.println("4. Проверить погодные условия");
            System.out.println("5. Отменить рейс");
            System.out.println("6. Показать информацию о рейсе");
            System.out.println("7. Выйти");
            System.out.print("Введите номер действия: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    // Создание самолета
                    System.out.print("Введите модель самолета: ");
                    String model = scanner.nextLine();
                    System.out.print("Введите вместимость самолета: ");
                    int capacity = scanner.nextInt();
                    System.out.print("Введите дальность полета самолета: ");
                    int range = scanner.nextInt();
                    airplane = new Airplane(model, capacity, range);
                    System.out.println("Самолет создан: " + airplane);
                    break;

                case 2:
                    // Добавление члена экипажа
                    System.out.print("Введите имя члена экипажа: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите роль (пилот, штурман, стюардесса и т.д.): ");
                    String role = scanner.nextLine();
                    CrewMember crewMember = new CrewMember(name, role) {};
                    crew.add(crewMember);
                    System.out.println("Член экипажа добавлен: " + crewMember);
                    break;

                case 3:
                    // Создание рейса
                    if (airplane == null) {
                        System.out.println("Ошибка: Сначала создайте самолет!");
                    } else if (crew.isEmpty()) {
                        System.out.println("Ошибка: Сначала добавьте экипаж!");
                    } else {
                        System.out.print("Введите номер рейса: ");
                        String flightNumber = scanner.nextLine();
                        flight = administrator.createFlight(flightNumber, airplane, crew);
                        System.out.println("Рейс создан: " + flight);
                    }
                    break;

                case 4:
                    // Проверка погодных условий
                    if (flight == null) {
                        System.out.println("Ошибка: Сначала создайте рейс!");
                    } else {
                        System.out.print("Плохая погода? (true/false): ");
                        boolean badWeather = scanner.nextBoolean();
                        Weather weather = new Weather(badWeather);
                        System.out.println("Погода проверена: " + (weather.isBadWeather() ? "Плохая погода" : "Хорошая погода"));
                    }
                    break;

                case 5:
                    // Отмена рейса
                    if (flight == null) {
                        System.out.println("Ошибка: Рейс еще не создан!");
                    } else {
                        System.out.print("Погода плохая? (true/false): ");
                        boolean badWeatherCancel = scanner.nextBoolean();
                        scanner.nextLine(); // очистка буфера
                        if (badWeatherCancel) {
                            dispatcher.cancelFlight(flight, new Weather(true), null); // Рейс отменен из-за плохой погоды
                        } else {
                            System.out.println("Рейс не отменен, погода хорошая.");
                        }
                    }
                    break;

                case 6:
                    // Показать информацию о рейсе
                    if (flight != null) {
                        System.out.println(flight);
                    } else {
                        System.out.println("Ошибка: Рейс не создан.");
                    }
                    break;

                case 7:
                    // Выход
                    running = false;
                    System.out.println("Программа завершена.");
                    break;

                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }
}
