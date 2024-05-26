package app.repository;

import app.model.Certificate;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Le Bao Hung(HungLB4)
 * @since 30/06/2024
 */
public interface ICertificateRepository {
    List<Certificate> getCertificates();
    Certificate getCertificateById(String certificateID) throws SQLException;
    void deleteCertificate(String certificateID);
}
