package uwt.ctrlfworkload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Resources resources = getResources();
        String fileContents = null;
        try {
            fileContents = loadFile("from_raw_folder.txt", false, resources);
            TextView tv1 = (TextView)findViewById(R.id.txtFile);
            tv1.setText(fileContents);
        }catch (IOException e){
            final Toast toast = Toast.makeText(this, "File: not found!", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public String loadFile(String fileName, boolean loadFromRawFolder, Resources resources) throws IOException
    {
        //Create a InputStream to read the file into
        InputStream iS;

        if (loadFromRawFolder)
        {
            //get the resource id from the file name
            int rID = resources.getIdentifier("uwt.ctrlfworkload:raw/"+fileName, null, null);
            //get the file as a stream
            iS = resources.openRawResource(rID);
        }
        else
        {
            //get the file as a stream
            iS = resources.getAssets().open(fileName);
        }

        //create a buffer that has the same size as the InputStream
        byte[] buffer = new byte[iS.available()];
        //read the text file as a stream, into the buffer
        iS.read(buffer);
        //create a output stream to write the buffer into
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        //write this buffer to the output stream
        oS.write(buffer);
        //Close the Input and Output streams
        oS.close();
        iS.close();

        //return the output stream as a String
        return oS.toString();
    }
}
