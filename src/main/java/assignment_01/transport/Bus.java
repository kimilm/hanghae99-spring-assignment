package assignment_01.transport;

public class Bus extends PublicTransport {
    // 운행요금
    int charge;
    //기본값
    //버스
    //최대 승객 수 = 30
    //상태 = 운행
    //요금 = 1000
    //대중교통
    //주유량 = 100
    //속도 = 0
    public Bus() {
        super(30, "운행중");
        this.charge = 1000;
    }

    public Bus(int number,
               int fuel,
               int speed,
               int maxPassengerVolume,
               int passenger,
               String status,
               int charge) {
        super(number,
                fuel,
                speed,
                maxPassengerVolume,
                passenger,
                status);
        this.charge = charge;
    }

    @Override
    public boolean getOn(int passenger) {
        if (!getStatus().equals("운행중")) {
            System.out.println("warning: 운행중이지 않은 차량에 탑승할 수 없습니다.");
            return false;
        }
        // 탑승처리
        boolean result = super.getOn(passenger);
        // 요금 부과
        if (result) {
            setIncome(getIncome() + passenger * charge);
        }
        return result;
    }

    @Override
    public void refuel(int fuel) {
        super.refuel(fuel);
        if (getFuel() < 10) {
            setStatus("차고지행");
        } else {
            setStatus("운행중");
        }
    }
}
