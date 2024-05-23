package app.model;

import java.util.Scanner;

public class Experience extends Candidate{
    private int expInYear;
    private String proSkill;

    public Experience() {
    }

    public Experience(String candidateId, String fullName, String birthDay, String phone, String email, int candidateType, String certificatedId, int expInYear, String proSkill) {
        super(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public String showInfo() {
        return super.toString() + " Experience{" +
                "expInYear=" + expInYear +
                ", proSkill='" + proSkill + '\'' +
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
            Experience experience = (Experience) candidate;
            candidate.setCandidateType(0);
            System.out.println("Moi ban nhap so nam kinh nghiem: ");
            experience.setExpInYear(Integer.parseInt(sc.nextLine()));
            System.out.println("Moi ban nhap proSkill: ");
            experience.setProSkill(sc.nextLine());
            experience.showInfo();
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
            System.out.println("Moi ban nhap lua chon sua Experice: ");
            System.out.println("1. Thay doi so nam kinh nghiem");
            System.out.println("2. Thay doi proskill");
            System.out.println("3. Thoat");
            System.out.println("------------------------------------------------------");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Moi ban nhap so nam kinh nghiem");
                    int expInYear = Integer.parseInt(sc.nextLine());
                    ((Experience) candidate).setExpInYear(expInYear);
                    candidate.showInfo();
                    break;
                case 2:
                    System.out.println("Moi ban nhap proSkill");
                    String proSkill = sc.nextLine();
                    ((Experience) candidate).setProSkill(proSkill);
                    break;
                case 3:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }
}
