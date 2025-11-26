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
            System.out.println("\n==================================");
            System.out.println("        МЕНЮ СИСТЕМЫ АЭРОФЛОТ");
            System.out.println("==================================");
            System.out.println("1. Создать самолет");
            System.out.println("2. Добавить члена экипажа");
            System.out.println("3. Создать рейс");
            System.out.println("4. Проверить погодные условия");
            System.out.println("5. Отменить рейс");
            System.out.println("6. Показать информацию о рейсе");
            System.out.println("7. Выйти");
            System.out.println("==================================");
            System.out.print("Введите номер действия: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число!");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Введите модель самолета: ");
                        String model = scanner.nextLine();
                        System.out.print("Введите вместимость самолета: ");
                        int capacity = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите дальность полета самолета: ");
                        int range = Integer.parseInt(scanner.nextLine());
                        airplane = new Airplane(model, capacity, range);
                        System.out.println("\nСамолет успешно создан:");
                        System.out.println("----------------------------------");
                        System.out.println(airplane);
                        System.out.println("----------------------------------");
                        break;

                    case 2:
                        System.out.print("Введите имя члена экипажа: ");
                        String name = scanner.nextLine();
                        System.out.print("Введите роль (пилот, штурман, радист, стюардесса): ");
                        String role = scanner.nextLine().toLowerCase();
                        CrewMember crewMember;
                        switch (role) {
                            case "пилот":
                                crewMember = new Pilot(name);
                                break;
                            case "штурман":
                                crewMember = new Navigator(name);
                                break;
                            case "радист":
                                crewMember = new RadioOperator(name);
                                break;
                            case "стюардесса":
                                crewMember = new Stewardess(name);
                                break;
                            default:
                                throw new IllegalArgumentException("Неизвестная роль: " + role);
                        }
                        crew.add(crewMember);
                        System.out.println("\nЧлен экипажа добавлен:");
                        System.out.println("----------------------------------");
                        System.out.println(crewMember);
                        System.out.println("----------------------------------");
                        break;

                    case 3:
                        System.out.print("Введите номер рейса: ");
                        String flightNumber = scanner.nextLine();
                        flight = administrator.createFlight(flightNumber, airplane, crew);
                        System.out.println("\nРейс успешно создан:");
                        System.out.println("----------------------------------");
                        System.out.println(flight);
                        System.out.println("----------------------------------");
                        break;

                    case 4:
                        System.out.print("Плохая погода? (true/false): ");
                        boolean badWeather = Boolean.parseBoolean(scanner.nextLine());
                        Weather weather = new Weather(badWeather);
                        System.out.println("\nРезультат проверки погоды:");
                        System.out.println("----------------------------------");
                        System.out.println(weather.isBadWeather() ? "Погода плохая" : "Погода хорошая");
                        System.out.println("----------------------------------");
                        break;

                    case 5:
                        if (flight == null) {
                            System.out.println("Ошибка: Рейс еще не создан!");
                        } else {
                            System.out.print("Погода плохая? (true/false): ");
                            boolean badWeatherCancel = Boolean.parseBoolean(scanner.nextLine());
                            TechnicalFailure failure = null;
                            if (!badWeatherCancel) {
                                System.out.print("Есть техническая неисправность? (да/нет): ");
                                String answer = scanner.nextLine().toLowerCase();
                                if (answer.equals("да")) {
                                    System.out.print("Опишите неисправность: ");
                                    String description = scanner.nextLine();
                                    failure = new TechnicalFailure(description);
                                }
                            }
                            dispatcher.cancelFlight(flight, new Weather(badWeatherCancel), failure);
                        }
                        break;

                    case 6:
                        if (flight != null) {
                            System.out.println("\n==================================");
                            System.out.println("        ИНФОРМАЦИЯ О РЕЙСЕ");
                            System.out.println("==================================");
                            System.out.println("Номер рейса: " + flight.getFlightNumber());
                            System.out.println("Самолет: " + flight.getAirplane().getModel() +
                                    " | Вместимость: " + flight.getAirplane().getCapacity() +
                                    " | Дальность: " + flight.getAirplane().getRange() + " км");
                            System.out.println("----------------------------------");
                            System.out.println("Экипаж:");
                            for (CrewMember member : flight.getCrew()) {
                                System.out.println(" - " + member);
                            }
                            System.out.println("----------------------------------");
                            System.out.println("Статус: " + (flight.isCancelled() ? "ОТМЕНЕН" : "АКТИВЕН"));
                            System.out.println("==================================");
                        } else {
                            System.out.println("Ошибка: Рейс не создан.");
                        }
                        break;

                    case 7:
                        running = false;
                        System.out.println("\n==================================");
                        System.out.println("   Программа завершена. До свидания!");
                        System.out.println("==================================");
                        break;

                    default:
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                }
            } catch (FlightException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nНажмите Enter, чтобы вернуться в меню...");
                scanner.nextLine();
            }
        }
    }
}
