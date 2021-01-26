package ir.rezvansoft.cryptowallet.service;

public class CurrencyChangeName {




    public static String entopersiannamechanger(String name){
        String currencyname="";

        switch (name){

            case "USD" :
                currencyname="دلار";
                break;
            case "EUR" :
                currencyname="یورو";
                break;
            case "CNY" :
                currencyname="یوان چین";
                break;
            case "GBP" :
                currencyname="پوند انگلیس";
                break;
            case "JPY" :
                currencyname="ین ژاپن";
                break;
            case "PLN" :
                currencyname="زلوتی لهستان";
                break;
            case "RUB" :
                currencyname="روبل روسیه";
                break;
            case "SGD" :
                currencyname="دلار سنگاپور";
                break;
            case "AUD" :
                currencyname="دلار استرالیا";
                break;
            case "HKD" :
                currencyname="دلار هنگ کنگ";
                break;
            case "CHF" :
                currencyname="CHF";
                break;
            case "INR" :
                currencyname="روپیه هند";
                break;
            case "NZD" :
                currencyname="دلار زلاند نو";
                break;
            case "BRL" :
                currencyname="رئال برزیل";
                break;
            case "SEK" :
                currencyname="کرون سوئد";
                break;
            case "PHP" :
                currencyname="پزوی فیلیپین";
                break;
            case "THB" :
                currencyname="بات تایلند";
                break;
            case "VND" :
                currencyname="دانگ ویتنام";
                break;
            case "CZK" :
                currencyname="کورونای جمهوری چک";
                break;
            case "ZAR" :
                currencyname="راند افریقای جنوبی";
                break;
            case "KWD" :
                currencyname="دینار کویت";
                break;
            case "ZMW" :
                currencyname="کواچای زامبیا";
                break;
            case "AED" :
                currencyname="درهم امارات";
                break;
            case "KRW" :
                currencyname="وون کرهٔ جنوبی";
                break;
            case "CUC" :
                currencyname="پزوی کوبا";
                break;

        }
        return currencyname;
    }
}
