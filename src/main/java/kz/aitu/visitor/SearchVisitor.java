package kz.aitu.visitor;

import java.util.ArrayList;
import java.util.List;

public class SearchVisitor implements Visitor {

    private final String keyword;
    private final List<String> results = new ArrayList<>();

    public SearchVisitor(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Keyword cannot be empty");
        }
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public void visit(FileNode file) {
        if (file.getName().toLowerCase().contains(keyword)) {
            results.add(file.getName());
        }
    }

    @Override
    public void visit(DirectoryNode directory) {
        if (directory.getName().toLowerCase().contains(keyword)) {
            results.add(directory.getName());
        }

        for (Element child : directory.getChildren()) {
            child.accept(this);
        }
    }

    public List<String> getResults() {
        return List.copyOf(results);
    }
}
