package au.edu.tafesa.itstudies.bookcatalog.DAO;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import au.edu.tafesa.itstudies.bookcatalog.models.BookShoppingCartModel;

/**
 * Created by sruiz on 26/03/2018.
 * Updated by sruiz on 25/3/2018
 *      General tidy up.
 */

public class BookShoppingCartFileDAO {
    public static final String BOOKS_FILENAME = "books.bin";

    /**
     *
     * @param context The context(Activity) that owns the file
     * @return A new BookShoppingCartModel read from the file BOOKS_FILENAME
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static BookShoppingCartModel loadFromBinaryFile(Context context) throws IOException, ClassNotFoundException {

        BookShoppingCartModel modelRead;
        //TODO
        File file = new File(context.getFilesDir(), BOOKS_FILENAME);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        modelRead = (BookShoppingCartModel)ois.readObject();
        ois.close();

        // Read in the Model from the binary file created by saveToBinaryFile
        return modelRead;
    }

    /**
     *
     * @param context The context(Activity) that owns the file
     * @param bookShoppingCartModel The BookShoppingCartModel to be saved.
     * @throws IOException
     */
    public static void saveToBinaryFile(Context context, BookShoppingCartModel bookShoppingCartModel) throws IOException {
        //TODO
        // Write out the Model to a binary file
        File file = new File(context.getFilesDir(), BOOKS_FILENAME);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(bookShoppingCartModel);
        objectOutputStream.close();
    }
}
