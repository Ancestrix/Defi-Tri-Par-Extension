package org.example;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;
import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner chemin = new Scanner(System.in);
        System.out.println("Veuillez entrer le chemin absolu du répertoire à trier :");
        String path=chemin.nextLine();

        FileFilter logFilefilter = file -> {
            if (file.isDirectory()==false) {
                return true;
            }
            return false;
        };

        File dossier = new File(path);
        File[] files = dossier.listFiles(logFilefilter);
        String[] filesName = dossier.list();
        int nbFile = dossier.listFiles(logFilefilter).length;
        for (int i=0;i<nbFile;i++){
            String fe = FilenameUtils.getExtension(files[i].getName());
            System.out.println("File extension is : "+fe);

            File dossierTri = new File(dossier.getAbsolutePath()+"\\"+fe);
            boolean res = dossierTri.mkdir();
            if(res) {
                System.out.println("Le dossier a été créé.");
                files[i].renameTo(new File(dossierTri+"\\"+files[i].getName()));

            }
            else {
                System.out.println("Le dossier existe déja.");
                files[i].renameTo(new File(dossierTri+"\\"+files[i].getName()));
            }
        }
    }
}