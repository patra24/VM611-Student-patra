package types;

/**
 * Represents a data type.
 */
public class DataType {
    /** The scalar type - void, int, object */
    private BaseType baseType;

    public DataType(String typeName) {
        switch (typeName) {
        case "int" -> this.baseType = BaseType.INT;
        case "void" -> this.baseType = BaseType.VOID;
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }

    @Override
    public String toString() {
        return baseType.toString();
    }

    public static DataType INT = new DataType("int");
    public static DataType VOID = new DataType("void");

    public enum BaseType {
        VOID,
        INT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        public static BaseType fromString(String str) {
            return BaseType.valueOf(str.toUpperCase());
        }
    }
}
