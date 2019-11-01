package br.com.prisma.requisicaovaga.model;

public class EmployeeG5 {

    private int numEmp;
    private int tipCol;
    private int numCad;

    public EmployeeG5() {
    }

    public EmployeeG5(int numEmp, int tipCol, int numCad) {
        this.numEmp = numEmp;
        this.tipCol = tipCol;
        this.numCad = numCad;
    }

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public int getTipCol() {
        return tipCol;
    }

    public void setTipCol(int tipCol) {
        this.tipCol = tipCol;
    }

    public int getNumCad() {
        return numCad;
    }

    public void setNumCad(int numCad) {
        this.numCad = numCad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.numEmp;
        hash = 17 * hash + this.tipCol;
        hash = 17 * hash + this.numCad;
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
        final EmployeeG5 other = (EmployeeG5) obj;
        if (this.numEmp != other.numEmp) {
            return false;
        }
        if (this.tipCol != other.tipCol) {
            return false;
        }
        if (this.numCad != other.numCad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EmployeeG5{" + "numEmp=" + numEmp + ", tipCol=" + tipCol + ", numCad=" + numCad + '}';
    }

}
