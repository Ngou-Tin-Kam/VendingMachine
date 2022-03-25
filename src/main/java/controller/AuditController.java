package controller;

import model.Audit;

public class AuditController {
    private Audit model;

    public AuditController(Audit model) {
        this.model = model;
    }

    public void uploadAuditList() {
        model.uploadAuditListToFile();
    }
}
