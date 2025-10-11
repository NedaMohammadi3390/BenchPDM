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

        public static String AddNewType(String newType) {
            return newType + space;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }
//#########################################################################
    public enum ElementType {
        PACKAGE, CLASS, INTERFACE,ENUM;

        @Override
        public String toString() {
            return super.toString().toLowerCase() + space;
        }
    }
//###############################################################################
    public enum AccessLevel {
        PRIVATE("private "),
        PUBLIC("public "),
        PACKAGE_PRIVATE("package-private "),
        CUSTOM("");

        private final String label;

        AccessLevel(String label) {
            this.label = label;
        }

    public static String AddNewType(String newType) {
        return newType + space;
    }
        @Override
        public String toString() {
            System.out.println("label is:"+ label);
            return  super.toString().toLowerCase() + " " ;
        }
    }

    //###########################################################################
    public enum ReturnType {
        INT("int"),
        FLOAT("float"),
        DOUBLE("double"),
        BOOLEAN("boolean"),
        VOID("void"),
        CONSTRUCTOR("constructor"),
        CUSTOM("custom");

        private String label;
        ReturnType(String label) {
            this.label = label;
        }

        public static String AddNewType(String newType) {
            return newType + space;
        }

        @Override
        public String toString() {
            return label + " " + super.toString().toLowerCase() + " " ;
        }

    }

    //##############################################################################

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

    static String annotate = "";
    public enum VariableTypeRequestBody{
        Object, String;


        public static String AddNewType(String annotation, String newType) {
            annotate = annotation;
            return annotation + space + newType.toLowerCase() + space;

        }

        @Override
        public String toString() {
            return annotate + space + super.toString() + space;
        }
    }

    public enum VariableTypeRequestBody2{
        Object, String;

        public static String AddNewType(String newType) {
            return newType.toLowerCase();
        }

        @Override
        public String toString() {
            return super.toString();
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
    public enum Exceptions {
        Exception;

        @Override
        public String toString() {
            return Keywords.THROWS + super.toString();
        }
    }


    public enum Annotations {
        RequestMapping, RestController, Autowired, GetMapping, JsonProperty, DeleteMapping,
        Service, Repository, Override, SpringBootApplication ,PostMapping, PutMapping,
       EnableDiscoveryClient, EnableZuulProxy,Component, EnableEurekaServer, Slf4j,Data, Getter, Setter, EnableCaching,
       Configuration, Value, AllArgsConstructor,NoArgsConstructor,Document, Id, Bean, LoadBalanced ;


        String content = "";


        public  static String setContent(String content) {

            return content;
        }


        @Override
        public String toString() {
            return "@" + super.toString();
        }

       public String toShortString() {
           return  super.toString();
       }
    }

}