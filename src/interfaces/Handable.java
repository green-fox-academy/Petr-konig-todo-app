package interfaces;

import exceptions.*;

public interface Handable {
    void handleArgs(String[] args) throws NoTaskProvidedException, NoIndexProvidedException, GreaterIndexException, NotANumberException, UnsupportedArgumentException, NoIndexProvidedCheckException, NotANumberCheckException, GreaterIndexCheckException;
}
