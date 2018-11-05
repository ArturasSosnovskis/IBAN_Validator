package com.asosnovskis;

import java.math.BigInteger;

public class IBAN_Validator {

    public boolean IBAN_Validation(String iban) {
        iban = iban.replaceAll("\\s+", "");

        if (!check_IBAN_SymbolCountByCountryCode(iban)) {
            return false;
        }
        return divisionByMod97(rearrange_IBAN_ToIntegerNumber(iban));
    }

    /**
     * Divides the IBAN that is modified by the first 3 steps of the IBAN validaton logic by .
     *
     * @param intIBAN
     * @return boolean
     */

    private boolean divisionByMod97(BigInteger intIBAN) {
        BigInteger constant1 = new BigInteger("97");
        BigInteger constant2 = new BigInteger("1");

        return intIBAN.mod(constant1).equals(constant2);
    }

    /**
     * Rearranges a given IBAN number to an integer number.
     * <br>(Contains the second and third steps in the IBAN validation logic. </br>
     *
     * @param iban IBAN number being validated
     * @return BigInteger value
     */

    private static BigInteger rearrange_IBAN_ToIntegerNumber(String iban) {
        iban = (iban.substring(4) + iban.substring(0, 4)).toUpperCase();
        String[] ibanAsStringArray = iban.split("");


        for (int i = 0; i < ibanAsStringArray.length; i++) {
            char c = ibanAsStringArray[i].charAt(0);
            if (Character.isLetter(c)) {
                ibanAsStringArray[i] = ("" + (c - '7'));
            }
        }
        BigInteger rearrangedIBAN = new BigInteger(String.join("", ibanAsStringArray));
        return rearrangedIBAN;
    }

    /**
     * Checks the IBAN length by the country code in the IBAN.
     * <br>(Contains the first step in the IBAN validation logic)</br>
     *
     * @param iban IBAN number being validated
     * @return boolean
     */

    private static boolean check_IBAN_SymbolCountByCountryCode(String iban) {
        int min_IBAN_SymbolCount = 15;
        int max_IBAN_SymbolCount = 32;


        if ((iban.length() < min_IBAN_SymbolCount) || (iban.length() > max_IBAN_SymbolCount)) {
            return false;
        }
        String countryCode = iban.substring(0, 2).toLowerCase();
        int IBAN_SymbolCount;
        switch (countryCode) {
            case "no":
                IBAN_SymbolCount = 15;
                break;

            case "be":
                IBAN_SymbolCount = 16;
                break;

            case "fo":
            case "gl":
            case "dk":
            case "fi":
            case "nl":
                IBAN_SymbolCount = 18;
                break;

            case "mk":
            case "si":
                IBAN_SymbolCount = 19;
                break;

            case "at":
            case "ba":
            case "ee":
            case "kz":
            case "xk":
            case "lt":
            case "lu":
                IBAN_SymbolCount = 20;
                break;

            case "hr":
            case "lv":
            case "li":
            case "ch":
                IBAN_SymbolCount = 21;
                break;

            case "bh":
            case "bg":
            case "cr":
            case "ge":
            case "de":
            case "ie":
            case "me":
            case "rs":
            case "gb":
                IBAN_SymbolCount = 22;
                break;

            case "gi":
            case "il":
            case "tl":
            case "ae":
            case "iq":
                IBAN_SymbolCount = 23;
                break;

            case "ad":
            case "cz":
            case "md":
            case "pk":
            case "ro":
            case "sa":
            case "sk":
            case "es":
            case "se":
            case "tn":
            case "vg":
                IBAN_SymbolCount = 24;
                break;

            case "pt":
            case "st":
                IBAN_SymbolCount = 25;
                break;

            case "is":
            case "tr":
                IBAN_SymbolCount = 26;
                break;

            case "fr":
            case "gr":
            case "it":
            case "mc":
            case "mr":
            case "sm":
                IBAN_SymbolCount = 27;
                break;

            case "al":
            case "az":
            case "cy":
            case "do":
            case "gt":
            case "hu":
            case "lb":
            case "pl":
            case "by":
            case "sv":
                IBAN_SymbolCount = 28;
                break;

            case "br":
            case "ps":
            case "qa":
            case "ua":
                IBAN_SymbolCount = 29;
                break;

            case "jo":
            case "kw":
            case "mu":
                IBAN_SymbolCount = 30;
                break;

            case "mt":
            case "sc":
                IBAN_SymbolCount = 31;
                break;

            case "lc":
                IBAN_SymbolCount = 32;
                break;

            default:
                IBAN_SymbolCount = -1;
        }
        return IBAN_SymbolCount == iban.length();
    }
}