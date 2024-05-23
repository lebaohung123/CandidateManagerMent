package app.model;

import java.util.Scanner;

public class Intern extends Candidate{
    private String majors;
    private String semester;
    private String universityName;

    public Intern() {
    }

    public Intern(String candidateId, String fullName, String birthDay, String phone, String email, int candidateType, String certificatedId, String majors, String semester, String universityName) {
        super(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId);
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String showInfo() {
        return super.toString() + "Intern{" +
                "majors='" + majors + '\'' +
                ", semester='" + semester + '\'' +
                ", universityName='" + universityName + '\'' +
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
           Intern intern = (Intern) candidate;
           candidate.setCandidateType(2);
           System.out.println("Moi ban nhap chuyen nganh hoc: ");
           intern.setMajors(sc.nextLine());
           System.out.println("Moi ban nhap ky dang  hoc: ");
           intern.setSemester(sc.nextLine());
           System.out.println("Moi ban nhap truong dang hoc: ");
           intern.setUniversityName(sc.nextLine());
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    /**
     * @param candidate
     */
    @Override
    public void updateBaseData(Candidate candidate) {
        Scanner sc = new Scanner(System.in);
        super.updateBaseData(candidate);
        boolean check = true;
        while (check){
            System.out.println("------------------------------------------------------");
            System.out.println("Moi ban nhap lua chon sua Intern: ");
            System.out.println("1. Moi ban nhap chuyen nganh hoc: ");
            System.out.println("2. Moi ban nhap ky dang hoc: ");
            System.out.println("3. Moi ban nhap truong dang hoc: ");
            System.out.println("4. Thoat");
            System.out.println("------------------------------------------------------");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Moi ban nhap chuyen nganh hoc");
                    String majors = sc.nextLine();
                    ((Intern) candidate).setMajors(majors);
                    candidate.showInfo();
                    break;
                case 2:
                    System.out.println("Moi ban nhap ky dang hoc");
                    String semester= sc.nextLine();
                    ((Intern) candidate).setSemester(semester);
                    candidate.showInfo();
                    break;
                case 3:
                    System.out.println("Moi ban nhap truong dang hoc");
                    String universityName = sc.nextLine();
                    ((Intern) candidate).setUniversityName(universityName);
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
