package br.com.prisma.requisicaovaga.model;

import java.time.LocalDate;
import java.util.Objects;

public class StaffRequisition {

    /**
     * Identificador único da entidade
     */
    private String id;

    /**
     * Motivo de abertura da requisição de pessoal. STAFF_INCREASE, REPLACEMENT
     */
    private StaffRequisitionReason reason;

    /**
     * ID do colaborador que será substituido (Se for o caso).
     */
    private String replacedEmployeeId;

    /**
     * Status da requisição de pessoal. IN_DEFINITION, IN_APPROVAL, APPROVED,
     * REJECTED, CANCELED
     */
    private StaffRequisitionStatus status;

    /**
     * Justificativa para a abertura da requisição de pessoal.
     */
    private String justification;

    /**
     * Descrição da vaga.
     */
    private String description;

    /**
     * Data de admissão prevista do colaborador.
     */
    private LocalDate admissionDate;

    /**
     * ID do colaborador que criou a requisição de pessoal.
     */
    private String requesterId;

    private String activeEmployeeId;

    public StaffRequisition() {
    }

    public StaffRequisition(String id, StaffRequisitionReason reason, String replacedEmployeeId, StaffRequisitionStatus status, String justification, String description, LocalDate admissionDate, String requesterId, String activeEmployeeId) {
        this.id = id;
        this.reason = reason;
        this.replacedEmployeeId = replacedEmployeeId;
        this.status = status;
        this.justification = justification;
        this.description = description;
        this.admissionDate = admissionDate;
        this.requesterId = requesterId;
        this.activeEmployeeId = activeEmployeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StaffRequisitionReason getReason() {
        return reason;
    }

    public void setReason(StaffRequisitionReason reason) {
        this.reason = reason;
    }

    public String getReplacedEmployeeId() {
        return replacedEmployeeId;
    }

    public void setReplacedEmployeeId(String replacedEmployeeId) {
        this.replacedEmployeeId = replacedEmployeeId;
    }

    public StaffRequisitionStatus getStatus() {
        return status;
    }

    public void setStatus(StaffRequisitionStatus status) {
        this.status = status;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public String getActiveEmployeeId() {
        return activeEmployeeId;
    }

    public void setActiveEmployeeId(String activeEmployeeId) {
        this.activeEmployeeId = activeEmployeeId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaffRequisition other = (StaffRequisition) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StaffRequisition{" + "id=" + id + ", reason=" + reason + ", replacedEmployeeId=" + replacedEmployeeId + ", status=" + status + ", justification=" + justification + ", description=" + description + ", admissionDate=" + admissionDate + ", requesterId=" + requesterId + ", activeEmployeeId=" + activeEmployeeId + '}';
    }

}
