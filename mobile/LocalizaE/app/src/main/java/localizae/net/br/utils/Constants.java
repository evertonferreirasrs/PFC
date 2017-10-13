package localizae.net.br.utils;

public class Constants {

    // Timers
    public static final long BEACON_SCAN_PERIOD = 6000;

    // Permission request code
    public static final int REQUEST_PERMISSION_CODE = 1234;

    // Failure code
    public static final int RETROFIT_FAILURE = -1000;

    // TAGS
    public static final String USER_SERVICE_TAG = "USER_SERVICE";
    public static final String BEACON_SERVICE_TAG = "BEACON_SERVICE";
    public static final String BOOTH_SERVICE_TAG = "BOOTH_SERVICE";

    public static final String BEACON_SCANNER_TAG = "BEACON_SCANNER";
    public static final String BEACON_SCAN_ACTIVITY_TAG = "BEACON_SCAN_ACT";

    public static final String CREATE_USER_ACTIVITY_TAG = "CADASTRAR_USUARIO_ACT";
    public static final String MAP_ACTIVITY_TAG = "MAP_ACT";
    public static final String LOGIN_ACTIVITY_TAG = "LOGIN_ACT";

    // Timeout
    public static final int BLE_SCAN_TIMEOUT = 5000;

    // User Kind/
    public static final Long USER_VISITANT = 2L;

    // Intent keys
    public static final String RESPONSE_CODE_KEY = "response_code";
    public static final String DATA_KEY = "data";

    // Texts
    public static final String RESPONSE_VALUE_200  = "Sucesso";
    public static final String RESPONSE_VALUE_400  = "Dados incorretos";
    public static final String RESPONSE_VALUE_404  = "Recurso não encontrado";
    public static final String RESPONSE_VALUE_405  = "Metodo nao suportado";
    public static final String RESPONSE_VALUE_500  = "Erro no servidor";
    public static final String RESPONSE_UNKNOWN  = "Erro desconhecido";

    public static final String RESPONSE_VALUE_RETROFIT_FAILURE = "Algo deu errado";


}
