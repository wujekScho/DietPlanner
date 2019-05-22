package pl.wujekscho.dietplanner.entity;

public enum ProductType {
    NABIAŁ("Nabiał"),
    OWOCE("Owoc"),
    PESTKI_NASIONA_SYPKIE("Pestki/Nasiona/Sypkie"),
    PIECZYWO("Pieczywo"),
    PRODUKTY_ŚNIADANIOWE("Produkty śniadaniowe"),
    PRZYPRAWY("Przyprawa"),
    RYBY_MIĘSA("Ryba/Mięso"),
    RYŻE_MAKARONY_KASZE("Ryż/Makaron/Kasza"),
    WARZYWA("Warzywo"),
    ZIELENINA("Zielenina"),
    INNE("Inne"),
    MROŻONKI("Mrożonka"),
    DO_PIECZENIA("Do pieczenia");
    private final String type;

    ProductType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
