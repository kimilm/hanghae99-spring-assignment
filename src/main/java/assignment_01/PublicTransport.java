package assignment_01;

public class PublicTransport {
    //차량번호
    private int number;
    //주유량
    private int fuelVolume;
    //속도
    private int speed;
    //최대 승객 수
    private int maxPassengerVolume;
    //있을 경우 {기타 공통 요소들}
    //탑승중인 승객 수
    private int passenger;
    //차량상태
    //private enum status;
    private String status;

    //기본값
    //주유량 = 100
    //속도 = 0
    public PublicTransport() {
        //주유량 100
        fuelVolume = 100;
        //속도 0
        speed = 0;
    }

    //기능
    //운행 시작
    public void onDrive() {
        this.status = "운행중";
        //speed++;
    }

    //속도 변경
    public void setSpeed(int speed) {
        int temp = this.speed + speed;
        if (temp < 0) {
            return;
        }
        this.speed = speed;
    }

    //상태 변경
    public void setStatus(String status) {
        this.status = status;
    }

    //승객 탑승
    public boolean getOn(int passenger) {
        int on = this.passenger + passenger;
        if (maxPassengerVolume < on) {
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

    //**있을 경우** {기타 공통 기능들}
    //주유하기
    public boolean refuel(int fuel) {
        this.fuelVolume += fuel;
        return true;
    }
}
