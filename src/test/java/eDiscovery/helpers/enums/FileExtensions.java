package eDiscovery.helpers.enums;

public enum FileExtensions {
    PPTX(".pptx"),
    ODP(".odp"),
    CSV(".csv"),
    XLSX(".xlsx"),
    ODS(".ods"),
    TXT(".txt"),
    ODT(".odt"),
    DOCX(".docx"),
    PDF(".pdf"),
    ZIP(".zip");

    public final String extension;

    FileExtensions(String extension){
        this.extension = extension;
    }
}
