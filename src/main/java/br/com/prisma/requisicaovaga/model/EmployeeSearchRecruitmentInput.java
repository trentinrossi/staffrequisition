package br.com.prisma.requisicaovaga.model;

public class EmployeeSearchRecruitmentInput {

    private String q;
    private String jobPositionId;
    private String origin;
    private String activeEmployeeId;

    public EmployeeSearchRecruitmentInput() {
    }

    public EmployeeSearchRecruitmentInput(String q, String jobPositionId, String origin, String activeEmployeeId) {
        this.q = q;
        this.jobPositionId = jobPositionId;
        this.origin = origin;
        this.activeEmployeeId = activeEmployeeId;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getJobPositionId() {
        return jobPositionId;
    }

    public void setJobPositionId(String jobPositionId) {
        this.jobPositionId = jobPositionId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getActiveEmployeeId() {
        return activeEmployeeId;
    }

    public void setActiveEmployeeId(String activeEmployeeId) {
        this.activeEmployeeId = activeEmployeeId;
    }

    @Override
    public String toString() {
        return "EmployeeSearchRecruitment{" + "q=" + q + ", jobPositionId=" + jobPositionId + ", origin=" + origin + ", activeEmployeeId=" + activeEmployeeId + '}';
    }

}
