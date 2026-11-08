
package kz.aitu.visitor;

public class PrintVisitor implements Visitor {

    private int depth = 0;

    @Override
    public void visit(FileNode file) {
        printIndent();
        System.out.println(file.getName()
                + " (" + file.getSize() + " bytes)");
    }

    @Override
    public void visit(DirectoryNode directory) {
        printIndent();
        System.out.println(directory.getName() + "/");

        depth++;

        for (Element child : directory.getChildren()) {
            child.accept(this);
        }

        depth--;
    }

    private void printIndent() {
        System.out.print("  ".repeat(depth));
    }
}
