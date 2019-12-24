package ua.epam.crud.model;

public enum AccountStatus {
    ACTIVE(1L), BANNED(2L), DELETED(3L);

    private Long id;

    AccountStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
