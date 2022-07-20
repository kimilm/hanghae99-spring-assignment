import assignment_01.Bus;

public class Main {
    public static void main(String[] args) {
        // 버스 시나리오
        Bus bus = new Bus();
        // 승객 +2
        bus.getOn(2);
        // 출력
        bus.print();
        // 주유량 -50
        // 상태변경: 차고지행
        bus.refuel(-95);
        bus.getOff(2);
        bus.getOn(2);
        System.out.println("주유량: " + bus.getFuel());
        System.out.println("상태: " + bus.getStatus() + "\n");
        // 주유량 +10
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
        // 주유량 -55/
        // 주유량 알럿
        bus.getOff(5);
        bus.refuel(-55);
        // 출력 Bus
        System.out.println("\n주유량: " + bus.getFuel());
        System.out.println("상태: " + bus.getStatus());

        // 택시 시나리오
    }
}
