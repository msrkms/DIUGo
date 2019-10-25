package com.sajidur.diugo.Backend;

public class MyUrl {
    public static final String Host="http://msrkmstest-001-site1.gtempurl.com";
    public static final String ApiURl=Host+"/Api";

    public static final String GetAllLabs=ApiURl+"/Lab";
    public static final String getComputersPart1 =ApiURl+"/Lab/";
    public static final String getComputersPart2 ="/Computer";
    public static final String GetComputerBookingInfoPart1=ApiURl+"/Computer/";
    public static final String getGetComputerBookingInfoPart2="/ComputerBookingInfo/";
    public static final String BookComputer=ApiURl+"/ComputerBookingInfo";


    public static final String GetEmployeeByCategoryPart1=ApiURl+"/EmployeeCatagory/";
    public static final String GetEmployeeByCategoryPart2= "/Employee";



    public static final String GetAllStudySpace=ApiURl+"/StudyPlace";
    public static final String getStudySpaceSeatPart1 =ApiURl+"/StudyPlace/";
    public static final String getStudySpaceSeatPart2 ="/StudySeat";

    public static final String GetStudySpaceBookingInfoPart1=ApiURl+"/StudySeat/";
    public static final String GetStudySpaceBookingInfoPart2="/ComputerBookingInfo/";
    public static final String BookStudySpace=ApiURl+"/StudySpaceBookingInfo";

}
