package com.example.baeza.fatherhome.network;

public class ApiUtils {

    private static final String THE_LEXHAM_ENGLISH_BIBLE = "LEB";
    private static final String REINA_VALERA_REVISADA = "RVR60";
    private static final String RUSSIAN_SYNODAL_BIBLE_TRANSLATION = "bb-sbb-rusbt";
    private static final String VERSAO_FACIL_DE_LER = "wbtc-ptbrnt";

    private static final String BOOK_OF_MATHEW = "Matthew";
    private static final String BOOK_OF_MARK = "Mark";
    private static final String BOOK_OF_LUKE = "Luke";
    private static final String BOOK_OF_JHON = "John";


    //text Api Styles

    private static final String fullyFormatted = "fullyFormatted";
    private static final String oneVersePerLine = "oneVersePerLine";
    private static final String oneVersePerLineFullReference = "oneVersePerLineFullReference";
    private static final String quotation = "quotation";
    private static final String simpleParagraphs = "simpleParagraphs";
    private static final String bibleTextOnly = "bibleTextOnly";
    private static final String orationOneParagraph = "orationOneParagraph";
    private static final String orationOneVersePerLine = "orationOneVersePerLine";
    private static final String orationBibleParagraphs = "orationBibleParagraphs";
    private static final String fullyFormattedWithFootnotes = "fullyFormattedWithFootnotes";


    public static String getTheLexhamEnglishBible() {
        return THE_LEXHAM_ENGLISH_BIBLE;
    }

    public static String getReinaValeraRevisada() {
        return REINA_VALERA_REVISADA;
    }

    public static String getRussianSynodalBibleTranslation() {
        return RUSSIAN_SYNODAL_BIBLE_TRANSLATION;
    }

    public static String getVersaoFacilDeLer() {
        return VERSAO_FACIL_DE_LER;
    }

    public static String getBookOfMathew() {
        return BOOK_OF_MATHEW;
    }

    public static String getBookOfMark() {
        return BOOK_OF_MARK;
    }

    public static String getBookOfLuke() {
        return BOOK_OF_LUKE;
    }

    public static String getBookOfJhon() {
        return BOOK_OF_JHON;
    }

    public static String getFullyFormatted() {
        return fullyFormatted;
    }

    public static String getOneVersePerLine() {
        return oneVersePerLine;
    }

    public static String getOneVersePerLineFullReference() {
        return oneVersePerLineFullReference;
    }

    public static String getQuotation() {
        return quotation;
    }

    public static String getSimpleParagraphs() {
        return simpleParagraphs;
    }

    public static String getBibleTextOnly() {
        return bibleTextOnly;
    }

    public static String getOrationOneParagraph() {
        return orationOneParagraph;
    }

    public static String getOrationOneVersePerLine() {
        return orationOneVersePerLine;
    }

    public static String getOrationBibleParagraphs() {
        return orationBibleParagraphs;
    }

    public static String getFullyFormattedWithFootnotes() {
        return fullyFormattedWithFootnotes;
    }
}
