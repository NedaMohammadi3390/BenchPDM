package content_generation;

import java.util.ArrayList;

public class Builder extends Constants {
    private String packageName;
    private ArrayList<Builder> order;

    public Builder(String packageName) {
        this.packageName = packageName;
    }

    public Builder() {}

    public Builder setContent(Builder builder) {
        if (order == null) {
            order = new ArrayList<>();
        }
        order.add(builder);
        return this;
    }

    public Builder getBuilderClass() {

        return order.get(order.size()-1);
    }

    public String build() {
        return content();
    }

    public String content() {
        StringBuilder classContent = new StringBuilder();

        if (order != null) {
            for (Object build : order) {

                classContent.append(((IGenerate) build).generate());
            }
        }

        return classContent.toString();
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return order.get(0).getClassName();
    }

}
