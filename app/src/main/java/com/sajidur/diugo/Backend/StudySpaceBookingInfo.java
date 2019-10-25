package com.sajidur.diugo.Backend;

public class StudySpaceBookingInfo {
    private int ID;
    private String BookingDate;
    private String BookingStartTime;
    private String BookingEndTime;
    private int S_ID ;
    private String U_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getBookingStartTime() {
        return BookingStartTime;
    }

    public void setBookingStartTime(String bookingStartTime) {
        BookingStartTime = bookingStartTime;
    }

    public String getBookingEndTime() {
        return BookingEndTime;
    }

    public void setBookingEndTime(String bookingEndTime) {
        BookingEndTime = bookingEndTime;
    }

    public int getS_ID() {
        return S_ID;
    }

    public void setS_ID(int S_ID) {
        S_ID = S_ID;
    }

    public String getU_ID() {
        return U_ID;
    }

    public void setU_ID(String u_ID) {
        U_ID = u_ID;
    }
}
