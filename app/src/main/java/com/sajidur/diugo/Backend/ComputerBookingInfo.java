package com.sajidur.diugo.Backend;

public class ComputerBookingInfo {
    private int ID;
    private String BookingDate;
    private String BookingStartTime;
    private String BookingEndTime;
    private int C_ID ;
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

    public int getC_ID() {
        return C_ID;
    }

    public void setC_ID(int c_ID) {
        C_ID = c_ID;
    }

    public String getU_ID() {
        return U_ID;
    }

    public void setU_ID(String u_ID) {
        U_ID = u_ID;
    }
}
