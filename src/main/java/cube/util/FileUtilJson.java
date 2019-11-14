/**
 * Handling of JSON File Read/Write operations in Cube.
 *
 * @author kuromono
 */

package cube.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import cube.exception.CubeException;
import cube.util.exception.CubeUtilException;
import cube.util.exception.UtilErrorMessage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FileUtilJson<T> extends FileUtil {
    private T fileObject;
    private String fileFullPath;
    private File file;
    private ObjectMapper mapper;
    private final Logger logger = LogUtil.getLogger(FileUtilJson.class);

    /**
     * Constructor with two argument.
     *
     * @param filePath the directory path where data will be stored.
     */
    public FileUtilJson(String filePath, String fileName, T fileObject) {
        super(filePath, fileName);
        this.fileFullPath = filePath + File.separator + fileName;
        this.fileObject = fileObject;
        this.file = new File(fileFullPath);
        this.mapper = new ObjectMapper();
    }

    /**
     * Loads the serialized object from the JSON file.
     *
     * @return De-serialized object read from the JSON file.
     * @throws CubeUtilException exception occurs in reading from data file.
     */
    public T load() throws CubeException {
        if (checkFileAvailable(true)) {
            logger.info("Loading file from : " + fileFullPath);

            try {
                JavaType type = mapper.getTypeFactory().constructType(fileObject.getClass());
                fileObject = mapper.readValue(file, type);
            } catch (IOException e) {
                logger.warning(UtilErrorMessage.READ_ERROR + fileFullPath);
                throw new CubeUtilException(UtilErrorMessage.READ_ERROR + fileFullPath);
            }
        }
        return fileObject;
    }

    /**
     * Saves the object into a JSON file.
     *
     * @param fileObject Object to be saved into JSON.
     * @throws CubeException exception happens in writing to the data file.
     */
    public void save(T fileObject) throws CubeException {
        checkFileAvailable(true);
        try {
            mapper.writeValue(file, fileObject);
        } catch (IOException e) {
            logger.warning(UtilErrorMessage.WRITE_ERROR + fileFullPath);
            throw new CubeUtilException(UtilErrorMessage.WRITE_ERROR + fileFullPath);
        }
    }
}
