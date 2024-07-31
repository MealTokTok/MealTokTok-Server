package core.startup.mealtoktok.domain.user.exception;

import core.startup.mealtoktok.common.exception.DomainException;

public class AlreadyExistAddressException extends DomainException {

  public static final AlreadyExistAddressException EXCEPTION = new AlreadyExistAddressException();

  private AlreadyExistAddressException() {
    super(UserErrorCode.ALREADY_EXIST_ADDRESS);
  }

}
