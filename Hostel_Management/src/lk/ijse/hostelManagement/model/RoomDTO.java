package lk.ijse.hostelManagement.model;

public class RoomDTO {
    private long roomTypeId;
    private String type;
    private String keyMoney;
    private int qty;

    public RoomDTO() {
    }

    public RoomDTO(long roomTypeId, String type, String keyMoney, int qty) {
        this.roomTypeId = roomTypeId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(String keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomTypeId=" + roomTypeId +
                ", type='" + type + '\'' +
                ", keyMoney='" + keyMoney + '\'' +
                ", qty=" + qty +
                '}';
    }
}
