package app.repository.impl;

import app.model.Candidate;
import app.model.Experience;
import app.model.Fresher;
import app.model.Intern;
import app.repository.ICandidateRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CandidateDAO implements ICandidateRepository {
    List<Candidate> newcandidatesList = new ArrayList<>();
    private ConnectDB connectDB = new ConnectDB();
    private final String INSERT_CANDIDATE =
            "INSERT INTO CANDIDATE(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId, expInYear, proSkill, "
                    + "graduationDate, graduationRank, education, majors, semester, universityName) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SHOW_ALL = "SELECT * FROM CANDIDATE";
    private final String SHOW_CANDIDATE_BY_ID = "SELECT * FROM CANDIDATE WHERE candidateId = ?";
    private final String CHECK_ID_EXIST = "SELECT * FROM CANDIDATE WHERE candidateId = ?";
    /**
     * @return
     */
    @Override
    public boolean checkConnection() {
        connectDB.getConnection();
        return true;
    }

    /**
     * @param candidate
     */
    @Override
    public void addNew(Candidate candidate) {
        Connection connection = ConnectDB.getConnection();
        try {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(SHOW_ALL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            rs.moveToInsertRow();
            rs.updateString("candidateId", candidate.getCandidateId());
            rs.updateString("fullName", candidate.getFullName());
            rs.updateString("birthDay", candidate.getBirthDay());
            rs.updateString("phone", candidate.getPhone());
            rs.updateString("email", candidate.getEmail());
            rs.updateInt("candidateType", candidate.getCandidateType());
            rs.updateString("certificatedId", candidate.getCertificatedId());
            if (candidate instanceof Experience){
                rs.updateInt("expInYear", ((Experience) candidate).getExpInYear());
                rs.updateString("proSkill", ((Experience) candidate).getProSkill());
            }
            if (candidate instanceof Fresher){
                rs.updateString("graduationDate", ((Fresher) candidate).getGraduationDate());
                rs.updateString("graduationRank", ((Fresher) candidate).getGraduationRank());
                rs.updateString("education", ((Fresher) candidate).getEducation());
            }
            if (candidate instanceof Intern){
                rs.updateString("majors", ((Intern) candidate).getMajors());
                rs.updateString("semester", ((Intern) candidate).getSemester());
                rs.updateString("universityName", ((Intern) candidate).getUniversityName());
            }
            rs.insertRow();
            stmt.close();
            connection.close();
            newcandidatesList.add(candidate);
        }catch (Exception e){
            e.printStackTrace();
        }
        getNewList();
    }
    /**
     * @return
     */
    @Override
    public List<Candidate> getAll() {
        Connection connection = ConnectDB.getConnection();
        List<Candidate> candidates = new ArrayList<>();
        Candidate candidate = null;
        try {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(SHOW_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                candidate = getCandidate(candidate, rs);
                candidates.add(candidate);
                assert candidate != null;
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    private Candidate getCandidate(Candidate candidate, ResultSet rs) throws SQLException {
        String candidateId = rs.getString("candidateId");
        String fullName = rs.getString("fullName");
        String birthDay = rs.getString("birthDay");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        int candidateType = rs.getInt("candidateType");
        String certificatedId = rs.getString("certificatedId");
        if (candidateType == 0){
            int expInYear = rs.getInt("expInYear");
            String proSkill = rs.getString("proSkill");
            candidate = new Experience(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId, expInYear, proSkill);
        }
        if (candidateType == 1){
            String graduationDate = rs.getString("graduationDate");
            String graduationRank = rs.getString("graduationRank");
            String education = rs.getString("education");
            candidate = new Fresher(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId,graduationDate ,graduationRank,education);
        }
        if (candidateType == 2){
            String majors = rs.getString("majors");
            String semester = rs.getString("semester");
            String universityName = rs.getString("universityName");
            candidate = new Intern(candidateId, fullName, birthDay, phone, email, candidateType, certificatedId, majors, semester, universityName);
        }
        return candidate;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Candidate getById(String id) {
        Connection connection = ConnectDB.getConnection();
        try {
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(SHOW_CANDIDATE_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            Candidate candidate = null;
            while (rs.next()) {
                candidate = getCandidate(candidate, rs);
            }
            connection.close();
            stmt.close();
            return candidate;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param candidate
     */
    @Override
    public void updateById(Candidate candidate) {
        Connection connection = ConnectDB.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(CHECK_ID_EXIST, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, candidate.getCandidateId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                rs.absolute(1);
                rs.updateString("fullName", candidate.getFullName());
                rs.updateString("birthDay", candidate.getBirthDay());
                rs.updateString("phone", candidate.getPhone());
                rs.updateString("email", candidate.getEmail());
                rs.updateInt("candidateType", candidate.getCandidateType());
                rs.updateString("certificatedId", candidate.getCertificatedId());
                if (candidate.getCandidateType() == 0){
                    rs.updateInt("expInYear", ((Experience) candidate).getExpInYear());
                    rs.updateString("proSkill", ((Experience) candidate).getProSkill());
                }
                if (candidate.getCandidateType() == 1){
                    rs.updateString("graduationDate", ((Fresher) candidate).getGraduationDate());
                    rs.updateString("graduationRank", ((Fresher) candidate).getGraduationRank());
                    rs.updateString("education", ((Fresher) candidate).getEducation());
                }
                if (candidate.getCandidateType() == 2){
                    rs.updateString("majors", ((Intern) candidate).getMajors());
                    rs.updateString("semester", ((Intern) candidate).getSemester());
                    rs.updateString("universityName", ((Intern) candidate).getUniversityName());
                }
            }
            rs.updateRow();
            stmt.close();
            connection.close();
            System.out.println("Update thanh cong!!!");
        }catch (SQLException e){
            System.err.println("The system has encountered an unexpected problem, sincerely sorry !!! ");
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    @Override
    public StringBuffer getFullName() {
        StringBuffer fullName = new StringBuffer();
        List<Candidate> candidates = getAll();
        for (Candidate candidate : candidates){
            fullName.append(candidate.getFullName()).append(", ");
        }
        return fullName;
    }

    /**
     * @return
     */
    @Override
    public void getNewList() {
        System.out.println("So ung vien vua nhap la: " + Candidate.getCandidateCount());
        if (newcandidatesList.size() <= 0){
            System.out.println("Khong co ung vien");
        }else {
            for (Candidate candidate: newcandidatesList){
                System.out.println(candidate.showInfo());
            }
        }
    }
}