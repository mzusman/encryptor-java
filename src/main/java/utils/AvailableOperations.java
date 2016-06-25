package utils;

import filehandler.operations.DecryptionOperation;
import filehandler.operations.EncryptionOperation;
import filehandler.operations.Operation;
import lombok.AllArgsConstructor;

/**
 * Created by mzeus on 6/25/16.
 */
@AllArgsConstructor
public enum AvailableOperations {
    DECRYPT(new DecryptionOperation()),
    ENCRYPT(new EncryptionOperation());

    Operation operation;

}
