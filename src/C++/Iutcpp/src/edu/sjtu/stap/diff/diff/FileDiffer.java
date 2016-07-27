package edu.sjtu.stap.diff.diff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileDiffer {

    private CPPFileFilter cppFileFilter;
    private DifferResult differResult;

    public FileDiffer() {
        cppFileFilter = new CPPFileFilter();
        differResult = new DifferResult();
    }


    public DifferResult diff(String oldDir, String newDir) throws IOException {
        if (!oldDir.endsWith("/")) {
            oldDir += "/";
        }
        if (!newDir.endsWith("/")) {
            newDir += "/";
        }
        return this.diff(oldDir, newDir, "");
    }

    public DifferResult diff(String oldDir, String newDir, String base) throws IOException {
        System.out.println("FileDiffer.diff( " + oldDir + ", " + newDir + ", " + base + ")");

        File oldFolder = new File(oldDir + base);
        File newFolder = new File(newDir + base);


        if (oldFolder.isDirectory() && newFolder.isDirectory()) {
            List<File> oldFiles = new ArrayList<>();
            List<File> newFiles = new ArrayList<>();

            HashMap<String, File> dirMap = new HashMap<>();

            //compare files
            for (File file : newFolder.listFiles()) {
                if (cppFileFilter.accept(file)) {
                    newFiles.add(file);
                } else if (file.isDirectory()) {

                    dirMap.put(file.getName(), file);
                }
            }

            for (File file : oldFolder.listFiles()) {
                if (cppFileFilter.accept(file)) {
                    oldFiles.add(file);
                } else if (file.isDirectory()) {
                    File dirInNew = dirMap.get(file.getName());
                    if (dirInNew == null) {//dir deleted

                        System.out.println("\nDir Deleted: " + file.getAbsolutePath());
                        differResult.appendFileDeleted(file.getAbsolutePath());
                    } else {
//						System.out.println("filename: "+oldDir+file.getName());
//                      Fixed: BUG: oldDir should be oldFolder's path, new* too

//                      diff this dir
                        this.diff(oldDir, newDir, base + file.getName() + "/");
                        dirMap.remove(file.getName());
                    }
                }
            }

            for (File file : dirMap.values()) {
                //dir added
                System.out.println("\nDir Added: " + file.getAbsolutePath());
                differResult.appendFileAdded(file.getAbsolutePath());
            }

            diffFiles(oldFiles, newFiles);

            return differResult;


        } else if (!oldFolder.isDirectory()) {

            System.out.println("ERROR:" + oldFolder.getName() + " is not a DIRECTORY!");
            return null;//TODO: better to raise an exception
        } else {
            System.out.println("ERROR:" + newFolder.getName() + " is not a DIRECTORY!");
            return null;//TODO: better to raise an exception
        }
    }

    private void diffFiles(List<File> oldFiles, List<File> newFiles) throws IOException {

        HashMap<String, File> newMap = new HashMap<>();

        for (File file : newFiles) {
            newMap.put(file.getName(), file);
        }

        for (File file : oldFiles) {
            String filename = file.getName();
            File fileInNew = newMap.get(filename);

            if (fileInNew == null) {
                //file deleted

                System.out.println("\n\tFile Deleted: " + file.getAbsolutePath());
                differResult.appendFileDeleted(file.getAbsolutePath());
            } else {
                String contentOld = FileUtils.readFileToString(file);
                String contentNew = FileUtils.readFileToString(fileInNew);
                if (!contentOld.equals(contentNew)) {
                    ASTDiffer astDiffer = null;
                    try {
                        astDiffer = new ASTDiffer(file, fileInNew);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("\n\tFile Modified: " + file.getAbsolutePath());

                    if (astDiffer.getIsOtherChanged()) {
                        System.out.println("Other element Changed! RERUN ALL TEST CASES!");
                        differResult.setOtherChanged(true);
                        return;
                    }
                    if (astDiffer.functionAdded.size() != 0) {
                        System.out.println("Function added! RERUN ALL TEST CASES!");
                        differResult.setOtherChanged(true);
                        return;
                    }
                    differResult.appendFileModified(file.getAbsolutePath(), astDiffer);
                }
//				}

                newMap.remove(filename);
            }
        }

        for (String filename : newMap.keySet()) {
            //file added
            System.out.println("\n\tFile Added: " + newMap.get(filename).getAbsolutePath());
            differResult.appendFileAdded(newMap.get(filename).getAbsolutePath());
        }

    }


}