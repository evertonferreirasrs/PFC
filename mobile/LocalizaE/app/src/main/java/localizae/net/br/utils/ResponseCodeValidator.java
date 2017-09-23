package localizae.net.br.utils;

import localizae.net.br.controller.R;

public class ResponseCodeValidator {

    public static String validateResponseCode(int responseCode) {
        String text = "";
        switch (responseCode) {
            case Constants.RETROFIT_FAILURE:
                text = Constants.RESPONSE_VALUE_RETROFIT_FAILURE;
                break;
            case 200:
                text = Constants.RESPONSE_VALUE_200;
                break;
            case 400:
                text = Constants.RESPONSE_VALUE_400;
                break;
            case 404:
                text = Constants.RESPONSE_VALUE_404;
                break;
            case 405:
                text = Constants.RESPONSE_VALUE_405;
                break;
            case 500:
                text = Constants.RESPONSE_VALUE_500;
                break;
            default:
                text = Constants.RESPONSE_UNKNOWN;
                break;
        }
        return  text;
    }

}
