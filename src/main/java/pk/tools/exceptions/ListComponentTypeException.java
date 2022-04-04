package pk.tools.exceptions;

import pk.tools.ListWC;

public class ListComponentTypeException extends Exception {

    public ListComponentTypeException(String errorMessage) {
        super(errorMessage);
    }

    public ListComponentTypeException(ListWC<?> listWC) {
        super(String.format("Widget List with name='%s', genericType='%s' didn't get correct generic type",
                listWC.getName(),
                listWC.getGenericTypeName()));
    }

    public ListComponentTypeException(ListWC<?> listWC, String errorMessage) {
        super(String.format(errorMessage, listWC.getName()));
    }
}
