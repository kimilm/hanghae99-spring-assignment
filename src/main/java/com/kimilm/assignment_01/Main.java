package com.kimilm.assignment_01;

import com.kimilm.assignment_01.transport.Bus;
import com.kimilm.assignment_01.transport.Taxi;

public class Main {
    public static void main(String[] args) throws Exception {
        // 버스 시나리오
        System.out.println("---버스시나리오---");
        Bus bus = new Bus();
        Thread.sleep(10);
        Bus bus2 = new Bus();
        System.out.println("bus 1 고유번호: " + bus.getNumber());
        System.out.println("bus 2 고유번호: " + bus2.getNumber() + "\n");
        // 승객 +2
        bus.getOn(2);
        // 출력
        bus.print();
        // 주유량 -95
        // 상태변경: 차고지행
        bus.refuel(-95);
        bus.getOff(2);
        bus.getOn(2);
        System.out.println("주유량: " + bus.getFuel());
        System.out.println("상태: " + bus.getStatus() + "\n");
        // 주유량 +90
        // 상태변경: 운행중
        bus.refuel(90);
        System.out.println("주유량: " + bus.getFuel());
        System.out.println("상태: " + bus.getStatus() + "\n");
        // 승객 +45
        // 최대 승객 초과 알럿
        bus.getOn(45);
        // 승객 +5
        bus.getOn(5);
        bus.print();
        // 주유량 -55
        // 주유량 알럿
        bus.getOff(5);
        bus.refuel(-55);
        // 출력 Bus
        System.out.println("\n주유량: " + bus.getFuel());
        System.out.println("상태: " + bus.getStatus() + "\n");

        // 택시 시나리오
        System.out.println("---택시시나리오---");
        Taxi taxi = new Taxi();
        Thread.sleep(10);
        Taxi taxi2 = new Taxi();
        System.out.println("택시 1 고유번호: " + taxi.getNumber());
        System.out.println("택시 2 고유번호: " + taxi2.getNumber() + "\n");
        // 승객 +2
        // 목적지 = 서울역
        // 목적지까지 거리 = 2km
        taxi.getOn(2, "서울역", 2);
        taxi.print();
        // 주유량 -80
        taxi.refuel(-80);
        // 요금 결제
        taxi.getOff();
        System.out.println("주유량: " + taxi.getFuel());
        System.out.println("수입: " + taxi.getIncome() + "\n");
        // 승객 + 5
        taxi.getOn(5);
        // 승객 +3
        // 목적지 = 구로디지털단지역
        // 목적지까지 거리 = 12km
        taxi.getOn(3, "구로디지털단지역", 12);
        taxi.print();
        // 주유량 -20
        taxi.refuel(-20);
        // 요금 결제
        taxi.getOff();
        System.out.println("주유량: " + taxi.getFuel());
        System.out.println("상태: " + taxi.getStatus());
        System.out.println("수입: " + taxi.getIncome() + "\n");
    }
}
