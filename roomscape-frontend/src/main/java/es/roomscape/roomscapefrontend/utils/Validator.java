package es.roomscape.roomscapefrontend.utils;

public class Validator {

    private static final String NUMBER_FIELD_ERROR_MESSAGE = "El campo %s debe ser numérico.";
    private static final String EMPTY_FIELD_ERROR_MESSAGE = "El campo %s no debe estar vacío.";

    public static Integer NumericFieldValidator(String value, String fieldName) throws Exception {
        Integer result;
        try {
            result = Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new Exception(String.format(NUMBER_FIELD_ERROR_MESSAGE, fieldName));
        }
        return result;
    }

    public static String EmptyFieldValidator(String value, String fieldName) throws Exception {
        if (value.trim().isEmpty()) {
            throw new Exception(String.format(EMPTY_FIELD_ERROR_MESSAGE, fieldName));
        }
        return value;
    }

    public static Double DoubleFieldValidator(String value, String fieldName) throws Exception {
        Double result;
        try {
            result = Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new Exception(String.format(NUMBER_FIELD_ERROR_MESSAGE, fieldName));
        }
        return result;
    }
}
