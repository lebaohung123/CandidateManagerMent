package app.model;

import app.exception.BirthDayException;
import app.service.ValidateData;

import java.util.Scanner;

public class Fresher extends Candidate{
    private String graduationDate;
    private String graduationRank;
    private String education;

    public Fresher() {
    }

    public Fresher(String graduationDate, String graduationRank, String education) {
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public Fresher(String candidateId, String fullName, String birthDay, String phone, String email, String certificatedId, String graduationDate, String graduationRank, String education) {
        super(candidateId, fullName, birthDay, phone, email, certificatedId);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public Fresher(String candidateId, String fullName, String birthDay, String phone, String email, int candidateType, String certificatedId, String graduationDate, String graduationRank, String education) {
        super(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Override
    public String showInfo() {
        return super.toString() + "Fresher{" +
                "graduationDate=" + graduationDate +
                ", graduationRank='" + graduationRank + '\'' +
                ", education='" + education + '\'' +
                '}';
    }

    /**
     * @param candidate
     */
    @Override
    public void inputBaseData(Candidate candidate) {
        try {
            Scanner sc = new Scanner(System.in);
            super.inputBaseData(candidate);
            Fresher fresher = (Fresher) candidate;
            candidate.setCandidateType(1);
            System.out.println("Moi ban nhap ngay tot nghiep: ");
            fresher.setGraduationDate(sc.nextLine());
            System.out.println("Moi ban nhap xep hang tot nghiep: ");
            fresher.setGraduationRank(sc.nextLine());
            System.out.println("Moi ban nhap truong tot nghiep: ");
            fresher.setEducation(sc.nextLine());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @param candidate
     */
    @Override
    public void updateBaseData(Candidate candidate) {
        ValidateData validateData = new ValidateData();
        Scanner sc = new Scanner(System.in);
        super.updateBaseData(candidate);
        boolean check = true;
        while (check){
            System.out.println("------------------------------------------------------");
            System.out.println("Moi ban nhap lua chon sua Fresher: ");
            System.out.println("1. Moi ban nhap ngay tot nghiep: ");
            System.out.println("2. Moi ban nhap xep hang tot nghiep: ");
            System.out.println("3. Moi ban nhap truong tot nghiep: ");
            System.out.println("4. Thoat ");
            System.out.println("------------------------------------------------------");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    String granduationdate = sc.nextLine();
                    while (!validateData.validateDate(granduationdate)) {
                        try {
                            System.out.println("Moi ban nhap ngay tot nghiep");
                            granduationdate= sc.nextLine();
                            if (granduationdate.isEmpty()){
                                throw new BirthDayException("Khong duoc de trong ngay tot nghiep");
                            }
                            if (!validateData.validateDate(granduationdate)) {
                                throw new BirthDayException("Nhap sai dinh dang ngay");
                            }
                        }catch (BirthDayException e){
                            System.out.println(e.getMessage());
                        }catch (Exception e){
                            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
                        }
                    }
                    ((Fresher) candidate).setGraduationDate(granduationdate);
                    candidate.showInfo();
                    break;
                case 2:
                    System.out.println("Moi ban nhap hang tot nghiep ");
                    String graduationRank= sc.nextLine();
                    ((Fresher) candidate).setGraduationRank(graduationRank);
                    candidate.showInfo();
                    break;
                case 3:
                    System.out.println("Moi ban nhap truong tot nghiep ");
                    String education= sc.nextLine();
                    ((Fresher) candidate).setEducation(education);
                    candidate.showInfo();
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }
}
