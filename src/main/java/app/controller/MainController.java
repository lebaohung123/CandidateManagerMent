package app.controller;

import app.model.Candidate;
import app.model.Experience;
import app.model.Fresher;
import app.model.Intern;
import app.repository.impl.CandidateDAO;
import app.utils.SortByCanTypeASCBirthDSC;

import java.util.*;

public class MainController {
    private static final CandidateDAO candidateDAO = new CandidateDAO();
    static List<Candidate> candidates = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        try {
            while (check){
                System.out.println("------------------------------------------------------");
                System.out.println("Moi ban chon:");
                System.out.println("1. Nhap thong tin ung vien.");
                System.out.println("2. Up date thong tin nhan vien.");
                System.out.println("3. Hien thi thong tin toan bo ung vien trong database.");
                System.out.println("4. Danh sach ten cua nhan vien trong cty.");
                System.out.println("5. Xoa nhan vien bang id.");
                System.out.println("6. Danh sach nhan vien trong cong ty khong trung candidateID");
                System.out.println("7. Thoat.");
                System.out.println("------------------------------------------------------");

                int choice = sc.nextInt();

                while (choice < 1 && choice > 7){
                    System.out.println("Nhap lua chon cua ban .");
                    choice = sc.nextInt();
                }

                switch (choice) {
                    case 1:
                        System.out.println("Nhap thong tin ung vien.");
                        nhapThongTin();
                        break;
                    case 2:
                        suaThongTin();
                        break;
                    case 3:
                        thongTinUngVien();
                        break;
                    case 4:
                        System.out.println(candidateDAO.getFullName());
                        break;
                    case 5:
                        deleteById();
                        break;
                    case 6:
                        dsNhanVienkhongTrungId();
                        break;
                    case 7:
                        System.out.println("Xin chao va hen gap lai!!!");
                        check = false;
                        break;
                    default:
                        System.out.println("Ban da nhap sai moi nhap lai");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("The system has encountered an unexpected problem, sincerely sorry !!!");
        }
    }

    public static void nhapThongTin(){
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        while (check){
            System.out.println("------------------------------------------------------");
            System.out.println("1. Experience");
            System.out.println("2. Fresher");
            System.out.println("3. Intern");
            System.out.println("4. Thoat nhap thong tin.");
            System.out.println("------------------------------------------------------");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    Candidate experience = new Experience();
                    experience.inputBaseData(experience);
                    candidates.add(experience);
                    candidateDAO.addNew(experience);
                    break;
                case 2:
                    Candidate fresher = new Fresher();
                    fresher.inputBaseData(fresher);
                    candidates.add(fresher);
                    candidateDAO.addNew(fresher);
                    break;
                case 3:
                    Candidate intern = new Intern();
                    intern.inputBaseData(intern);
                    candidates.add(intern);
                    candidateDAO.addNew(intern);
                    break;
                case 4:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }
    public static void thongTinUngVien(){
        List<Candidate> candidates = candidateDAO.getAll();
        Map<String, Candidate> candidateMap = new LinkedHashMap<>();
        for (Candidate candidate : candidates){
            if (!candidateMap.containsKey(candidate.getCandidateId())){
                candidateMap.put(candidate.getCandidateId(), candidate);
            }
        }
        List<Candidate> candidateUnique = new ArrayList<>(candidateMap.values());
         if (!candidates.isEmpty()){
            System.out.println("------------------------------------------------------");
            for (Candidate c : candidateUnique){
                System.out.println(c.showInfo());
            }
            System.out.println("------------------------------------------------------");
        }else {
            System.out.println("Khong co ung vien nao >< ");
        }
    }
    public static void suaThongTin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------------------------");
        System.out.println("Moi ban nhap id ung vien muon sua ");
        String id = sc.nextLine();
        Candidate candidate = candidateDAO.getById(id);
        if (candidate == null){
            System.out.println("Id khong hop le");
        }else{
            candidate.updateBaseData(candidate);
            candidateDAO.updateById(candidate);
        }
    }
    public static void deleteById(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("------------------------------------------------------");
            System.out.println("Delete ung vien: ");
            System.out.println("Moi ban nhap id ung vien muon xoa ");
            String id = sc.nextLine();
            candidateDAO.deleteByID(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void dsNhanVienkhongTrungId(){
        List<Candidate> candidates = candidateDAO.getAll();
        candidates.sort(new SortByCanTypeASCBirthDSC());
        for (Candidate c : candidates){
            System.out.println(c);
        }
        if (candidates.isEmpty()){
            System.out.println("Khong co ung vien nao.");
        }else {
            for (Candidate c : candidates){
                System.out.println(c);
            }
        }
    }
}
