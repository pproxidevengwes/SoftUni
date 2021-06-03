package com.company;

import java.util.*;

public class Robotics {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputRobots = scanner.nextLine();
        String[] robotsData = inputRobots.split(";");
        Map<String, Integer> robots = getRobotsMap(robotsData);
        Map<String, Integer> robotsWorkingTime = getRobotsWorkingTimeMap(robotsData);

        String startTime = scanner.nextLine();
        int startTimeInSec = getStartTimeInSecs(startTime);

        ArrayDeque<String> products = new ArrayDeque<>();
        String product = scanner.nextLine();
        while (!product.equals("End")) {
            products.offer(product);
            product = scanner.nextLine();
        }

        while (!products.isEmpty()) {
            String current = products.poll();
            startTimeInSec++;
            decreaseWorkingTime(robotsWorkingTime);
            boolean isTaken = false;

            for (Map.Entry<String, Integer> robot : robotsWorkingTime.entrySet()) {
                if (robot.getValue() == 0) {
                    System.out.println(robot.getKey() + " - " + current + " [" + getStartTime(startTimeInSec)+"]");
                    robotsWorkingTime.put(robot.getKey(), robots.get(robot.getKey()));
                    robot.setValue(robots.get(robot.getKey()));
                    isTaken = true;
                    break;
                }
            }

            if (!isTaken) {
                products.offer(current);
            }

        }


    }

    private static void decreaseWorkingTime(Map<String, Integer> robotsWorkingTime) {
        for (Map.Entry<String, Integer> robot : robotsWorkingTime.entrySet()) {
            if (robot.getValue() > 0) {
                robotsWorkingTime.put(robot.getKey(), robot.getValue() - 1);
            }
        }
    }

    private static Map<String, Integer> getRobotsWorkingTimeMap(String[] robotsData) {
        Map<String, Integer> robots = new LinkedHashMap<>();

        for (String robotData : robotsData) {
            String name = robotData.split("-")[0];
            robots.put(name, 0);
        }
        return robots;
    }

    private static int getStartTimeInSecs(String startTime) {
        int hours = Integer.parseInt(startTime.split(":")[0]);
        int min = Integer.parseInt(startTime.split(":")[1]);
        int secs = Integer.parseInt(startTime.split(":")[2]);
        return hours * 3600 + min * 60 + secs;
    }

    private static String getStartTime(int startTimeInSec) {
        int hours = (startTimeInSec / 3600)%24;
        int min = startTimeInSec % 3600 / 60;
        int secs = startTimeInSec % 60;
        if (hours>=24) {
            hours-=24;
        }
        return String.format("%02d%s%02d%s%02d",hours, ":", min, ":", secs);
    }

    private static Map<String, Integer> getRobotsMap(String[] robotsData) {
        Map<String, Integer> robots = new LinkedHashMap<>();

        for (String robotData : robotsData) {
            String name = robotData.split("-")[0];
            int time = Integer.parseInt(robotData.split("-")[1]);
            robots.put(name, time);
        }
        return robots;
    }

}
