package app.model;

import java.sql.Date;

public class Certificate {
    private String certificateId;
    private String certificateName;
    private String certificateRank;
    private String certificateDate;

    public Certificate() {
    }

    public Certificate(String certificateId, String certificateName, String certificateRank, String certificateDate) {
        this.certificateId = certificateId;
        this.certificateName = certificateName;
        this.certificateRank = certificateRank;
        this.certificateDate = certificateDate;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "certificateId='" + certificateId + '\'' +
                ", certificateName='" + certificateName + '\'' +
                ", certificateRank='" + certificateRank + '\'' +
                ", certificateDate=" + certificateDate +
                '}';
    }
}
