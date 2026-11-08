package kz.aitu.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode implements Element {

    private final String name;
    private final List<Element> children = new ArrayList<>();

    public DirectoryNode(String name) {
        if (name == null || name.isBlank()
                || name.contains("/")) {
            throw new IllegalArgumentException(
                    "Invalid directory name");
        }

        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void add(Element child) {
        if (child == null) {
            throw new IllegalArgumentException(
                    "Child cannot be null");
        }

        if (child == this) {
            throw new IllegalArgumentException(
                    "Cannot add directory to itself");
        }

        for (Element existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException(
                        "Duplicate child name");
            }
        }

        children.add(child);
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

