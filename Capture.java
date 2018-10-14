/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitapsearchstring;

import java.io.BufferedReader;
import java.io.File; 
  
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import org.apache.commons.io.filefilter.HiddenFileFilter;



public class Capture {

    public void walk( String path ) throws IOException {

        File root = new File( path );
        File[] list = root.listFiles();
        		File[] hiddenFiles = root.listFiles((FileFilter) HiddenFileFilter.HIDDEN);

                if (list == null) return;

         for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println("Dir:" + f.getAbsoluteFile());
            }
            else {
                    System.out.println("File:" + f.getAbsoluteFile());
                    try {
			FileReader fileReader = new FileReader(f);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("n");
			}
			fileReader.close();
			System.out.println("Nội dung của File :\n");
			System.out.println(stringBuffer.toString());
                        System.out.println("---------------------------------------------------------------");
                        try (PrintWriter p = new PrintWriter(new FileOutputStream("F:\\output.txt", true))) 
                        {
                        p.println(f.getAbsoluteFile());
                        p.println(stringBuffer.toString());
                        } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
}

		} catch (IOException e) {
			e.printStackTrace();
		}
                  
            }
            
        }
         
         for (File hiddenFile : hiddenFiles) {
			System.out.println("\nhidden file: " + hiddenFile.getCanonicalPath());
                        try {
			FileReader fileReader = new FileReader(hiddenFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			System.out.println("Nội dung của File:\n");
			System.out.println(stringBuffer.toString());
                            System.out.println("-------------------------------------------------------");
                             try (PrintWriter p = new PrintWriter(new FileOutputStream("F:\\output.txt", true))) 
                        {
                            p.println("File ẩn : ");
                        p.println(hiddenFile.getAbsoluteFile());
                        p.println(stringBuffer.toString());
                        } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
         
    }

    public static void main(String[] args) throws IOException {
        Capture fw = new Capture();
        System.out.println("Nhap duong dan folder ban muon scan ( Vi du : F:/DEMO) : "); // Vi DU : ( C:/ , F:/)
      Scanner s1 = new Scanner(System.in);
      String folderPath = s1.next();
        fw.walk(folderPath);
    }

}

