package controller;

import model.Audit;

public class AuditController {
    private Audit model; // Attribute to declare Audit model

    // Constructor to set the model
    public AuditController(Audit model) {
        this.model = model;
    }

    // A method to call the model
    public void uploadAuditList() {
        model.uploadAuditListToFile();
    }
}
