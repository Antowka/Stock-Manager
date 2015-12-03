package ru.antowka.stock.model;

import javax.persistence.*;

/**
 * Created by Anton Nik on 27.11.15.
 */
@Entity
@Table(name = "operation_type")
public class OperationType {

    @Id
    @GeneratedValue
    @Column(name = "operation_type_id")
    private int operationTypeId;

    @Column(name = "operation_type_name")
    private String operationTypeName;

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    public int getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }
}
