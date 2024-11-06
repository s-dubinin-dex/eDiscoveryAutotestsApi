package eDiscovery.helpers.admin;

public enum PredefinedRoles {

    NO_RIGHTS("Нет прав"),
    FULL_RIGHTS("Полные права"),
    FULL_WRITE("Test FullWrite Role"),
    FULL_READ("Test FullRead Role");

    public final String name;

    PredefinedRoles(String name){
        this.name = name;
    }
}
