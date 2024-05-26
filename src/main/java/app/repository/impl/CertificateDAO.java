package app.repository.impl;

import app.model.Certificate;
import app.repository.ICertificateRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Le Bao Hung(HungLB4)
 * @since 30/06/2024
 */
public class CertificateDAO implements ICertificateRepository {
    Connection conection = ConnectDB.getConnection();
    private final String SHOW_ALL = "SELECT * FROM certificate";
    private final String SHOW_BY_ID = "SELECT * FROM certificate WHERE id = ?";
    private final String Delete_BY_ID = "DELETE FROM certificate WHERE id = ?";
    /**
     * @return
     */
    @Override
    public List<Certificate> getCertificates() {
        List<Certificate> certificates = new ArrayList<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Certificate certificate;
        try {
            statement = conection.prepareStatement(SHOW_ALL);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                certificate = createCertificate(resultSet);
                certificates.add(certificate);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return certificates;
    }

    /**
     * @param certificateID
     * @return
     */
    @Override
    public Certificate getCertificateById(String certificateID) throws SQLException {
        Certificate certificate = null;
        PreparedStatement stmt = conection.prepareStatement(SHOW_BY_ID);
        try {
            stmt.setString(1, certificateID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                certificate = createCertificate(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return certificate;
    }

    /**
     * @param certificateID
     */
    @Override
    public void deleteCertificate(String certificateID) {
        try {
            Certificate certificate = getCertificateById(certificateID);
            int count = 0;
            if (certificate != null){
                PreparedStatement stmt = conection.prepareStatement(SHOW_BY_ID);
                stmt.setString(1, certificateID);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    count++;
                }
                if (count > 0){
                    PreparedStatement deleteStmt = conection.prepareStatement(Delete_BY_ID);
                    deleteStmt.setString(1, certificateID);
                    deleteStmt.executeQuery();
                    System.out.println("Certificate deleted");
                    deleteStmt.close();
                }else{
                    System.out.println("The certificate " + certificateID + " does not exist");
                }
            conection.close();
            rs.close();
            }else {
                throw new SQLException("Certificate not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Certificate createCertificate(ResultSet rs) throws SQLException {
        String certificateID = rs.getString("certificateID");
        String certificateName = rs.getString("certificateName");
        String certificateRank = rs.getString("certificateRank");
        String certificateDate = rs.getString("certificateDate");
        return new Certificate(certificateID, certificateName, certificateRank, certificateDate);
    }
}
