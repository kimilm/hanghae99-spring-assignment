package assignment_01.transport;

public class Taxi extends PublicTransport {
    // 목적지
    String destination;
    // 기본 거리
    int defaultDistance;
    // 목적지까지 거리
    int destinationDistance;
    // 기본요금
    int defaultCharge;
    // 거리당요금
    int distanceCharge;

    public Taxi() {
        super(4, "일반");
        this.destination = "";
        this.defaultCharge = 3000;
        this.distanceCharge = 1000;
        this.defaultDistance = 1;
        this.destinationDistance = 0;
    }

    @Override
    public boolean onDrive() {
        boolean result = super.onDrive();
        if (result) {
            setStatus("일반");
        }
        return result;
    }

    public boolean getOn(int passenger, String destination, int destinationDistance) {
        if (!getStatus().equals("일반")) {
            System.out.println("warning: 일반 상태의 차량에만 탑승 가능합니다.");
            return false;
        }
        // 탑승처리
        boolean result = super.getOn(passenger);
        // 목적지 설정 및 상태 변경
        if (result) {
            this.destination = destination;
            this.destinationDistance = destinationDistance;
            setStatus("운행중");
        }
        return result;
    }

    public boolean getOff() {
        super.getOff(getPassenger());
        if (getStatus().equals("운행중")) {
            setStatus("일반");
        }
        setIncome(getIncome() + pay());
        return true;
    }

    @Override
    public void refuel(int fuel) {
        super.refuel(fuel);
        if (getFuel() < 10) {
            setStatus("운행불가");
        } else {
            setStatus("일반");
        }
    }

    public void print() {
        super.print();
        System.out.println("기본 요금: " + this.defaultCharge);
        System.out.println("목적지: " + this.destination);
        System.out.println("목적지까지 거리: " + this.destinationDistance + "km");
        System.out.println("지불할 요금: " + pay());
        System.out.println("상태: " + getStatus() + "\n");
    }

    private int pay() {
        return (this.destinationDistance - this.defaultDistance) * this.distanceCharge + defaultCharge;
    }
}
