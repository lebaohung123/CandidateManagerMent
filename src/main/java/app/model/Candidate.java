package app.model;

import app.exception.BirthDayException;
import app.exception.EmailException;
import app.service.ValidateData;
import java.util.Scanner;

public abstract class Candidate {
    private String candidateId;
    private String fullName;
    private String birthDay;
    private String phone;
    private String email;
    private Integer candidateType;
    public static int candidateCount = 0;
    private String certificatedId;

    public Candidate(String candidateId, String fullName, String birthDay, String phone, String email, int candidateType, String certificatedId) {
        this.candidateId = candidateId;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
        this.certificatedId = certificatedId;
        candidateCount++;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(int candidateType) {
        this.candidateType = candidateType;
    }

    public static int getCandidateCount() {
        return candidateCount;
    }
    public static void setCandidateCount(int candidateCount) {
        Candidate.candidateCount = candidateCount;
    };

    public String getCertificatedId() {
        return certificatedId;
    }

    public void setCertificatedId(String certificatedId) {
        this.certificatedId = certificatedId;
    }

    public Candidate() {
//        candidateCount++;
    }

    public abstract String showInfo();

    @Override
    public String toString() {
        return  "candidateId='" + candidateId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", candidateType=" + candidateType + " ";
    }

    public void inputBaseData(Candidate candidate){
        setCandidateCount(getCandidateCount() + 1);
        Scanner sc = new Scanner(System.in);
        ValidateData validateData = new ValidateData();
        System.out.println("Moi ban nhap candidateID: ");
        candidate.setCandidateId(sc.nextLine());
        System.out.println("Moi ban nhap fullName: ");
        candidate.setFullName(sc.nextLine());
        String birthDay;
        do {
            try {
                System.out.println("Moi ban nhap birthDay dinh dang yyyy-mmm-dd: ");
                birthDay = sc.nextLine();
                if (birthDay.isEmpty()) {
                    throw new BirthDayException("Khong duoc de trong birth day");
                }
            }catch (BirthDayException e){
                System.out.println(e.getMessage());
                birthDay = null;
            }catch (Exception e){
                System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
                birthDay = null;
            }
        }while (!validateData.validateDate(birthDay));
        candidate.setBirthDay(birthDay);
        System.out.println("Moi ban nhap phone: ");
        candidate.setPhone(sc.nextLine());
        String email;
        do {
            try {
                System.out.println("Moi ban nhap email: ");
                email = sc.nextLine();
                if (email.isEmpty()){
                    throw new EmailException("Khong duoc de trong email");
                } else if (!validateData.validateEmail(email)) {
                    throw new EmailException("Nhap sai dinh dang email");
                }
            }catch (EmailException e){
                System.out.println(e.getMessage());
                email = "";
            }catch (Exception e){
                System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
                email = "";
            }
        }while (!validateData.validateEmail(email));
        candidate.setEmail(email);
        System.out.println("Moi ban nhap certificateId: ");
        candidate.setCertificatedId(sc.nextLine());
    }
    public void updateBaseData(Candidate candidate){
        ValidateData validateData = new ValidateData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi ban chon truong muon sua: ");
        boolean check = true;
        while (check){
            System.out.println("------------------------------------------------------");
            System.out.println("Moi ban nhap so: ");
            System.out.println("1. Thay doi full name");
            System.out.println("2. Thay doi birthday");
            System.out.println("3. Thay doi phone");
            System.out.println("4. Thay doi email");
            System.out.println("5. Thay doi certificate id");
            System.out.println("6. Tiep tuc.");
            System.out.println("------------------------------------------------------");
            int choice = Integer.parseInt(sc.nextLine());
            while (choice < 1 && choice > 6){
                System.out.println("Moi ban nhap lua chon: ");
                choice = Integer.parseInt(sc.nextLine());
            }
            switch (choice) {
                case 1:
                    System.out.println("Moi ban nhap full name");
                    String fullName = sc.nextLine();
                    candidate.setFullName(fullName);
                    candidate.showInfo();
                    break;
                case 2:
                    String birthDay = "";
                    do {
                        try {
                            System.out.println("Moi ban nhap birth day");
                            birthDay = sc.nextLine();
                            if (birthDay.equals("")){
                                throw new BirthDayException("Khong duoc de trong birth day");
                            }
                            if (!validateData.validateDate(birthDay)) {
                                throw new BirthDayException("Nhap sai dinh dang ngay");
                            }
                            if (Integer.parseInt(birthDay.substring(6, 10)) < 1900) {
                                throw new BirthDayException("Nam phai > 1900");
                            }
                        }catch (BirthDayException e){
                            System.out.println(e.getMessage());
                        }catch (Exception e){
                            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
                        }
                    }while (!validateData.validateDate(birthDay));
                    candidate.setBirthDay(birthDay);
                    candidate.showInfo();
                    break;
                case 3:
                    System.out.println("Ban hay nhap so dien thoai");
                    String phone = sc.nextLine();
                    candidate.setPhone(phone);
                    candidate.showInfo();
                    break;
                case 4:
                    String email = "";
                    do {
                        try {
                            System.out.println("Moi ban nhap email: ");
                            email = sc.nextLine();
                            if (email.isEmpty()){
                                throw new EmailException("Khong duoc de trong email");
                            } else if (!validateData.validateEmail(email)) {
                                throw new EmailException("Nhap sai dinh dang email");
                            }
                        }catch (EmailException e){
                            System.out.println(e.getMessage());
                        }catch (Exception e){
                            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
                        }
                    }while (!validateData.validateEmail(email));
                    candidate.setEmail(email);
                    candidate.showInfo();
                    break;
                case 5:
                    System.out.println("Moi ban nhap cerfiticate Id");
                    String certificateId = sc.nextLine();
                    candidate.setCandidateId(certificateId);
                    candidate.showInfo();
                    break;
                case 6:
                check = false;
                break;
            }
        }
    }
}