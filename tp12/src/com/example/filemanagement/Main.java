
package com.example.filemanagement;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Main {

    // 1. Lecture avec BufferedReader
    public static void readFileWithBufferedReader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture : " + e.getMessage());
        }
    }

    // 2. Ecriture avec BufferedWriter
    public static void writeFileWithBufferedWriter(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
            bw.newLine();
            bw.write("Ligne ajoutee avec succes.");
            System.out.println("Fichier ecrit avec succes.");
        } catch (IOException e) {
            System.err.println("Erreur ecriture : " + e.getMessage());
        }
    }

    // 3. Ecriture avec PrintWriter
    public static void writeWithPrintWriter(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath))) {
            pw.println("=== Rapport des employes ===");
            pw.printf("Nom: %s, Age: %d, Poste: %s%n", "Ali", 25, "Engineer");
            pw.printf("Nom: %s, Age: %d, Poste: %s%n", "Oussama", 30, "Manager");
            pw.println("Fin du rapport.");

            if (pw.checkError()) {
                System.err.println("Erreur pendant l'ecriture.");
            } else {
                System.out.println("Rapport cree avec succes.");
            }
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    // 4. Lecture avec Files API
    public static void readWithFilesApi(String filePath) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lecture Files : " + e.getMessage());
        }
    }

    // 5. Ecriture avec Files API
    public static void writeWithFilesApi(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, content.getBytes());
            System.out.println("Fichier ecrit : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur ecriture : " + e.getMessage());
        }
    }

    // 6. Copie d'un fichier
    public static void copyFile(String source, String destination) {
        try {
            Files.copy(Paths.get(source), Paths.get(destination),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichier copie avec succes.");
        } catch (IOException e) {
            System.err.println("Erreur copie : " + e.getMessage());
        }
    }

    // 7. Deplacement d'un fichier
    public static void moveFile(String source, String destination) {
        try {
            Files.move(Paths.get(source), Paths.get(destination),
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichier deplace avec succes.");
        } catch (IOException e) {
            System.err.println("Erreur deplacement : " + e.getMessage());
        }
    }

    // 8. Suppression d'un fichier
    public static void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Fichier supprime : " + filePath);
            } else {
                System.out.println("Fichier introuvable : " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Erreur suppression : " + e.getMessage());
        }
    }

    // 9. Lecture d'un fichier binaire
    public static void readBinaryFile(String filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytes = 0;

            while ((bytesRead = bis.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }

            System.out.println("Fichier binaire lu : " + filePath);
            System.out.println("Taille : " + totalBytes + " octets");
        } catch (IOException e) {
            System.err.println("Erreur binaire : " + e.getMessage());
        }
    }

    // 10. Ecriture d'un fichier binaire
    public static void writeBinaryFile(String filePath, byte[] data) {
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(filePath))) {
            bos.write(data);
            System.out.println("Fichier binaire cree.");
            System.out.println("Taille : " + data.length + " octets");
        } catch (IOException e) {
            System.err.println("Erreur binaire : " + e.getMessage());
        }
    }

    // 11. Metadonnees du fichier
    public static void getFileMetadata(String filePath) {
        try {
            Path path = Paths.get(filePath);
            BasicFileAttributes attrs = Files.readAttributes(
                    path, BasicFileAttributes.class);

            System.out.println("\n=== METADONNEES ===");
            System.out.println("Nom : " + path.getFileName());
            System.out.println("Taille : " + attrs.size() + " octets");
            System.out.println("Derniere modification : " + attrs.lastModifiedTime());
            System.out.println("Est un repertoire : " + attrs.isDirectory());
            System.out.println("Est un fichier : " + attrs.isRegularFile());

        } catch (IOException e) {
            System.err.println("Erreur metadata : " + e.getMessage());
        }
    }

    // 12. Programme principal
    public static void main(String[] args) {

        try {
            Files.createDirectories(Paths.get("data"));

            // Creation du fichier employees.txt
            Files.write(Paths.get("data/employees.txt"),
                    ("Ali,25,Engineer\n" +
                     "Oussama,30,Manager\n").getBytes());

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
            return;
        }

        System.out.println("==================================");
        System.out.println(" TP 12 : GESTION DES FICHIERS JAVA");
        System.out.println("==================================");

        System.out.println("\n1. LECTURE BUFFEREDREADER");
        readFileWithBufferedReader("data/employees.txt");

        System.out.println("\n2. ECRITURE BUFFEREDWRITER");
        writeFileWithBufferedWriter("data/output.txt",
                "Bienvenue dans le TP Java.");

        System.out.println("\n3. ECRITURE PRINTWRITER");
        writeWithPrintWriter("data/report.txt");

        System.out.println("\n4. LECTURE FILES API");
        readWithFilesApi("data/employees.txt");

        System.out.println("\n5. ECRITURE FILES API");
        writeWithFilesApi("data/nio_output.txt",
                "Test de l'API Files Java.");

        System.out.println("\n6. COPIE FICHIER");
        copyFile("data/employees.txt",
                "data/employees_backup.txt");

        System.out.println("\n7. DEPLACEMENT FICHIER");
        moveFile("data/nio_output.txt",
                "data/moved_output.txt");

        System.out.println("\n8. METADONNEES");
        getFileMetadata("data/employees.txt");

        System.out.println("\n9. SUPPRESSION FICHIER");
        deleteFile("data/moved_output.txt");

        System.out.println("\n10. TESTS TERMINES !");
    }
}
