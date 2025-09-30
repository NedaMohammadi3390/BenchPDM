package content_generation;

public abstract class Constants {
    public static int tabCounter = 0;
    public static final String space = " ";
    public static final String emptyLine = "\n\n";
    public static final String nextLine = "\n";
    public static final String semicolon = ";" + nextLine;
    public static final String openBrace = space + "{" + nextLine;
    public static final String closeBrace = nextLine + "}" + emptyLine;
    public static final String openParenthesis = "(";
    public static final String closeParenthesis = ")";
    public static final String equalSymbol = space + "=" + space;
    public static final String comma = "," + space;

    public enum Keywords {
        FOR, IF, WHILE, DO, THROWS, RETURN, IMPLEMENTS, EXTENDS, TRUE, FALSE, NULL;

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }

    public enum ElementType {
        PACKAGE, CLASS, INTERFACE;

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }

    public enum AccessLevel {
        PRIVATE, PUBLIC, PACKAGE_PRIVATE;

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }

    public enum VariableType {
        BOOLEAN, BYTE, CHAR, SHORT, INT, LONG, FLOAT, DOUBLE;

        public static String AddNewType(String newType) {
            return newType + space;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }

    public enum VariableTypeRequestBody{
        Object, String;

        public static String AddNewType(String newType) {
            return "@RequestBody" + space + newType.toLowerCase() + space;
        }

        @Override
        public String toString() {
            return "@RequestBody" + space + super.toString() + space;
        }
    }

    public enum VariableTypeRequestParam{
        Object, String;

        public static String AddNewType(String newType) {
            return "@RequestParam" + space + newType.toLowerCase() + space;
        }

        @Override
        public String toString() {
            return "@RequestParam" + space + super.toString() + space;
        }
    }

    public enum ReturnType {
        INT, FLOAT, DOUBLE, BOOLEAN, VOID, CONSTRUCTOR;

        public static String AddNewType(String newType) {
            return newType + space;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().equals("constructor") ? "" : super.toString().toLowerCase() + space;
        }
    }

    public enum Exceptions {
        Exception;

        @Override
        public String toString() {
            return Keywords.THROWS + super.toString();
        }
    }


    public enum Annotations {
        RequestMapping, RestController, Autowired, GetMapping, JsonProperty, DeleteMapping,
        Service, Repository, Override, SpringBootApplication ,PostMapping, PutMapping;

        String content = "";


        public Annotations setContent(String content) {
            this.content = content;
            return this;
        }


        @Override
        public String toString() {
            return "@" + super.toString();
        }
    }

}