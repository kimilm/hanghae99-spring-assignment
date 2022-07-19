package assignment_01;

public class PublicTransport {
    //차량번호
    private int number;
    //주유량
    private int fuel;
    //속도
    private int speed;
    //최대 승객 수
    private int maxPassengerVolume;
    //있을 경우 {기타 공통 요소들}
    //탑승중인 승객 수
    private int passenger;
    //차량상태
    private String status;
    //수입
    private int income;

    //기본값
    //주유량 = 100
    //속도 = 0
    protected PublicTransport() {
        //주유량 100
        this.fuel = 100;
        //속도 0
        this.speed = 0;
        //수입 0
        this.income = 0;
    }

    protected PublicTransport(int maxPassengerVolume, String status) {
        this();
        this.maxPassengerVolume = maxPassengerVolume;
        this.status = status;
    }

    protected PublicTransport(int number, int fuel, int speed, int maxPassengerVolume, int passenger, String status) {
        this.number = number;
        this.fuel = fuel;
        this.speed = speed;
        this.maxPassengerVolume = maxPassengerVolume;
        this.passenger = passenger;
        this.status = status;
        this.income = 0;
    }

    //기능
    //운행 시작
    public void onDrive() {
        this.status = "운행중";
        //speed++;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        int temp = this.speed + speed;
        if (temp < 0) {
            return;
        }
        this.speed = speed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //승객 탑승
    public boolean getOn(int passenger) {
        int on = this.passenger + passenger;
        if (maxPassengerVolume < on) {
            System.out.println("warning: 최대 승객 수를 초과하였습니다");
            return false;
        }
        this.passenger = on;
        return true;
    }

    //승객 하차
    public boolean getOff(int passenger) {
        int off = this.passenger - passenger;
        if (off < 0) {
            return false;
        }
        this.passenger = off;
        return true;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public boolean refuel(int fuel) {
        this.fuel += fuel;
        if (this.fuel < 10) {
            System.out.println("\nwarning: " + "주유 필요");
        }
        return true;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void print() {
        System.out.println("\ninfo----");
        System.out.println("탑승 승객 수: " + this.passenger);
        System.out.println("잔여 승객 수: " + (this.maxPassengerVolume - this.passenger));
        System.out.println("총 수입: " + this.income);
    }
}
