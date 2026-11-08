
package kz.aitu.visitor;

public class Main {

    public static void main(String[] args) {

        DirectoryNode root = new DirectoryNode("documents");

        FileNode file1 = new FileNode("report.pdf", 500);
        FileNode file2 = new FileNode("notes.txt", 100);

        DirectoryNode photos = new DirectoryNode("photos");
        FileNode image = new FileNode("image.jpg", 800);

        root.add(file1);
        root.add(file2);
        root.add(photos);
        photos.add(image);

        System.out.println("=== FILE STRUCTURE ===");
        PrintVisitor printer = new PrintVisitor();
        root.accept(printer);

        System.out.println("\n=== TOTAL SIZE ===");
        SizeVisitor sizeVisitor = new SizeVisitor();
        root.accept(sizeVisitor);
        System.out.println(
                "Total size: " + sizeVisitor.getTotalSize() + " bytes"
        );

        System.out.println("\n=== SEARCH RESULTS ===");
        SearchVisitor searchVisitor = new SearchVisitor("report");
        root.accept(searchVisitor);

        for (String result : searchVisitor.getResults()) {
            System.out.println(result);
        }
        System.out.println("\n=== STATISTICS ===");

        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);

        System.out.println("Files: " + stats.getFileCount());
        System.out.println("Directories: " + stats.getDirectoryCount());
        System.out.println("Max depth: " + stats.getMaxDepth());
    }
}
